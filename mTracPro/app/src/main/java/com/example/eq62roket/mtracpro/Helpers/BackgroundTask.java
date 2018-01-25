package com.example.eq62roket.mtracpro.Helpers;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.eq62roket.mtracpro.Interfaces.HistoryVolleyCallBack;
import com.example.eq62roket.mtracpro.Interfaces.MohBulletinVolleyCallBack;
import com.example.eq62roket.mtracpro.Models.Bulletin;
import com.example.eq62roket.mtracpro.Models.History;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by eq62roket on 1/11/18.
 */

public class BackgroundTask {
    private static final String TAG = "BackgroundTask";
    Context context;
    private OurSharedPreferences sharedPrefs;
    ArrayList<History> arrayList = new ArrayList<>();

    String json_url = "http://mtracpro.gcinnovate.com/api/v1/reporterhistory/";
    String bulletin_url = "http://10.150.222.126/bulletin.php";

    ArrayList<Bulletin> bulletinArrayList = new ArrayList<>();

    public BackgroundTask(Context context){

        this.context = context;
        sharedPrefs = new OurSharedPreferences(context);
    }

    public void getHistoryList(final HistoryVolleyCallBack callBack){
        callBack.onStart();
        String phoneNumber = sharedPrefs.getSharedPreference("phoneNumber");
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                json_url + phoneNumber, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        int count = 0;
                        while (count < response.length()){
                            try {
                                JSONObject jsonObject = response.getJSONObject(count);
                                Log.d(TAG, "onResponse: " + jsonObject);
                                History history = new History(jsonObject.getString("period"),
                                        jsonObject.getString("rawMsg"), jsonObject.getString("details"),
                                        jsonObject.getString("date"), jsonObject.getString("period"));
                                arrayList.add(history);
                                count++;
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        callBack.onSuccess(arrayList);
                        Log.d(TAG, "onResponse: " + arrayList);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                callBack.onFailure(volleyError);
                Toast.makeText(context, "Network Unavailable. Please try again later", Toast.LENGTH_SHORT).show();
                volleyError.printStackTrace();
            }
        });

        VolleySingleton.getInstance(context).addToRequestQue(jsonArrayRequest);
    }

    public void getBulletin(final MohBulletinVolleyCallBack mohBulletinVolleyCallBack){
        JsonArrayRequest BulletinJsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                bulletin_url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        int count = 0;
                        while (count<response.length()){
                            try {
                                JSONObject jsonObject = response.getJSONObject(count);
                                Log.d(TAG, "onResponse: " + jsonObject);
                                Bulletin bulletin = new Bulletin(jsonObject.getString("id"),
                                        jsonObject.getString("date"), jsonObject.getString("content"));
                                bulletinArrayList.add(bulletin);
                                count++;
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        mohBulletinVolleyCallBack.onSuccess(bulletinArrayList);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                mohBulletinVolleyCallBack.onFailure(volleyError);
                Toast.makeText(context, "Network Unavailable. Please try again later", Toast.LENGTH_SHORT).show();
                volleyError.printStackTrace();
            }
        });

        VolleySingleton.getInstance(context).addToRequestQue(BulletinJsonArrayRequest);
    }

}
