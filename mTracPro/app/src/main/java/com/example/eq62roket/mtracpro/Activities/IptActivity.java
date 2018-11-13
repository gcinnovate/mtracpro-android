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


public class IptActivity extends AppCompatActivity {

    private VolleyHelper mVolleyHelper;

    Animation animShake;
    public Vibrator vib;
    Button iptButton;
    EditText ipt_eligible_adult_art, ipt_eligible_children_adolescent_art,
            adult_art_client_initiated_on_ipt, children_adolescents_art_clients_initiated_on_ipt;

    private LinearLayout ipt_linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ipt);

        mVolleyHelper = new VolleyHelper(this);

        ipt_linearLayout = (LinearLayout) findViewById(R.id.ipt_linearLayout);

        iptButton = (Button) findViewById(R.id.iptButton);
        ipt_eligible_adult_art = (EditText)findViewById(R.id.ipt_eligible_adult_art);
        ipt_eligible_children_adolescent_art = (EditText)findViewById(
                R.id.ipt_eligible_children_adolescent_art);
        adult_art_client_initiated_on_ipt = (EditText)findViewById(
                R.id.ipt_adult_art_client_initiated_on_ipt);
        children_adolescents_art_clients_initiated_on_ipt = (EditText)findViewById(
                R.id.ipt_children_adolescents_art_clients_initiated_on_ipt);

        animShake = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.shake);
        vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        iptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
            }

        });

    }
    private void submitForm() {
        final String ipt_adult_art = ipt_eligible_adult_art.getText().toString().trim();
        final String ipt_children_adolescent_art = ipt_eligible_children_adolescent_art.getText().toString().trim();
        final String adult_art_client_on_ipt = adult_art_client_initiated_on_ipt.getText().toString().trim();
        final String children_adolescents_art_clients_on_ipt = children_adolescents_art_clients_initiated_on_ipt.getText().toString().trim();


        if (TextUtils.isEmpty(ipt_adult_art)){
            ipt_eligible_adult_art.setError(getString(R.string.err_msg_ipt_eligible_adult_art));
            ipt_eligible_adult_art.requestFocus();
            ipt_eligible_adult_art.setAnimation(animShake);
            ipt_eligible_adult_art.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }

        if (TextUtils.isEmpty(ipt_children_adolescent_art)){
            ipt_eligible_children_adolescent_art.setError(getString(
                    R.string.err_msg_ipt_eligible_children_adolescent_art));
            ipt_eligible_children_adolescent_art.requestFocus();
            ipt_eligible_children_adolescent_art.setAnimation(animShake);
            ipt_eligible_children_adolescent_art.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }

        if (TextUtils.isEmpty(adult_art_client_on_ipt)){
            adult_art_client_initiated_on_ipt.setError(getString(
                    R.string.err_msg_adult_art_client_initiated_on_ipt));
            adult_art_client_initiated_on_ipt.requestFocus();
            adult_art_client_initiated_on_ipt.setAnimation(animShake);
            adult_art_client_initiated_on_ipt.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }

        if (TextUtils.isEmpty(children_adolescents_art_clients_on_ipt)){
            children_adolescents_art_clients_initiated_on_ipt.setError(getString(
                    R.string.err_msg_children_adolescents_art_clients_initiated_on_ipt));
            children_adolescents_art_clients_initiated_on_ipt.requestFocus();
            children_adolescents_art_clients_initiated_on_ipt.setAnimation(animShake);
            children_adolescents_art_clients_initiated_on_ipt.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }

        int ret = mVolleyHelper.sendData(ipt_linearLayout, "ipt");
        if (ret == 0){
            Intent traIntent = new Intent(IptActivity.this, MainActivity.class);
            startActivity(traIntent);
            finish();
        }
    }
}
