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
    EditText ipt_eligible_adults, ipt_children_and_adolo, ipt_adult_initiated,
            ipt_child_adolo_init;

    private LinearLayout ipt_linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ipt);

        mVolleyHelper = new VolleyHelper(this);

        ipt_linearLayout = (LinearLayout) findViewById(R.id.ipt_linearLayout);

        iptButton = (Button) findViewById(R.id.iptButton);
        ipt_eligible_adults = (EditText)findViewById(R.id.ipt_eligible_adults);
        ipt_children_and_adolo = (EditText)findViewById(R.id.ipt_children_and_adolo);
        ipt_adult_initiated = (EditText)findViewById(R.id.ipt_adults_initiated);
        ipt_child_adolo_init = (EditText)findViewById(R.id.ipt_child_adolo_init);

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
        final String ipt_adult_art = ipt_eligible_adults.getText().toString().trim();
        final String ipt_children_adolescent_art = ipt_children_and_adolo.getText().toString().trim();
        final String adult_art_client_on_ipt = ipt_adult_initiated.getText().toString().trim();
        final String children_adolescents_art_clients_on_ipt = ipt_child_adolo_init.getText().toString().trim();


        if (TextUtils.isEmpty(ipt_adult_art)){
            ipt_eligible_adults.setError(getString(R.string.err_msg_ipt_eligible_adult_art));
            ipt_eligible_adults.requestFocus();
            ipt_eligible_adults.setAnimation(animShake);
            ipt_eligible_adults.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }

        if (TextUtils.isEmpty(ipt_children_adolescent_art)){
            ipt_children_and_adolo.setError(getString(
                    R.string.err_msg_ipt_eligible_children_adolescent_art));
            ipt_children_and_adolo.requestFocus();
            ipt_children_and_adolo.setAnimation(animShake);
            ipt_children_and_adolo.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }

        if (TextUtils.isEmpty(adult_art_client_on_ipt)){
            ipt_adult_initiated.setError(getString(
                    R.string.err_msg_adult_art_client_initiated_on_ipt));
            ipt_adult_initiated.requestFocus();
            ipt_adult_initiated.setAnimation(animShake);
            ipt_adult_initiated.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }

        if (TextUtils.isEmpty(children_adolescents_art_clients_on_ipt)){
            ipt_child_adolo_init.setError(getString(
                    R.string.err_msg_children_adolescents_art_clients_initiated_on_ipt));
            ipt_child_adolo_init.requestFocus();
            ipt_child_adolo_init.setAnimation(animShake);
            ipt_child_adolo_init.startAnimation(animShake);
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
