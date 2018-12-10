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
    EditText gp_samples, gp_rejected, gp_mtb_detected, gp_rifr,
            gp_errors, gp_modules, gp_cartridges;

    private LinearLayout gp_linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gp);

        mVolleyHelper = new VolleyHelper(this);

        gp_linearLayout = (LinearLayout) findViewById(R.id.gp_linearLayout);

        gpButton = (Button) findViewById(R.id.gpButton);
        gp_samples = (EditText)findViewById(R.id.gp_samples);
        gp_rejected = (EditText)findViewById(R.id.gp_rejected);
        gp_mtb_detected = (EditText)findViewById(R.id.gp_mtb_detected);
        gp_rifr = (EditText)findViewById(R.id.gp_rifr);
        gp_errors = (EditText)findViewById(R.id.gp_errors);
        gp_modules = (EditText)findViewById(R.id.gp_modules);
        gp_cartridges = (EditText)findViewById(R.id.gp_cartridges);

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
        final String tested_samples = gp_samples.getText().toString().trim();
        final String rejected_samples = gp_rejected.getText().toString().trim();
        final String mtb_detected = gp_mtb_detected.getText().toString().trim();
        final String total_rif = gp_rifr.getText().toString().trim();
        final String errors_invalid_results = gp_errors.getText().toString().trim();
        final String genexpert_modules_working = gp_modules.getText().toString().trim();
        final String cartridges_remaining = gp_cartridges.getText().toString().trim();


        if (TextUtils.isEmpty(tested_samples)){
            gp_samples.setError(getString(R.string.err_msg_no_of_samples_tested));
            gp_samples.requestFocus();
            gp_samples.setAnimation(animShake);
            gp_samples.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }

        if (TextUtils.isEmpty(rejected_samples)){
            gp_rejected.setError(getString(
                    R.string.err_msg_samples_rejected));
            gp_rejected.requestFocus();
            gp_rejected.setAnimation(animShake);
            gp_rejected.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }

        if (TextUtils.isEmpty(mtb_detected)){
            gp_mtb_detected.setError(getString(
                    R.string.err_msg_total_mtb_detected));
            gp_mtb_detected.requestFocus();
            gp_mtb_detected.setAnimation(animShake);
            gp_mtb_detected.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }

        if (TextUtils.isEmpty(total_rif)){
            gp_rifr.setError(getString(
                    R.string.err_msg_total_no_of_rif_r));
            gp_rifr.requestFocus();
            gp_rifr.setAnimation(animShake);
            gp_rifr.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }

        if (TextUtils.isEmpty(errors_invalid_results)){
            gp_errors.setError(getString(
                    R.string.err_msg_no_of_errors_invalid_results));
            gp_errors.requestFocus();
            gp_errors.setAnimation(animShake);
            gp_errors.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }

        if (TextUtils.isEmpty(genexpert_modules_working)){
            gp_modules.setError(getString(
                    R.string.err_msg_no_of_genexpert_modules_working));
            gp_modules.requestFocus();
            gp_modules.setAnimation(animShake);
            gp_modules.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }

        if (TextUtils.isEmpty(cartridges_remaining)){
            gp_cartridges.setError(getString(
                    R.string.err_msg_no_of_cartridges_remaining));
            gp_cartridges.requestFocus();
            gp_cartridges.setAnimation(animShake);
            gp_cartridges.startAnimation(animShake);
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
