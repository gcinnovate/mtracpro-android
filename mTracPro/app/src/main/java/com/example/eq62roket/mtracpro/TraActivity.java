package com.example.eq62roket.mtracpro;

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
import android.widget.Toast;

public class TraActivity extends AppCompatActivity {
    Button traButton;
    EditText tra_act_tablets, tra_ors_sackets, tra_measles_vaccine, tra_amoxcilline, tra_depo_provera, tra_iv_artesunate,
            tra_fansidar, tra_rdt_malaria;
    Animation animShake;
    public Vibrator vib;
    TextInputLayout tra_rdt_malaria_label, tra_fansidar_label, tra_iv_artesunate_label, tra_depo_provera_label,
            tra_amoxcilline_label, tra_measles_vaccine_label, tra_ors_sackets_label, tra_act_tablets_label;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tra);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tra_rdt_malaria_label = (TextInputLayout) findViewById(R.id.tra_rdt_malaria_label);
        tra_fansidar_label = (TextInputLayout) findViewById(R.id.tra_fansidar_label);
        tra_iv_artesunate_label = (TextInputLayout) findViewById(R.id.tra_iv_artesunate_label);
        tra_depo_provera_label = (TextInputLayout) findViewById(R.id.tra_depo_provera_label);
        tra_amoxcilline_label = (TextInputLayout) findViewById(R.id.tra_amoxcilline_label);
        tra_measles_vaccine_label = (TextInputLayout) findViewById(R.id.tra_measles_vaccine_label);
        tra_ors_sackets_label = (TextInputLayout) findViewById(R.id.tra_ors_sackets_label);
        tra_act_tablets_label = (TextInputLayout) findViewById(R.id.tra_act_tablets_label);

        traButton = (Button) findViewById(R.id.traButton);
        traButton.getBackground().setColorFilter(0xFF00FF00, PorterDuff.Mode.MULTIPLY);

        tra_act_tablets = (EditText)findViewById(R.id.tra_act_tablets);
        tra_ors_sackets = (EditText)findViewById(R.id.tra_ors_sackets);
        tra_measles_vaccine = (EditText)findViewById(R.id.tra_measles_vaccine);
        tra_amoxcilline = (EditText)findViewById(R.id.tra_amoxcilline);
        tra_depo_provera = (EditText)findViewById(R.id.tra_depo_provera);
        tra_iv_artesunate = (EditText)findViewById(R.id.tra_iv_artesunate);
        tra_fansidar = (EditText)findViewById(R.id.tra_fansidar);
        tra_rdt_malaria = (EditText)findViewById(R.id.tra_rdt_malaria);

        animShake = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.shake);
        vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);


        traButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
            }

        });
    }

    private void submitForm() {

        if (!checkMeaslesVaccine()) {
            tra_measles_vaccine.setAnimation(animShake);
            tra_measles_vaccine.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }
        if (!checkAmoxicilline()) {
            tra_amoxcilline.setAnimation(animShake);
            tra_amoxcilline.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }
        if (!checkDepoProvera()) {
            tra_depo_provera.setAnimation(animShake);
            tra_depo_provera.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }
        if (!checkIVArtesunate()) {
            tra_iv_artesunate.setAnimation(animShake);
            tra_iv_artesunate.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }
        if (!checkFansidar()) {
            tra_fansidar.setAnimation(animShake);
            tra_fansidar.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }
        if (!checkACT()) {
            tra_act_tablets.setAnimation(animShake);
            tra_act_tablets.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }
        if (!checkORS()) {
            tra_ors_sackets.setAnimation(animShake);
            tra_ors_sackets.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }
        if (!checkRDT()) {
            tra_rdt_malaria.setAnimation(animShake);
            tra_rdt_malaria.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }


        tra_measles_vaccine_label.setErrorEnabled(false);
        tra_act_tablets_label.setErrorEnabled(false);
        tra_ors_sackets_label.setErrorEnabled(false);
        tra_amoxcilline_label.setErrorEnabled(false);
        tra_depo_provera_label.setErrorEnabled(false);
        tra_rdt_malaria_label.setErrorEnabled(false);
        tra_fansidar_label.setErrorEnabled(false);
        tra_iv_artesunate_label.setErrorEnabled(false);


        Intent aptIntent = new Intent(TraActivity.this, MainActivity.class);
        startActivity(aptIntent);
        finish();
        Toast.makeText(getApplicationContext(), "Information has been submitted", Toast.LENGTH_SHORT).show();
    }

    private boolean checkMeaslesVaccine() {
        if (tra_measles_vaccine.getText().toString().trim().isEmpty()) {

            tra_measles_vaccine_label.setErrorEnabled(true);
            tra_measles_vaccine_label.setError(getString(R.string.err_msg_tra_measles_vaccine_label));
            return false;
        }
        tra_measles_vaccine_label.setErrorEnabled(false);
        return true;
    }

    private boolean checkAmoxicilline() {
        if (tra_amoxcilline.getText().toString().trim().isEmpty()) {

            tra_amoxcilline_label.setErrorEnabled(true);
            tra_amoxcilline_label.setError(getString(R.string.err_msg_tra_amoxcilline_label));
            return false;
        }
        tra_amoxcilline_label.setErrorEnabled(false);
        return true;

    }

    private boolean checkDepoProvera() {
        if (tra_depo_provera.getText().toString().trim().isEmpty()) {

            tra_depo_provera_label.setErrorEnabled(true);
            tra_depo_provera_label.setError(getString(R.string.err_msg_tra_depo_provera_label));
            return false;
        }
        tra_depo_provera_label.setErrorEnabled(false);
        return true;
    }
    private boolean checkIVArtesunate() {
        if (tra_iv_artesunate.getText().toString().trim().isEmpty()) {

            tra_iv_artesunate_label.setErrorEnabled(true);
            tra_iv_artesunate_label.setError(getString(R.string.err_msg_tra_iv_artesunate_label));
            return false;
        }
        tra_iv_artesunate_label.setErrorEnabled(false);
        return true;
    }

    private boolean checkFansidar() {
        if (tra_fansidar.getText().toString().trim().isEmpty()) {

            tra_fansidar_label.setErrorEnabled(true);
            tra_fansidar_label.setError(getString(R.string.err_msg_tra_fansidar_label));
            return false;
        }
        tra_fansidar_label.setErrorEnabled(false);
        return true;
    }

    private boolean checkORS() {
        if (tra_ors_sackets.getText().toString().trim().isEmpty()) {

            tra_ors_sackets_label.setErrorEnabled(true);
            tra_ors_sackets_label.setError(getString(R.string.err_msg_tra_ors_sackets_label));
            return false;
        }
        tra_ors_sackets_label.setErrorEnabled(false);
        return true;

    }

    private boolean checkACT() {
        if (tra_act_tablets.getText().toString().trim().isEmpty()) {

            tra_act_tablets_label.setErrorEnabled(true);
            tra_act_tablets_label.setError(getString(R.string.err_msg_tra_act_tablets_label));
            return false;
        }
        tra_act_tablets_label.setErrorEnabled(false);
        return true;
    }
    private boolean checkRDT() {
        if (tra_rdt_malaria.getText().toString().trim().isEmpty()) {

            tra_rdt_malaria_label.setErrorEnabled(true);
            tra_rdt_malaria_label.setError(getString(R.string.err_msg_tra_rdt_malaria_label));
            return false;
        }
        tra_rdt_malaria_label.setErrorEnabled(false);
        return true;
    }


}
