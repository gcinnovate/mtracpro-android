package com.example.eq62roket.mtracpro.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.eq62roket.mtracpro.Helpers.VolleyHelper;
import com.example.eq62roket.mtracpro.R;


public class EpcActivity extends AppCompatActivity {
    private VolleyHelper mVolleyHelper;

    Button epcButton;
    private LinearLayout epcLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_epc);

        // setting up VolleyHelper
        mVolleyHelper = new VolleyHelper(this);
        mVolleyHelper.setCurrentReportingWeek();

        epcLinearLayout = (LinearLayout)findViewById(R.id.epc_linearLayout);

        epcButton = (Button) findViewById(R.id.epcButton);

        epcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int ret = mVolleyHelper.sendData(epcLinearLayout, "epc");
                if (ret == 0){
                    Intent idsrDeathIntent = new Intent(EpcActivity.this, MainActivity.class);
                    startActivity(idsrDeathIntent);
                    finish();
                }
            }
        });
    }
}
