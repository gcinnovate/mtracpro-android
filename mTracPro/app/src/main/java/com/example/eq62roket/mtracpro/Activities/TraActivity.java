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

public class TraActivity extends AppCompatActivity {

    private VolleyHelper mVolleyHelper;

    Button traButton;
    EditText tra_act_tablets, tra_ors_sackets, tra_measles_vaccine, tra_amoxcilline, tra_depo_provera, tra_iv_artesunate,
            tra_fansidar, tra_rdt_malaria;
    Animation animShake;
    public Vibrator vib;

    private LinearLayout tra_linearLinear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tra);

        mVolleyHelper = new VolleyHelper(this);

        tra_linearLinear = (LinearLayout) findViewById(R.id.tra_linearLinear);

        traButton = (Button) findViewById(R.id.traButton);

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
        final String act_tablets = tra_act_tablets.getText().toString().trim();
        final String ors_sackets = tra_ors_sackets.getText().toString().trim();
        final String measles_vaccine = tra_measles_vaccine.getText().toString().trim();
        final String amoxcilline = tra_amoxcilline.getText().toString().trim();
        final String depo_provera = tra_depo_provera.getText().toString().trim();
        final String iv_artesunate = tra_iv_artesunate.getText().toString().trim();
        final String fansidar = tra_fansidar.getText().toString().trim();
        final String rdt_malaria = tra_rdt_malaria.getText().toString().trim();

        if (TextUtils.isEmpty(act_tablets)){
            tra_act_tablets.setError(getString(R.string.err_msg_tra_act_tablets_label));
            tra_act_tablets.requestFocus();
            tra_act_tablets.setAnimation(animShake);
            tra_act_tablets.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }

        if (TextUtils.isEmpty(ors_sackets)){
            tra_ors_sackets.setError(getString(R.string.err_msg_tra_ors_sackets_label));
            tra_ors_sackets.requestFocus();
            tra_ors_sackets.setAnimation(animShake);
            tra_ors_sackets.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }

        if (TextUtils.isEmpty(measles_vaccine)){
            tra_measles_vaccine.setError(getString(R.string.err_msg_tra_measles_vaccine_label));
            tra_measles_vaccine.requestFocus();
            tra_measles_vaccine.setAnimation(animShake);
            tra_measles_vaccine.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }

        if (TextUtils.isEmpty(amoxcilline)){
            tra_amoxcilline.setError(getString(R.string.err_msg_tra_amoxcilline_label));
            tra_amoxcilline.requestFocus();
            tra_amoxcilline.setAnimation(animShake);
            tra_amoxcilline.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }

        if (TextUtils.isEmpty(depo_provera)){
            tra_depo_provera.setError(getString(R.string.err_msg_tra_depo_provera_label));
            tra_depo_provera.requestFocus();
            tra_depo_provera.setAnimation(animShake);
            tra_depo_provera.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }

        if (TextUtils.isEmpty(iv_artesunate)){
            tra_iv_artesunate.setError(getString(R.string.err_msg_tra_iv_artesunate_label));
            tra_iv_artesunate.requestFocus();
            tra_iv_artesunate.setAnimation(animShake);
            tra_iv_artesunate.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }

        if (TextUtils.isEmpty(fansidar)){
            tra_fansidar.setError(getString(R.string.err_msg_tra_fansidar_label));
            tra_fansidar.requestFocus();
            tra_fansidar.setAnimation(animShake);
            tra_fansidar.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }

        if (TextUtils.isEmpty(rdt_malaria)){
            tra_rdt_malaria.setError(getString(R.string.err_msg_tra_rdt_malaria_label));
            tra_rdt_malaria.requestFocus();
            tra_rdt_malaria.setAnimation(animShake);
            tra_rdt_malaria.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }

        int ret = mVolleyHelper.sendData(tra_linearLinear, "tra");
        if (ret == 0){
            Intent traIntent = new Intent(TraActivity.this, MainActivity.class);
            startActivity(traIntent);
            finish();

        }
    }
}
