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

public class MatActivity extends AppCompatActivity {

    private VollerHelper mVollerHelper;

    Button matButton;
    EditText mat_suspected_malaria, mat_rdt_tested, mat_rdt_positive, mat_microscopy_tested, mat_microscopy_positive, mat_not_tested_treated,
            mat_rdt_neg_treated, mat_rdt_pos_treated, mat_microscopy_neg_treated, mat_microscopy_pos_treated;
    Animation animShake;
    public Vibrator vib;
    TextInputLayout mat_microscopy_pos_treated_label, mat_microscopy_neg_treated_label, mat_rdt_pos_treated_label, mat_rdt_neg_treated_label,
            mat_not_tested_treated_label, mat_microscopy_positive_label, mat_microscopy_tested_label, mat_rdt_positive_label, mat_rdt_tested_label,
            mat_suspected_malaria_label;

    private LinearLayout mat_linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mat);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // setting up VolleyHelper
        mVollerHelper = new VollerHelper(this);

        mat_microscopy_pos_treated_label = (TextInputLayout) findViewById(R.id.mat_microscopy_pos_treated_label);
        mat_microscopy_neg_treated_label = (TextInputLayout) findViewById(R.id.mat_microscopy_neg_treated_label);
        mat_rdt_pos_treated_label = (TextInputLayout) findViewById(R.id.mat_rdt_pos_treated_label);
        mat_rdt_neg_treated_label = (TextInputLayout) findViewById(R.id.mat_rdt_neg_treated_label);
        mat_not_tested_treated_label = (TextInputLayout) findViewById(R.id.mat_not_tested_treated_label);
        mat_microscopy_positive_label = (TextInputLayout) findViewById(R.id.mat_microscopy_positive_label);
        mat_microscopy_tested_label = (TextInputLayout) findViewById(R.id.mat_microscopy_tested_label);
        mat_rdt_positive_label = (TextInputLayout) findViewById(R.id.mat_rdt_positive_label);
        mat_rdt_tested_label = (TextInputLayout) findViewById(R.id.mat_rdt_tested_label);
        mat_suspected_malaria_label = (TextInputLayout) findViewById(R.id.mat_suspected_malaria_label);

        mat_linearLayout = (LinearLayout) findViewById(R.id.mat_linearLayout);

        matButton = (Button) findViewById(R.id.matButton);
        matButton.getBackground().setColorFilter(0xFF00FF00, PorterDuff.Mode.MULTIPLY);

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

        if (!checkSupectedMalaria()) {
            mat_suspected_malaria.setAnimation(animShake);
            mat_suspected_malaria.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }
        if (!checkRDTTested()) {
            mat_rdt_tested.setAnimation(animShake);
            mat_rdt_tested.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }
        if (!checkRDTPositive()) {
            mat_rdt_positive.setAnimation(animShake);
            mat_rdt_positive.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }
        if (!checkMicroscopyTested()) {
            mat_microscopy_tested.setAnimation(animShake);
            mat_microscopy_tested.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }
        if (!checkMicroscopyPositive()) {
            mat_microscopy_positive.setAnimation(animShake);
            mat_microscopy_positive.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }
        if (!checkNotTestedCasesTreated()) {
            mat_not_tested_treated.setAnimation(animShake);
            mat_not_tested_treated.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }
        if (!checkRDTNegativeTreated()) {
            mat_rdt_neg_treated.setAnimation(animShake);
            mat_rdt_neg_treated.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }
        if (!checkRDTPositiveTreated()) {
            mat_rdt_pos_treated.setAnimation(animShake);
            mat_rdt_pos_treated.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }
        if (!checkMicroscopyNegativeTreated()) {
            mat_microscopy_neg_treated.setAnimation(animShake);
            mat_microscopy_neg_treated.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }
        if (!checkMicroscopyPositiveTreated()) {
            mat_microscopy_pos_treated.setAnimation(animShake);
            mat_microscopy_pos_treated.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }


        mat_suspected_malaria_label.setErrorEnabled(false);
        mat_rdt_tested_label.setErrorEnabled(false);
        mat_rdt_positive_label.setErrorEnabled(false);
        mat_microscopy_tested_label.setErrorEnabled(false);
        mat_microscopy_positive_label.setErrorEnabled(false);
        mat_not_tested_treated_label.setErrorEnabled(false);
        mat_rdt_pos_treated_label.setErrorEnabled(false);
        mat_microscopy_neg_treated_label.setErrorEnabled(false);
        mat_microscopy_pos_treated_label.setErrorEnabled(false);
        mat_rdt_neg_treated_label.setErrorEnabled(false);

        mVollerHelper.sendData(mat_linearLayout);

        Intent aptIntent = new Intent(MatActivity.this, MainActivity.class);
        startActivity(aptIntent);
        finish();
        Toast.makeText(getApplicationContext(), "Information has been submitted", Toast.LENGTH_SHORT).show();
    }

    private boolean checkSupectedMalaria() {
        if (mat_suspected_malaria.getText().toString().trim().isEmpty()) {

            mat_suspected_malaria_label.setErrorEnabled(true);
            mat_suspected_malaria_label.setError(getString(R.string.err_msg_mat_suspected_malaria_label));
            return false;
        }
        mat_suspected_malaria_label.setErrorEnabled(false);
        return true;
    }

    private boolean checkRDTTested() {
        if (mat_rdt_tested.getText().toString().trim().isEmpty()) {

            mat_rdt_tested_label.setErrorEnabled(true);
            mat_rdt_tested_label.setError(getString(R.string.err_msg_mat_rdt_tested_label));
            return false;
        }
        mat_rdt_tested_label.setErrorEnabled(false);
        return true;

    }

    private boolean checkRDTPositive() {
        if (mat_rdt_positive.getText().toString().trim().isEmpty()) {

            mat_rdt_positive_label.setErrorEnabled(true);
            mat_rdt_positive_label.setError(getString(R.string.err_msg_mat_rdt_positive_label));
            return false;
        }
        mat_rdt_positive_label.setErrorEnabled(false);
        return true;
    }
    private boolean checkMicroscopyTested() {
        if (mat_microscopy_tested.getText().toString().trim().isEmpty()) {

            mat_microscopy_tested_label.setErrorEnabled(true);
            mat_microscopy_tested_label.setError(getString(R.string.err_msg_mat_microscopy_tested_label));
            return false;
        }
        mat_microscopy_tested_label.setErrorEnabled(false);
        return true;
    }

    private boolean checkMicroscopyPositive() {
        if (mat_microscopy_positive.getText().toString().trim().isEmpty()) {

            mat_microscopy_positive_label.setErrorEnabled(true);
            mat_microscopy_positive_label.setError(getString(R.string.err_msg_mat_microscopy_positive_label));
            return false;
        }
        mat_microscopy_positive_label.setErrorEnabled(false);
        return true;

    }

    private boolean checkNotTestedCasesTreated() {
        if (mat_not_tested_treated.getText().toString().trim().isEmpty()) {

            mat_not_tested_treated_label.setErrorEnabled(true);
            mat_not_tested_treated_label.setError(getString(R.string.err_msg_mat_not_tested_treated_label));
            return false;
        }
        mat_not_tested_treated_label.setErrorEnabled(false);
        return true;
    }

    private boolean checkRDTNegativeTreated() {
        if (mat_rdt_neg_treated.getText().toString().trim().isEmpty()) {

            mat_rdt_neg_treated_label.setErrorEnabled(true);
            mat_rdt_neg_treated_label.setError(getString(R.string.err_msg_mat_rdt_neg_treated_label));
            return false;
        }
        mat_rdt_neg_treated_label.setErrorEnabled(false);
        return true;
    }

    private boolean checkRDTPositiveTreated() {
        if (mat_rdt_pos_treated.getText().toString().trim().isEmpty()) {

            mat_rdt_pos_treated_label.setErrorEnabled(true);
            mat_rdt_pos_treated_label.setError(getString(R.string.err_msg_mat_rdt_pos_treated_label));
            return false;
        }
        mat_rdt_pos_treated_label.setErrorEnabled(false);
        return true;

    }

    private boolean checkMicroscopyNegativeTreated() {
        if (mat_microscopy_neg_treated.getText().toString().trim().isEmpty()) {

            mat_microscopy_neg_treated_label.setErrorEnabled(true);
            mat_microscopy_neg_treated_label.setError(getString(R.string.err_msg_mat_microscopy_neg_treated_label));
            return false;
        }
        mat_microscopy_neg_treated_label.setErrorEnabled(false);
        return true;
    }

    private boolean checkMicroscopyPositiveTreated() {
        if (mat_microscopy_pos_treated.getText().toString().trim().isEmpty()) {

            mat_microscopy_pos_treated_label.setErrorEnabled(true);
            mat_microscopy_pos_treated_label.setError(getString(R.string.err_msg_mat_microscopy_pos_treated_label));
            return false;
        }
        mat_microscopy_pos_treated_label.setErrorEnabled(false);
        return true;
    }

}
