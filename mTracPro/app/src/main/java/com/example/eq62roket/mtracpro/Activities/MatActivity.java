package com.example.eq62roket.mtracpro.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.eq62roket.mtracpro.Helpers.VolleyHelper;
import com.example.eq62roket.mtracpro.R;

public class MatActivity extends AppCompatActivity {

    private VolleyHelper mVolleyHelper;

    Button matButton;
    EditText mat_suspected_malaria, mat_rdt_tested, mat_rdt_positive, mat_microscopy_tested, mat_microscopy_positive, mat_not_tested_treated,
            mat_rdt_neg_treated, mat_rdt_pos_treated, mat_microscopy_neg_treated, mat_microscopy_pos_treated;
    Animation animShake;
    public Vibrator vib;

    private LinearLayout mat_linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mat);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // setting up VolleyHelper
        mVolleyHelper = new VolleyHelper(this);

        mat_linearLayout = (LinearLayout) findViewById(R.id.mat_linearLayout);

        matButton = (Button) findViewById(R.id.matButton);

        mat_suspected_malaria = (EditText)findViewById(R.id.mat_suspected_malaria);
        mat_rdt_tested = (EditText)findViewById(R.id.mat_rdt_tested);
        mat_rdt_positive = (EditText)findViewById(R.id.mat_rdt_positive);
        mat_microscopy_tested = (EditText)findViewById(R.id.mat_microscopy_tested);
        mat_microscopy_positive = (EditText)findViewById(R.id.mat_microscopy_positive);
        mat_not_tested_treated = (EditText)findViewById(R.id.mat_not_tested_treated);
        mat_rdt_pos_treated = (EditText)findViewById(R.id.mat_rdt_pos_treated);
        mat_microscopy_pos_treated = (EditText)findViewById(R.id.mat_microscopy_pos_treated);
        mat_rdt_neg_treated = (EditText)findViewById(R.id.mat_rdt_neg_treated);
        mat_microscopy_neg_treated = (EditText)findViewById(R.id.mat_microscopy_neg_treated);

        animShake = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.shake);
        vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        matButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
            }

        });
    }

    private void submitForm() {

        int ret = mVolleyHelper.sendData(mat_linearLayout);
        if (ret == 0){
            Intent matIntent = new Intent(MatActivity.this, MainActivity.class);
            startActivity(matIntent);
            finish();
        }
    }
}
