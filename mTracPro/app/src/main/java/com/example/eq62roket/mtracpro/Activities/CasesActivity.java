package com.example.eq62roket.mtracpro.Activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Vibrator;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.eq62roket.mtracpro.R;

public class CasesActivity extends AppCompatActivity {
    Button casesButton;
    EditText cases_ma, cases_dy, cases_sa, cases_af, cases_ae, cases_ab, cases_mg, cases_ch, cases_gw, cases_me,
            cases_nt, cases_vf, cases_pl, cases_tf, cases_yf, cases_tb;
    Animation animShake;
    public Vibrator vib;
    TextInputLayout cases_ma_label, cases_dy_label, cases_sa_label, cases_af_label,
            cases_ae_label, cases_ab_label, cases_mg_label, cases_ch_label, cases_gw_label, cases_yf_label,
            cases_me_label, cases_nt_label, cases_vf_label, cases_pl_label, cases_tf_label, cases_tb_label;
    LinearLayout cases_linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cases);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cases_ma_label = (TextInputLayout) findViewById(R.id.cases_ma_label);
        cases_dy_label = (TextInputLayout) findViewById(R.id.cases_dy_label);
        cases_sa_label = (TextInputLayout) findViewById(R.id.cases_sa_label);
        cases_af_label = (TextInputLayout) findViewById(R.id.cases_af_label);
        cases_ae_label = (TextInputLayout) findViewById(R.id.cases_ae_label);
        cases_ab_label = (TextInputLayout) findViewById(R.id.cases_ab_label);
        cases_mg_label = (TextInputLayout) findViewById(R.id.cases_mg_label);
        cases_ch_label = (TextInputLayout) findViewById(R.id.cases_ch_label);
        cases_gw_label = (TextInputLayout) findViewById(R.id.cases_gw_label);
        cases_yf_label = (TextInputLayout) findViewById(R.id.cases_yf_label);
        cases_me_label = (TextInputLayout) findViewById(R.id.cases_me_label);
        cases_nt_label = (TextInputLayout) findViewById(R.id.cases_nt_label);
        cases_vf_label = (TextInputLayout) findViewById(R.id.cases_vf_label);
        cases_pl_label = (TextInputLayout) findViewById(R.id.cases_pl_label);
        cases_tf_label = (TextInputLayout) findViewById(R.id.cases_tf_label);
        cases_tb_label = (TextInputLayout) findViewById(R.id.cases_tb_label);

        casesButton = (Button) findViewById(R.id.casesButton);
        casesButton.getBackground().setColorFilter(0xFF00FF00, PorterDuff.Mode.MULTIPLY);

        cases_ma = (EditText)findViewById(R.id.cases_ma);
        cases_dy = (EditText)findViewById(R.id.cases_dy);
        cases_sa = (EditText)findViewById(R.id.cases_sa);
        cases_af = (EditText)findViewById(R.id.cases_af);
        cases_ae = (EditText)findViewById(R.id.cases_ae);
        cases_ab = (EditText)findViewById(R.id.cases_ab);
        cases_mg = (EditText)findViewById(R.id.cases_mg);
        cases_ch = (EditText)findViewById(R.id.cases_ch);
        cases_gw = (EditText)findViewById(R.id.cases_gw);
        cases_me = (EditText)findViewById(R.id.cases_me);
        cases_nt = (EditText)findViewById(R.id.cases_nt);
        cases_vf = (EditText)findViewById(R.id.cases_vf);
        cases_pl = (EditText)findViewById(R.id.cases_pl);
        cases_tf = (EditText)findViewById(R.id.cases_tf);
        cases_yf = (EditText)findViewById(R.id.cases_yf);
        cases_tb = (EditText)findViewById(R.id.cases_tb);

        animShake = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.shake);
        vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        casesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!cases_ma.getText().toString().equals("") || !cases_dy.getText().toString().equals("")
                        || !cases_sa.getText().toString().equals("") || !cases_af.getText().toString().equals("")
                        || !cases_ae.getText().toString().equals("") || !cases_ae.getText().toString().equals("")
                        || !cases_ab.getText().toString().equals("") || !cases_mg.getText().toString().equals("")
                        || !cases_ch.getText().toString().equals("") || !cases_gw.getText().toString().equals("")
                        || !cases_me.getText().toString().equals("") || !cases_yf.getText().toString().equals("")
                        || !cases_nt.getText().toString().equals("") || !cases_tf.getText().toString().equals("")
                        || !cases_vf.getText().toString().equals("") || !cases_pl.getText().toString().equals("")
                        || !cases_tb.getText().toString().equals("")) {
                    Intent casesIntent = new Intent(CasesActivity.this, MainActivity.class);
                    startActivity(casesIntent);
                    finish();
                }
                else {
                    Toast.makeText(CasesActivity.this, "Please input amount before submitting", Toast.LENGTH_LONG).show();
                }
            }

        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id==android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}
