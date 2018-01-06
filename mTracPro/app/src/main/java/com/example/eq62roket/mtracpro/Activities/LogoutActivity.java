package com.example.eq62roket.mtracpro.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.eq62roket.mtracpro.Helpers.OurSharedPreferences;
import com.example.eq62roket.mtracpro.R;

public class LogoutActivity extends AppCompatActivity {

    private Button btnLogout;
    private OurSharedPreferences mOurSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);

        btnLogout = (Button) findViewById(R.id.btnLogout);

        mOurSharedPreferences = new OurSharedPreferences(this);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
    }

    private void logout(){
        Intent intent = new Intent(LogoutActivity.this, LoginActivity.class);
        mOurSharedPreferences.clearSharedPreference();
        startActivity(intent);
        finish();
    }
}
