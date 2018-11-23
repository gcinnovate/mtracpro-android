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
    EditText clients_screened, presumptive_tb_cases_identified, new_relapse_diagnosed_registered,
            new_relapse_tb_cases_started_on_treatment, bacteriologically_tb_cases_registered,
            bacteriologically_confirmed_tb_cases_with_genexpert, no_of_tb_contacts_traced_and_screened;

    private LinearLayout tb_linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tb);

        mVolleyHelper = new VolleyHelper(this);

        tb_linearLayout = (LinearLayout) findViewById(R.id.tb_linearLayout);

        tbButton = (Button) findViewById(R.id.tbButton);
        clients_screened = (EditText)findViewById(R.id.tb_clients_screened);
        presumptive_tb_cases_identified = (EditText)findViewById(R.id.tb_presumptive_tb_cases_identified);
        new_relapse_diagnosed_registered = (EditText)findViewById(R.id.tb_new_relapse_diagnosed_registered);
        new_relapse_tb_cases_started_on_treatment = (EditText)findViewById(
                R.id.tb_new_relapse_tb_cases_started_on_treatment);
        bacteriologically_tb_cases_registered = (EditText)findViewById(
                R.id.tb_bacteriologically_tb_cases_registered);
        bacteriologically_confirmed_tb_cases_with_genexpert = (EditText)findViewById(
                R.id.tb_bacteriologically_confirmed_tb_cases_with_genexpert);
        no_of_tb_contacts_traced_and_screened = (EditText)findViewById(
                R.id.tb_no_of_tb_contacts_traced_and_screened);

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
        final String screened_clients = clients_screened.getText().toString().trim();
        final String presumptive_tb_cases = presumptive_tb_cases_identified.getText().toString().trim();
        final String relapse_diagnosed_registered = new_relapse_diagnosed_registered.getText().toString().trim();
        final String relapse_tb_cases_started_on_treatment = new_relapse_tb_cases_started_on_treatment.getText().toString().trim();
        final String registered_bacteriologically_tb_cases = bacteriologically_tb_cases_registered.getText().toString().trim();
        final String genexpert_bacteriologically_confirmed_tb_cases = bacteriologically_confirmed_tb_cases_with_genexpert.getText().toString().trim();
        final String tb_contacts_traced_and_screened = no_of_tb_contacts_traced_and_screened.getText().toString().trim();


        if (TextUtils.isEmpty(screened_clients)){
            clients_screened.setError(getString(R.string.err_msg_clients_screened));
            clients_screened.requestFocus();
            clients_screened.setAnimation(animShake);
            clients_screened.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }

        if (TextUtils.isEmpty(presumptive_tb_cases)){
            presumptive_tb_cases_identified.setError(getString(
                    R.string.err_msg_presumptive_tb_cases_identified));
            presumptive_tb_cases_identified.requestFocus();
            presumptive_tb_cases_identified.setAnimation(animShake);
            presumptive_tb_cases_identified.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }

        if (TextUtils.isEmpty(relapse_diagnosed_registered)){
            new_relapse_diagnosed_registered.setError(getString(
                    R.string.err_msg_new_relapse_diagnosed_registered));
            new_relapse_diagnosed_registered.requestFocus();
            new_relapse_diagnosed_registered.setAnimation(animShake);
            new_relapse_diagnosed_registered.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }

        if (TextUtils.isEmpty(relapse_tb_cases_started_on_treatment)){
            new_relapse_tb_cases_started_on_treatment.setError(getString(
                    R.string.err_msg_new_relapse_tb_cases_started_on_treatment));
            new_relapse_tb_cases_started_on_treatment.requestFocus();
            new_relapse_tb_cases_started_on_treatment.setAnimation(animShake);
            new_relapse_tb_cases_started_on_treatment.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }

        if (TextUtils.isEmpty(registered_bacteriologically_tb_cases)){
            bacteriologically_tb_cases_registered.setError(getString(
                    R.string.err_msg_bacteriologically_tb_cases_registered));
            bacteriologically_tb_cases_registered.requestFocus();
            bacteriologically_tb_cases_registered.setAnimation(animShake);
            bacteriologically_tb_cases_registered.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }

        if (TextUtils.isEmpty(genexpert_bacteriologically_confirmed_tb_cases)){
            bacteriologically_confirmed_tb_cases_with_genexpert.setError(getString(
                    R.string.err_msg_bacteriologically_confirmed_tb_cases_with_genexpert));
            bacteriologically_confirmed_tb_cases_with_genexpert.requestFocus();
            bacteriologically_confirmed_tb_cases_with_genexpert.setAnimation(animShake);
            bacteriologically_confirmed_tb_cases_with_genexpert.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }

        if (TextUtils.isEmpty(tb_contacts_traced_and_screened)){
            no_of_tb_contacts_traced_and_screened.setError(getString(
                    R.string.err_msg_no_of_tb_contacts_traced_and_screened));
            no_of_tb_contacts_traced_and_screened.requestFocus();
            no_of_tb_contacts_traced_and_screened.setAnimation(animShake);
            no_of_tb_contacts_traced_and_screened.startAnimation(animShake);
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
