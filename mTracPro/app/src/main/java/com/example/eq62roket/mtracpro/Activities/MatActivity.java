package com.example.eq62roket.mtracpro.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

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

        final String suspected_malaria = mat_suspected_malaria.getText().toString().trim();
        final String rdt_tested = mat_rdt_tested.getText().toString().trim();
        final String rdt_positive = mat_rdt_positive.getText().toString().trim();
        final String microscopy_tested = mat_microscopy_tested.getText().toString().trim();
        final String microscopy_positive = mat_microscopy_positive.getText().toString().trim();
        final String not_tested_treated = mat_not_tested_treated.getText().toString().trim();
        final String rdt_pos_treated = mat_rdt_pos_treated.getText().toString().trim();
        final String microscopy_pos_treated = mat_microscopy_pos_treated.getText().toString().trim();
        final String rdt_neg_treated = mat_rdt_neg_treated.getText().toString().trim();
        final String microscopy_neg_treated = mat_microscopy_neg_treated.getText().toString().trim();


        if (TextUtils.isEmpty(suspected_malaria)){
            mat_suspected_malaria.setError(getString(R.string.err_msg_mat_suspected_malaria_label));
            mat_suspected_malaria.requestFocus();
            mat_suspected_malaria.setAnimation(animShake);
            mat_suspected_malaria.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }

        if (TextUtils.isEmpty(rdt_tested)){
            mat_rdt_tested.setError(getString(R.string.err_msg_mat_rdt_tested_label));
            mat_rdt_tested.requestFocus();
            mat_rdt_tested.setAnimation(animShake);
            mat_rdt_tested.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }

        if (TextUtils.isEmpty(rdt_positive)){
            mat_rdt_positive.setError(getString(R.string.err_msg_mat_rdt_positive_label));
            mat_rdt_positive.requestFocus();
            mat_rdt_positive.setAnimation(animShake);
            mat_rdt_positive.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }

        if (TextUtils.isEmpty(microscopy_tested)){
            mat_microscopy_tested.setError(getString(R.string.err_msg_mat_microscopy_tested_label));
            mat_microscopy_tested.requestFocus();
            mat_microscopy_tested.setAnimation(animShake);
            mat_microscopy_tested.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }

        if (TextUtils.isEmpty(microscopy_positive)){
            mat_microscopy_positive.setError(getString(R.string.err_msg_mat_microscopy_positive_label));
            mat_microscopy_positive.requestFocus();
            mat_microscopy_positive.setAnimation(animShake);
            mat_microscopy_positive.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }

        if (TextUtils.isEmpty(not_tested_treated)){
            mat_not_tested_treated.setError(getString(R.string.err_msg_mat_not_tested_treated_label));
            mat_not_tested_treated.requestFocus();
            mat_not_tested_treated.setAnimation(animShake);
            mat_not_tested_treated.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }

        if (TextUtils.isEmpty(rdt_pos_treated)){
            mat_rdt_pos_treated.setError(getString(R.string.err_msg_mat_rdt_pos_treated_label));
            mat_rdt_pos_treated.requestFocus();
            mat_rdt_pos_treated.setAnimation(animShake);
            mat_rdt_pos_treated.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }

        if (TextUtils.isEmpty(microscopy_pos_treated)){
            mat_microscopy_pos_treated.setError(getString(R.string.err_msg_mat_microscopy_pos_treated_label));
            mat_microscopy_pos_treated.requestFocus();
            mat_microscopy_pos_treated.setAnimation(animShake);
            mat_microscopy_pos_treated.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }


        if (TextUtils.isEmpty(rdt_neg_treated)){
            mat_rdt_neg_treated.setError(getString(R.string.err_msg_mat_rdt_neg_treated_label));
            mat_rdt_neg_treated.requestFocus();
            mat_rdt_neg_treated.setAnimation(animShake);
            mat_rdt_neg_treated.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }

        if (TextUtils.isEmpty(microscopy_neg_treated)){
            mat_microscopy_neg_treated.setError(getString(R.string.err_msg_mat_microscopy_neg_treated_label));
            mat_microscopy_neg_treated.requestFocus();
            mat_microscopy_neg_treated.setAnimation(animShake);
            mat_microscopy_neg_treated.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }

        int ret = mVolleyHelper.sendData(mat_linearLayout, "mat");
        if (ret == 0){
            Intent matIntent = new Intent(MatActivity.this, MainActivity.class);
            startActivity(matIntent);
            finish();
        }
    }
}
