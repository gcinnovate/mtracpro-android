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


public class AptActivity extends AppCompatActivity {

    private VolleyHelper mVolleyHelper;

    Animation animShake;
    public Vibrator vib;
    Button aptButton;
    EditText apt_opd_new, apt_opd_total, apt_emtct_expected, apt_emtct_missed;

    private LinearLayout apt_linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apt);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // setting up VolleyHelper
        mVolleyHelper = new VolleyHelper(this);

        apt_linearLayout = (LinearLayout) findViewById(R.id.apt_linearLayout);

        aptButton = (Button) findViewById(R.id.aptButton);
        apt_opd_new = (EditText)findViewById(R.id.apt_opd_new);
        apt_opd_total = (EditText)findViewById(R.id.apt_opd_total);
        apt_emtct_expected = (EditText)findViewById(R.id.apt_emtct_expected);
        apt_emtct_missed = (EditText)findViewById(R.id.apt_emtct_missed);

        animShake = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.shake);
        vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        aptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
            }

        });

    }
    private void submitForm() {
        final String opd_total = apt_opd_total.getText().toString().trim();
        final String opd_new = apt_opd_new.getText().toString().trim();
        final String emtct_expected = apt_emtct_expected.getText().toString().trim();
        final String emtct_missed = apt_emtct_missed.getText().toString().trim();


        if (TextUtils.isEmpty(opd_total)){
            apt_opd_total.setError(getString(R.string.err_msg_apt_opd_total_label));
            apt_opd_total.requestFocus();
            apt_opd_total.setAnimation(animShake);
            apt_opd_total.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }

        if (TextUtils.isEmpty(opd_new)){
            apt_opd_new.setError(getString(R.string.err_msg_apt_opd_new_label));
            apt_opd_new.requestFocus();
            apt_opd_new.setAnimation(animShake);
            apt_opd_new.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }

        if (TextUtils.isEmpty(emtct_expected)){
            apt_emtct_expected.setError(getString(R.string.err_msg_apt_emtct_expected_label));
            apt_emtct_expected.requestFocus();
            apt_emtct_expected.setAnimation(animShake);
            apt_emtct_expected.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }

        if (TextUtils.isEmpty(emtct_missed)){
            apt_emtct_missed.setError(getString(R.string.err_msg_apt_emtct_missed_label));
            apt_emtct_missed.requestFocus();
            apt_emtct_missed.setAnimation(animShake);
            apt_emtct_missed.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }

        int ret = mVolleyHelper.sendData(apt_linearLayout, "apt");
        if (ret == 0){
            Intent deathIntent = new Intent(AptActivity.this, MainActivity.class);
            startActivity(deathIntent);
            finish();
        }
    }
}
