package com.example.eq62roket.mtracpro;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class CasesActivity extends AppCompatActivity {
    Button casesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cases);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        casesButton = (Button) findViewById(R.id.casesButton);

        casesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout cases_linearLayout = (LinearLayout)findViewById(R.id.cases_linearLayout);
                for (int i = 0; i < cases_linearLayout.getChildCount(); i++ ){
                    View view1 = cases_linearLayout.getChildAt(i);
                    if (view1 instanceof TextInputLayout){
                        EditText editText = ((TextInputLayout) view1).getEditText();
                        if (!editText.getText().toString().equals("")){
                            Intent deathIntent = new Intent(CasesActivity.this, MainActivity.class);
                            startActivity(deathIntent);
                            finish();
                        }
                        else {
                            Toast.makeText(CasesActivity.this, "Please input a case before submitting", Toast.LENGTH_LONG).show();
                        }
                    }
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
