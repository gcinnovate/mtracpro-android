package com.example.eq62roket.mtracpro.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.eq62roket.mtracpro.Helpers.VolleyHelper;
import com.example.eq62roket.mtracpro.R;

public class CasesActivity extends AppCompatActivity {

    private VolleyHelper mVolleyHelper;

    Button casesButton;
    private LinearLayout cases_linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cases);

        mVolleyHelper = new VolleyHelper(this);
        mVolleyHelper.setCurrentReportingWeek();

        casesButton = (Button) findViewById(R.id.casesButton);
        cases_linearLayout = (LinearLayout) findViewById(R.id.cases_linearLayout);

        casesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int ret = mVolleyHelper.sendData(cases_linearLayout, "cases");
                if (ret == 0){
                    Intent deathIntent = new Intent(CasesActivity.this, MainActivity.class);
                    startActivity(deathIntent);
                    finish();
                }
            }

        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id==android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

}
