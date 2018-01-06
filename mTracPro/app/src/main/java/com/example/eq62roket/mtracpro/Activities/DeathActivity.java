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

public class DeathActivity extends AppCompatActivity {

    private VollerHelper mVollerHelper;

    Button deathButton;
    EditText death_ma, death_dy, death_sa, death_af, death_ae, death_ab, death_mg, death_ch, death_gw, death_me,
            death_nt, death_vf, death_pl, death_tf, death_yf, death_tb;
    Animation animShake;
    public Vibrator vib;
    TextInputLayout death_ma_label, death_dy_label, death_sa_label, death_af_label,
            death_ae_label, death_ab_label, death_mg_label, death_ch_label, death_gw_label, death_yf_label,
            death_me_label, death_nt_label, death_vf_label, death_pl_label, death_tf_label, death_tb_label;
    LinearLayout death_linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_death);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // setting up VolleyHelper
        mVollerHelper = new VollerHelper(this);

        death_ma_label = (TextInputLayout) findViewById(R.id.death_ma_label);
        death_dy_label = (TextInputLayout) findViewById(R.id.death_dy_label);
        death_sa_label = (TextInputLayout) findViewById(R.id.death_sa_label);
        death_af_label = (TextInputLayout) findViewById(R.id.death_af_label);
        death_ae_label = (TextInputLayout) findViewById(R.id.death_ae_label);
        death_ab_label = (TextInputLayout) findViewById(R.id.death_ab_label);
        death_mg_label = (TextInputLayout) findViewById(R.id.death_mg_label);
        death_ch_label = (TextInputLayout) findViewById(R.id.death_ch_label);
        death_gw_label = (TextInputLayout) findViewById(R.id.death_gw_label);
        death_yf_label = (TextInputLayout) findViewById(R.id.death_yf_label);
        death_me_label = (TextInputLayout) findViewById(R.id.death_me_label);
        death_nt_label = (TextInputLayout) findViewById(R.id.death_nt_label);
        death_vf_label = (TextInputLayout) findViewById(R.id.death_vf_label);
        death_pl_label = (TextInputLayout) findViewById(R.id.death_pl_label);
        death_tf_label = (TextInputLayout) findViewById(R.id.death_tf_label);
        death_tb_label = (TextInputLayout) findViewById(R.id.death_tb_label);

        death_linearLayout = (LinearLayout) findViewById(R.id.death_linearLayout);

        deathButton = (Button) findViewById(R.id.deathButton);
        deathButton.getBackground().setColorFilter(0xFF00FF00, PorterDuff.Mode.MULTIPLY);

        death_ma = (EditText)findViewById(R.id.death_ma);
        death_dy = (EditText)findViewById(R.id.death_dy);
        death_sa = (EditText)findViewById(R.id.death_sa);
        death_af = (EditText)findViewById(R.id.death_af);
        death_ae = (EditText)findViewById(R.id.death_ae);
        death_ab = (EditText)findViewById(R.id.death_ab);
        death_mg = (EditText)findViewById(R.id.death_mg);
        death_ch = (EditText)findViewById(R.id.death_ch);
        death_gw = (EditText)findViewById(R.id.death_gw);
        death_me = (EditText)findViewById(R.id.death_me);
        death_nt = (EditText)findViewById(R.id.death_nt);
        death_vf = (EditText)findViewById(R.id.death_vf);
        death_pl = (EditText)findViewById(R.id.death_pl);
        death_tf = (EditText)findViewById(R.id.death_tf);
        death_yf = (EditText)findViewById(R.id.death_yf);
        death_tb = (EditText)findViewById(R.id.death_tb);

        animShake = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.shake);
        vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        deathButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!death_ma.getText().toString().equals("") || !death_dy.getText().toString().equals("")
                        || !death_sa.getText().toString().equals("") || !death_af.getText().toString().equals("")
                        || !death_ae.getText().toString().equals("") || !death_ae.getText().toString().equals("")
                        || !death_ab.getText().toString().equals("") || !death_mg.getText().toString().equals("")
                        || !death_ch.getText().toString().equals("") || !death_gw.getText().toString().equals("")
                        || !death_me.getText().toString().equals("") || !death_yf.getText().toString().equals("")
                        || !death_nt.getText().toString().equals("") || !death_tf.getText().toString().equals("")
                        || !death_vf.getText().toString().equals("") || !death_pl.getText().toString().equals("")
                        || !death_tb.getText().toString().equals("")) {

                    mVollerHelper.sendData(death_linearLayout);

                    Intent deathIntent = new Intent(DeathActivity.this, MainActivity.class);
                    startActivity(deathIntent);
                    finish();
                    Toast.makeText(DeathActivity.this, "Cases Form submitted successfully", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(DeathActivity.this, "Please input amount before submitting", Toast.LENGTH_LONG).show();
                }
            }

        });

    }
}
