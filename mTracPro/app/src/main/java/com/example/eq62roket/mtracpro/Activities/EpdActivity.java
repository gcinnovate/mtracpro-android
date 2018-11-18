package com.example.eq62roket.mtracpro.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.eq62roket.mtracpro.Helpers.VolleyHelper;
import com.example.eq62roket.mtracpro.R;


public class EpdActivity extends AppCompatActivity {
    private VolleyHelper mVolleyHelper;

    Button epdButton;
    private LinearLayout epdLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_epd);

        // setting up VolleyHelper
        mVolleyHelper = new VolleyHelper(this);
        mVolleyHelper.setCurrentReportingWeek();

        epdLinearLayout = (LinearLayout)findViewById(R.id.epd_linearLayout);

        epdButton = (Button) findViewById(R.id.epcButton);

        epdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int ret = mVolleyHelper.sendData(epdLinearLayout, "epd");
                if (ret == 0){
                    Intent idsrDeathIntent = new Intent(EpdActivity.this, MainActivity.class);
                    startActivity(idsrDeathIntent);
                    finish();
                }
            }
        });
    }
}
