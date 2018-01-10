package com.example.eq62roket.mtracpro.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.eq62roket.mtracpro.Helpers.VolleyHelper;
import com.example.eq62roket.mtracpro.R;

public class DeathActivity extends AppCompatActivity {

    private VolleyHelper mVolleyHelper;

    Button deathButton;
    private LinearLayout death_linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_death);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // setting up VolleyHelper
        mVolleyHelper = new VolleyHelper(this);
        death_linearLayout = (LinearLayout)findViewById(R.id.death_linearLayout);


        deathButton = (Button) findViewById(R.id.deathButton);

        deathButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int ret = mVolleyHelper.sendData(death_linearLayout, "death");
                if (ret == 0){
                    Intent deathIntent = new Intent(DeathActivity.this, MainActivity.class);
                    startActivity(deathIntent);
                    finish();
                }
            }

        });

    }
}
