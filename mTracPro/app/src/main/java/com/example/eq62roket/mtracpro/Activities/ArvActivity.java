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

public class ArvActivity extends AppCompatActivity {

    private VolleyHelper mVolleyHelper;

    Animation animShake;
    public Vibrator vib;
    Button arvButton;
    EditText arv_hiv_screening_test_kits, arv_emtct, arv_nevirapine;
    TextInputLayout arv_hiv_screening_test_kits_label, arv_emtct_label, arv_nevirapine_label;

    private LinearLayout arv_linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arv);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // setting up VolleyHelper
        mVolleyHelper = new VolleyHelper(this);

        arv_hiv_screening_test_kits_label = (TextInputLayout) findViewById(R.id.arv_hiv_screening_test_kits_label);
        arv_emtct_label = (TextInputLayout) findViewById(R.id.arv_emtct_label);
        arv_nevirapine_label = (TextInputLayout) findViewById(R.id.arv_nevirapine_label);

        arv_linearLayout = (LinearLayout) findViewById(R.id.arv_linearLayout);

        arvButton = (Button) findViewById(R.id.arvButton);

        arv_hiv_screening_test_kits = (EditText)findViewById(R.id.arv_hiv_screening_test_kits);
        arv_emtct = (EditText)findViewById(R.id.arv_emtct);
        arv_nevirapine = (EditText)findViewById(R.id.arv_nevirapine);

        animShake = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.shake);
        vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        arvButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();

            }

        });

    }
    private void submitForm() {

        if (!checkHIVScreeningTestKits()) {
            arv_hiv_screening_test_kits.setAnimation(animShake);
            arv_hiv_screening_test_kits.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }
        if (!checkARVs()) {
            arv_emtct.setAnimation(animShake);
            arv_emtct.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }
        if (!checkNevirapineTherapy()) {
            arv_nevirapine.setAnimation(animShake);
            arv_nevirapine.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }

        arv_hiv_screening_test_kits_label.setErrorEnabled(false);
        arv_emtct_label.setErrorEnabled(false);
        arv_nevirapine_label.setErrorEnabled(false);

        mVolleyHelper.sendData(arv_linearLayout, "arv");

        Intent aptIntent = new Intent(ArvActivity.this, MainActivity.class);
        startActivity(aptIntent);
        finish();
        Toast.makeText(getApplicationContext(), "Information has been submitted", Toast.LENGTH_SHORT).show();
    }

    private boolean checkHIVScreeningTestKits() {
        if (arv_hiv_screening_test_kits.getText().toString().trim().isEmpty()) {

            arv_hiv_screening_test_kits_label.setErrorEnabled(true);
            arv_hiv_screening_test_kits_label.setError(getString(R.string.err_msg_arv_hiv_screening_test_kits_label));
            return false;
        }
        arv_hiv_screening_test_kits_label.setErrorEnabled(false);
        return true;
    }

    private boolean checkARVs() {
        if (arv_emtct.getText().toString().trim().isEmpty()) {

            arv_emtct_label.setErrorEnabled(true);
            arv_emtct_label.setError(getString(R.string.err_msg_arv_emtct_label));
            return false;
        }
        arv_emtct_label.setErrorEnabled(false);
        return true;

    }

    private boolean checkNevirapineTherapy() {
        if (arv_nevirapine.getText().toString().trim().isEmpty()) {

            arv_nevirapine_label.setErrorEnabled(true);
            arv_nevirapine_label.setError(getString(R.string.err_msg_arv_nevirapine_label));
            return false;
        }
        arv_nevirapine_label.setErrorEnabled(false);
        return true;
    }

}
