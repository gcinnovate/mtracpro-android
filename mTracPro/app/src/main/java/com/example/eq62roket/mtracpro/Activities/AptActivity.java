package com.example.eq62roket.mtracpro.Activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
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

import com.example.eq62roket.mtracpro.Helpers.VollerHelper;
import com.example.eq62roket.mtracpro.R;


public class AptActivity extends AppCompatActivity {

    private VollerHelper mVollerHelper;

    Animation animShake;
    public Vibrator vib;
    Button aptButton;
    EditText apt_opd_new, apt_opd_total, apt_emtct_expected, apt_emtct_missed;
    TextInputLayout apt_opd_new_label, apt_opd_total_label, apt_emtct_expected_label, apt_emtct_missed_label;

    private LinearLayout apt_linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apt);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // setting up VolleyHelper
        mVollerHelper = new VollerHelper(this);

        apt_opd_new_label = (TextInputLayout) findViewById(R.id.apt_opd_new_label);
        apt_opd_total_label = (TextInputLayout) findViewById(R.id.apt_opd_total_label);
        apt_emtct_expected_label = (TextInputLayout) findViewById(R.id.apt_emtct_expected_label);
        apt_emtct_missed_label = (TextInputLayout) findViewById(R.id.apt_emtct_missed_label);

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

        if (!checkOPDNewAttendees()) {
            apt_opd_new.setAnimation(animShake);
            apt_opd_new.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }
        if (!checkOPDTotalAttendance()) {
            apt_opd_total.setAnimation(animShake);
            apt_opd_total.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }
        if (!checkExpectedeMTCTMothersOnAppt()) {
            apt_emtct_expected.setAnimation(animShake);
            apt_emtct_expected.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }
        if (!checkeMTCTMissedAppointments()) {
            apt_emtct_missed.setAnimation(animShake);
            apt_emtct_missed.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }
        apt_opd_new_label.setErrorEnabled(false);
        apt_opd_total_label.setErrorEnabled(false);
        apt_emtct_expected_label.setErrorEnabled(false);
        apt_emtct_missed_label.setErrorEnabled(false);

        mVollerHelper.sendData(apt_linearLayout);

        Intent aptIntent = new Intent(AptActivity.this, MainActivity.class);
        startActivity(aptIntent);
        finish();
        Toast.makeText(getApplicationContext(), "Information has been submitted", Toast.LENGTH_SHORT).show();
    }

    private boolean checkOPDNewAttendees() {
        if (apt_opd_new.getText().toString().trim().isEmpty()) {

            apt_opd_new_label.setErrorEnabled(true);
            apt_opd_new_label.setError(getString(R.string.err_msg_apt_opd_new_label));
            return false;
        }
        apt_opd_new_label.setErrorEnabled(false);
        return true;
    }

    private boolean checkOPDTotalAttendance() {
        if (apt_opd_total.getText().toString().trim().isEmpty()) {

            apt_opd_total_label.setErrorEnabled(true);
            apt_opd_total_label.setError(getString(R.string.err_msg_apt_opd_total_label));
            return false;
        }
        apt_opd_total_label.setErrorEnabled(false);
        return true;

    }

    private boolean checkExpectedeMTCTMothersOnAppt() {
        if (apt_emtct_expected.getText().toString().trim().isEmpty()) {

            apt_emtct_expected_label.setErrorEnabled(true);
            apt_emtct_expected_label.setError(getString(R.string.err_msg_apt_emtct_expected_label));
            return false;
        }
        apt_emtct_expected_label.setErrorEnabled(false);
        return true;
    }

    private boolean checkeMTCTMissedAppointments() {
        if (apt_emtct_missed.getText().toString().trim().isEmpty()) {

            apt_emtct_missed_label.setErrorEnabled(true);
            apt_emtct_missed_label.setError(getString(R.string.err_msg_apt_emtct_missed_label));
            return false;
        }
        apt_emtct_missed_label.setErrorEnabled(false);
        return true;

    }

}
