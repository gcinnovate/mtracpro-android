package com.example.eq62roket.mtracpro.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.eq62roket.mtracpro.Helpers.OurSharedPreferences;
import com.example.eq62roket.mtracpro.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity {

    private final static String TAG = "HomeActivity";
    private final String url = "http://192.168.0.100:8080/api/v1/reporter";
    // dispatcher2d.gcinnovate.com/queue?username=admin&password=admin  expects our json

    private RelativeLayout cases_relative_layout;
    private TextView tvName, tvTotalReports, tvLastReportDate, tvDistrict, tvHealthF;

    private RequestQueue queue;
    private OurSharedPreferences mOurSharedPreferences;


    private String name, totalReports, facility, district, lastReportingDate, facilityId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tvName = (TextView) findViewById(R.id.tvName);
        tvHealthF =(TextView) findViewById(R.id.tvHealthF);
        tvDistrict = (TextView) findViewById(R.id.tvDistrict);
        tvTotalReports = (TextView) findViewById(R.id.tvTotalReports);
        tvLastReportDate = (TextView) findViewById(R.id.tvLastReportDate);


        mOurSharedPreferences = new OurSharedPreferences(this);
        queue = Volley.newRequestQueue(this);

        setUserProfile();

    }

    private void setValuesToTextViews(){
        // set text views with values from shared preferences
        tvName.setText(mOurSharedPreferences.getSharedPreference("name"));
        tvHealthF.setText(mOurSharedPreferences.getSharedPreference("facility"));
        tvDistrict.setText(mOurSharedPreferences.getSharedPreference("district"));
        tvTotalReports.setText(mOurSharedPreferences.getSharedPreference("totalReports"));
        tvLastReportDate.setText(mOurSharedPreferences.getSharedPreference("lastReportDate"));
    }

    public void setUserProfile(){
        // get profile info
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // Grab values from json
                            name = response.get("name").toString();
                            facility = response.get("facility").toString();
                            facilityId = response.get("facilityId").toString();
                            district = response.get("district").toString();
                            lastReportingDate = response.get("lastReportingDate").toString();
                            totalReports = response.get("totalReports").toString();

                            // add json values to shared preference
                            mOurSharedPreferences.writeSharedPreference("name", name);
                            mOurSharedPreferences.writeSharedPreference("facility", facility);
                            mOurSharedPreferences.writeSharedPreference("facilityId", facilityId);
                            mOurSharedPreferences.writeSharedPreference("district", district);
                            mOurSharedPreferences.writeSharedPreference("lastReportDate", lastReportingDate);
                            mOurSharedPreferences.writeSharedPreference("totalReports", totalReports);

                            setValuesToTextViews();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.d(TAG, "Response: " + response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: ",  error);
            }
        }){

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Content-Type", "application/json");
                return params;
            }
        };

        // add queue to request
        queue.add(getRequest);

    }


}
