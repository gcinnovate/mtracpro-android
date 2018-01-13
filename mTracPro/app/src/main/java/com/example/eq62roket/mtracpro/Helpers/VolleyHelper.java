package com.example.eq62roket.mtracpro.Helpers;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by probuse on 1/6/18.
 */

public class VolleyHelper {

    private static final String TAG = "VolleyHelper";

    // TODO: 1/5/18 - > add week and year to url
    private static final String url = "http://dispatcher2d.gcinnovate.com/queue?username=admin&password=admin&source=mTracPro_android&destination=dhis2";
    private static final String REPORTING_WEEK_URI = "http://mtracpro.gcinnovate.com/api/v1/reportingweek";

    private Context mContext;
    private OurSharedPreferences mOurSharedPreferences;
    private RequestQueue queue;
    private JsonHelper mJsonHelper;

    private Calendar calendar;
    private String formattedDate;


    public VolleyHelper(Context context){
        mContext = context;
        mOurSharedPreferences = new OurSharedPreferences(context);
        mJsonHelper = new JsonHelper(context);
        queue = Volley.newRequestQueue(context);

        calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

        formattedDate = dateFormat.format(calendar.getTime());

    }


    public int sendData(LinearLayout linearLayout, final String form){

        JSONObject [] mJSONObject = generateJson(linearLayout);
        String msg = "";
        try {
            Log.i("dataValues", mJSONObject[0].get("dataValues").toString());
            msg = mJSONObject[1].getString("rawMsg");

            if (mJSONObject[0].get("dataValues").toString().equals("[]")) {
                Toast.makeText(mContext, "Please enter at least one value", Toast.LENGTH_SHORT).show();
                return -1;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final String facility = mOurSharedPreferences.getSharedPreference("facilityId");
        final String district = mOurSharedPreferences.getSharedPreference("district");
        final String msisdn = mOurSharedPreferences.getSharedPreference("phoneNumber");
        String extra_params = "&report_type=" + form
                + "&district=" + URLEncoder.encode(district)
                + "&facility=" + URLEncoder.encode(facility)
                + "&msisdn=" + URLEncoder.encode(msisdn)
                + "&raw_msg=" + URLEncoder.encode(msg);

        JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST,
                url + extra_params, mJSONObject[0],
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Toast.makeText(mContext, "Report submitted successfully.", Toast.LENGTH_LONG).show();
                        Log.d(TAG, "onResponse: " + response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(mContext, "Failed to submit data", Toast.LENGTH_LONG).show();
                Log.d(TAG, "onErrorResponse: " + error.toString());
            }
        }){

            /**
             * Passing some request headers
             * */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };

        queue.add(postRequest);

        return 0;
    }

    private String getCurrentReportingWeek(){
        calendar.add( Calendar.DAY_OF_WEEK, -(calendar.get(Calendar.DAY_OF_WEEK)-1));
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        String week = String.valueOf(calendar.get(Calendar.WEEK_OF_YEAR));

        return year + "W" + week;
    }

    private void setCurrentReportingWeek(){
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET,
                REPORTING_WEEK_URI, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            mOurSharedPreferences.writeSharedPreference(
                                    "period", response.get("period").toString());
                            Log.i("Reporting Week", mOurSharedPreferences.getSharedPreference("period"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        mOurSharedPreferences.writeSharedPreference(
                                "period", getCurrentReportingWeek()); /* fix getCurrentReportingWeek*/
                        Log.i("Error getting Period", ""+ error.toString());
                    }
                }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("Content-Type", "application/json; charset=utf-8");
                    return headers;
                }
        };
        queue.add(getRequest);
    }



    private JSONObject [] generateJson(LinearLayout linearLayout){
        JSONObject [] ret = new JSONObject[2];
        setCurrentReportingWeek();
        final ArrayList<JSONObject> collection = new ArrayList<>();
        final JSONObject mJSONObject = new JSONObject();
        final JSONObject rawMsgObj = new JSONObject();
        JSONArray mJSONArray = new JSONArray();
        String dataSet = "V1kJRs8CtW4";
        try {
            mJSONObject.put("dataSet", dataSet);
            mJSONObject.put("completeDate", formattedDate);
            mJSONObject.put("period", mOurSharedPreferences.getSharedPreference("period"));
            Log.i("Reporting Week", "period=" + mJSONObject.getString("period"));
            mJSONObject.put("attributeOptionCombo", "");
            mJSONObject.put("orgUnit", mOurSharedPreferences.getSharedPreference("facilityId"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        String rawMsg = "";
        for (int i = 0; i < linearLayout.getChildCount(); i++){
            View view = linearLayout.getChildAt(i);
            if (view instanceof TextInputLayout){
                EditText et = ((TextInputLayout) view).getEditText();
                String id = mContext.getApplicationContext().getResources().getResourceEntryName(et.getId());
                String [] kwAndCommand = id.split("_", 2);
                String keyword = kwAndCommand[0];
                String command = kwAndCommand[1];


                JSONObject body = new JSONObject();
                if (et.getText().toString().isEmpty()){
                    continue;
                }

                /* Try and build raw message*/
                if (keyword.equals("cases") || keyword.equals("death")){
                    if (rawMsg.isEmpty()){
                        rawMsg += keyword + "." + command + "." + et.getText().toString();
                    } else {
                        rawMsg += "." + command + "." + et.getText().toString();
                    }

                } else {
                    if (rawMsg.isEmpty()){
                        rawMsg += keyword + "." + et.getText().toString();
                    }
                    else {
                        rawMsg += "." + et.getText().toString();

                    }

                }
                /* End build raw message */
                try {
                    body.put("categoryOptionCombo", mJsonHelper.getJsonValue(id, "categoryOptionCombo"));
                    body.put("dataElement", mJsonHelper.getJsonValue(id, "dataElement"));
                    body.put("value", et.getText().toString());
                    mJSONArray.put(body);
                    collection.add(mJSONObject);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        Log.i("RAW MESSAGE:", rawMsg);

        try {
            rawMsgObj.put("rawMsg", rawMsg); /* Lets put in the rawMsg here*/
            ret[1] = rawMsgObj;
            mJSONObject.put("dataValues", mJSONArray);
            ret[0] = mJSONObject;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return ret;
    }
}
