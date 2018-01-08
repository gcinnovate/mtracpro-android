package com.example.eq62roket.mtracpro;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class DeathActivity extends AppCompatActivity {
    Button deathButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_death);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        deathButton = (Button) findViewById(R.id.deathButton);

        deathButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout death_linearLayout = (LinearLayout)findViewById(R.id.death_linearLayout);
                for (int i = 0; i < death_linearLayout.getChildCount(); i++ ){
                    View view1 = death_linearLayout.getChildAt(i);
                    if (view1 instanceof TextInputLayout){
                        EditText editText = ((TextInputLayout) view1).getEditText();
                        if (!editText.getText().toString().equals("")){
                            Intent deathIntent = new Intent(DeathActivity.this, MainActivity.class);
                            startActivity(deathIntent);
                            finish();
                        }
                        else {
                            Toast.makeText(DeathActivity.this, "Please input a death before submitting", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }

        });

    }
}
