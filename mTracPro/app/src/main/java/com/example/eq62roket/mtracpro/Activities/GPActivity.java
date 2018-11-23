package com.example.eq62roket.mtracpro.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.eq62roket.mtracpro.Helpers.VolleyHelper;
import com.example.eq62roket.mtracpro.R;


public class GPActivity extends AppCompatActivity {

    private VolleyHelper mVolleyHelper;

    Animation animShake;
    public Vibrator vib;
    Button gpButton;
    EditText samples_tested, samples_rejected, total_mtb_detected, total_no_of_rif_r,
            no_of_errors_invalid_results, no_of_genexpert_modules_working,
            no_of_cartridges_remaining;

    private LinearLayout gp_linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gp);

        mVolleyHelper = new VolleyHelper(this);

        gp_linearLayout = (LinearLayout) findViewById(R.id.gp_linearLayout);

        gpButton = (Button) findViewById(R.id.gpButton);
        samples_tested = (EditText)findViewById(R.id.gp_samples_tested);
        samples_rejected = (EditText)findViewById(R.id.gp_samples_rejected);
        total_mtb_detected = (EditText)findViewById(R.id.gp_total_mtb_detected);
        total_no_of_rif_r = (EditText)findViewById(R.id.gp_total_no_of_rif_r);
        no_of_errors_invalid_results = (EditText)findViewById(R.id.gp_no_of_errors_invalid_results);
        no_of_genexpert_modules_working = (EditText)findViewById(R.id.gp_no_of_genexpert_modules_working);
        no_of_cartridges_remaining = (EditText)findViewById(R.id.gp_no_of_cartridges_remaining);

        animShake = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.shake);
        vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        gpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
            }

        });

    }
    private void submitForm() {
        final String tested_samples = samples_tested.getText().toString().trim();
        final String rejected_samples = samples_rejected.getText().toString().trim();
        final String mtb_detected = total_mtb_detected.getText().toString().trim();
        final String total_rif = total_no_of_rif_r.getText().toString().trim();
        final String errors_invalid_results = no_of_errors_invalid_results.getText().toString().trim();
        final String genexpert_modules_working = no_of_genexpert_modules_working.getText().toString().trim();
        final String cartridges_remaining = no_of_cartridges_remaining.getText().toString().trim();


        if (TextUtils.isEmpty(tested_samples)){
            samples_tested.setError(getString(R.string.err_msg_no_of_samples_tested));
            samples_tested.requestFocus();
            samples_tested.setAnimation(animShake);
            samples_tested.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }

        if (TextUtils.isEmpty(rejected_samples)){
            samples_rejected.setError(getString(
                    R.string.err_msg_samples_rejected));
            samples_rejected.requestFocus();
            samples_rejected.setAnimation(animShake);
            samples_rejected.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }

        if (TextUtils.isEmpty(mtb_detected)){
            total_mtb_detected.setError(getString(
                    R.string.err_msg_total_mtb_detected));
            total_mtb_detected.requestFocus();
            total_mtb_detected.setAnimation(animShake);
            total_mtb_detected.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }

        if (TextUtils.isEmpty(total_rif)){
            total_no_of_rif_r.setError(getString(
                    R.string.err_msg_total_no_of_rif_r));
            total_no_of_rif_r.requestFocus();
            total_no_of_rif_r.setAnimation(animShake);
            total_no_of_rif_r.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }

        if (TextUtils.isEmpty(errors_invalid_results)){
            no_of_errors_invalid_results.setError(getString(
                    R.string.err_msg_no_of_errors_invalid_results));
            no_of_errors_invalid_results.requestFocus();
            no_of_errors_invalid_results.setAnimation(animShake);
            no_of_errors_invalid_results.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }

        if (TextUtils.isEmpty(genexpert_modules_working)){
            no_of_genexpert_modules_working.setError(getString(
                    R.string.err_msg_no_of_genexpert_modules_working));
            no_of_genexpert_modules_working.requestFocus();
            no_of_genexpert_modules_working.setAnimation(animShake);
            no_of_genexpert_modules_working.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }

        if (TextUtils.isEmpty(cartridges_remaining)){
            no_of_cartridges_remaining.setError(getString(
                    R.string.err_msg_no_of_cartridges_remaining));
            no_of_cartridges_remaining.requestFocus();
            no_of_cartridges_remaining.setAnimation(animShake);
            no_of_cartridges_remaining.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }

        int ret = mVolleyHelper.sendData(gp_linearLayout, "gp");
        if (ret == 0){
            Intent traIntent = new Intent(GPActivity.this, MainActivity.class);
            startActivity(traIntent);
            finish();
        }
    }
}
