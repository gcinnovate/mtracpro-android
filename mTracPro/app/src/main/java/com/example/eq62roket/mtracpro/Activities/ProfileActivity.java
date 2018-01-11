package com.example.eq62roket.mtracpro.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.eq62roket.mtracpro.Helpers.OurSharedPreferences;
import com.example.eq62roket.mtracpro.R;

public class ProfileActivity extends AppCompatActivity {

    private final static String TAG = "HomeActivity";
    private final String REPORTER_API_URI = "http://mtracpro.gcinnovate.com/api/v1/reporter/";

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

        mOurSharedPreferences = new OurSharedPreferences(this);
        queue = Volley.newRequestQueue(this);

        setValuesToTextViews();

    }

    private void setValuesToTextViews(){
        // set text views with values from shared preferences
        tvName.setText(mOurSharedPreferences.getSharedPreference("name"));
        tvHealthF.setText(mOurSharedPreferences.getSharedPreference("facility"));
        tvDistrict.setText(mOurSharedPreferences.getSharedPreference("district"));
        tvTotalReports.setText(mOurSharedPreferences.getSharedPreference("totalReports"));
        //tvLastReportDate.setText(mOurSharedPreferences.getSharedPreference("lastReportDate"));
    }

}
