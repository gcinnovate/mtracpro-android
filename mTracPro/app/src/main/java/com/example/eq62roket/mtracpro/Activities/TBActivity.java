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


public class TBActivity extends AppCompatActivity {

    private VolleyHelper mVolleyHelper;
    Animation animShake;
    public Vibrator vib;
    Button tbButton;
    EditText tb_screened, tb_presumptive, tb_new_relapse_registered, tb_new_relapse_on_treatment,
            tb_bacter_registered, tb_genexpert_confirmed, tb_contacts_traced;

    private LinearLayout tb_linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tb);

        mVolleyHelper = new VolleyHelper(this);

        tb_linearLayout = (LinearLayout) findViewById(R.id.tb_linearLayout);

        tbButton = (Button) findViewById(R.id.tbButton);
        tb_screened = (EditText)findViewById(R.id.tb_screened);
        tb_presumptive = (EditText)findViewById(R.id.tb_presumptive);
        tb_new_relapse_registered = (EditText)findViewById(R.id.tb_new_relapse_registered);
        tb_new_relapse_on_treatment = (EditText)findViewById(R.id.tb_new_relapse_on_treatment);
        tb_bacter_registered = (EditText)findViewById(R.id.tb_bacter_registered);
        tb_genexpert_confirmed = (EditText)findViewById(R.id.tb_genexpert_confirmed);
        tb_contacts_traced = (EditText)findViewById(R.id.tb_contacts_traced);

        animShake = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.shake);
        vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        tbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
            }

        });

    }
    private void submitForm() {
        final String screened_clients = tb_screened.getText().toString().trim();
        final String presumptive_tb_cases = tb_presumptive.getText().toString().trim();
        final String relapse_diagnosed_registered = tb_new_relapse_registered.getText().toString().trim();
        final String relapse_tb_cases_started_on_treatment = tb_new_relapse_on_treatment.getText().toString().trim();
        final String registered_bacteriologically_tb_cases = tb_bacter_registered.getText().toString().trim();
        final String genexpert_bacteriologically_confirmed_tb_cases = tb_genexpert_confirmed.getText().toString().trim();
        final String tb_contacts_traced_and_screened = tb_contacts_traced.getText().toString().trim();


        if (TextUtils.isEmpty(screened_clients)){
            tb_screened.setError(getString(R.string.err_msg_clients_screened));
            tb_screened.requestFocus();
            tb_screened.setAnimation(animShake);
            tb_screened.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }

        if (TextUtils.isEmpty(presumptive_tb_cases)){
            tb_presumptive.setError(getString(
                    R.string.err_msg_presumptive_tb_cases_identified));
            tb_presumptive.requestFocus();
            tb_presumptive.setAnimation(animShake);
            tb_presumptive.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }

        if (TextUtils.isEmpty(relapse_diagnosed_registered)){
            tb_new_relapse_registered.setError(getString(
                    R.string.err_msg_new_relapse_diagnosed_registered));
            tb_new_relapse_registered.requestFocus();
            tb_new_relapse_registered.setAnimation(animShake);
            tb_new_relapse_registered.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }

        if (TextUtils.isEmpty(relapse_tb_cases_started_on_treatment)){
            tb_new_relapse_on_treatment.setError(getString(
                    R.string.err_msg_new_relapse_tb_cases_started_on_treatment));
            tb_new_relapse_on_treatment.requestFocus();
            tb_new_relapse_on_treatment.setAnimation(animShake);
            tb_new_relapse_on_treatment.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }

        if (TextUtils.isEmpty(registered_bacteriologically_tb_cases)){
            tb_bacter_registered.setError(getString(
                    R.string.err_msg_bacteriologically_tb_cases_registered));
            tb_bacter_registered.requestFocus();
            tb_bacter_registered.setAnimation(animShake);
            tb_bacter_registered.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }

        if (TextUtils.isEmpty(genexpert_bacteriologically_confirmed_tb_cases)){
            tb_genexpert_confirmed.setError(getString(
                    R.string.err_msg_bacteriologically_confirmed_tb_cases_with_genexpert));
            tb_genexpert_confirmed.requestFocus();
            tb_genexpert_confirmed.setAnimation(animShake);
            tb_genexpert_confirmed.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }

        if (TextUtils.isEmpty(tb_contacts_traced_and_screened)){
            tb_contacts_traced.setError(getString(
                    R.string.err_msg_no_of_tb_contacts_traced_and_screened));
            tb_contacts_traced.requestFocus();
            tb_contacts_traced.setAnimation(animShake);
            tb_contacts_traced.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }

        int ret = mVolleyHelper.sendData(tb_linearLayout, "tb");
        if (ret == 0){
            Intent tbIntent = new Intent(TBActivity.this, MainActivity.class);
            startActivity(tbIntent);
            finish();

        }
    }
}
