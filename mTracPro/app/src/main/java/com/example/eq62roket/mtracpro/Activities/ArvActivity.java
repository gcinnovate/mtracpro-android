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

public class ArvActivity extends AppCompatActivity {

    private VolleyHelper mVolleyHelper;

    Animation animShake;
    public Vibrator vib;
    Button arvButton;
    EditText arv_hiv_screening_test_kits, arv_emtct, arv_nevirapine;

    private LinearLayout arv_linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arv);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // setting up VolleyHelper
        mVolleyHelper = new VolleyHelper(this);

        arv_linearLayout = (LinearLayout) findViewById(R.id.arv_linearLayout);

        arv_hiv_screening_test_kits = (EditText)findViewById(R.id.arv_hiv_screening_test_kits);
        arv_emtct = (EditText)findViewById(R.id.arv_emtct);
        arv_nevirapine = (EditText)findViewById(R.id.arv_nevirapine);

        arvButton = (Button)findViewById(R.id.arvButton);

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

        final String hiv_screening_test_kits = arv_hiv_screening_test_kits.getText().toString().trim();
        final String emtct = arv_emtct.getText().toString().trim();
        final String nevirapine = arv_nevirapine.getText().toString().trim();

        if (TextUtils.isEmpty(hiv_screening_test_kits)){
            arv_hiv_screening_test_kits.setError(getString(R.string.err_msg_arv_hiv_screening_test_kits_label));
            arv_hiv_screening_test_kits.requestFocus();
            arv_hiv_screening_test_kits.setAnimation(animShake);
            arv_hiv_screening_test_kits.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }

        if (TextUtils.isEmpty(emtct)){
            arv_emtct.setError(getString(R.string.err_msg_arv_emtct_label));
            arv_emtct.requestFocus();
            arv_emtct.setAnimation(animShake);
            arv_emtct.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }

        if (TextUtils.isEmpty(nevirapine)){
            arv_nevirapine.setError(getString(R.string.err_msg_arv_nevirapine_label));
            arv_nevirapine.requestFocus();
            arv_nevirapine.setAnimation(animShake);
            arv_nevirapine.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }

        int ret = mVolleyHelper.sendData(arv_linearLayout);
        if (ret == 0){
            Intent aptIntent = new Intent(ArvActivity.this, MainActivity.class);
            startActivity(aptIntent);
            finish();
        }
    }


}
