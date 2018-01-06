package com.example.eq62roket.mtracpro.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.eq62roket.mtracpro.Helpers.OurSharedPreferences;
import com.example.eq62roket.mtracpro.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private final static String TAG = "LoginActivity";
    private final String url = "http://192.168.0.100:8080/api/v1/reporter";

    private RequestQueue queue;
    private OurSharedPreferences mOurSharedPreferences;

    private EditText edPhoneNumber;
    private Button btnLogin;

    private String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_login);

        mOurSharedPreferences = new OurSharedPreferences(this);
        queue = Volley.newRequestQueue(this);

        if (mOurSharedPreferences.getSharedPreference("loggedIn").equals("loggedIn")){
            // start main activity
            startMainActivity();
        }

        edPhoneNumber = (EditText) findViewById(R.id.edPhoneNumber);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    private void login(){

        if ( !edPhoneNumber.getText().toString().equals("") &&
                edPhoneNumber.getText().length() == 10 &&
                edPhoneNumber.getText().toString().startsWith("07")){

            JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                // Grab Phone number from json
                                 phoneNumber = response.get("phoneNumber").toString();

                                if (edPhoneNumber.getText().toString().equals(phoneNumber)){
                                    // add phone number to shared preference and set user as loggedIn
                                    mOurSharedPreferences.writeSharedPreference("phoneNumber", phoneNumber);
                                    mOurSharedPreferences.writeSharedPreference("loggedIn", "loggedIn");

                                    //start Main Activity
                                    startMainActivity();

                                } else {
                                    edPhoneNumber.setText("");
                                    Toast.makeText(LoginActivity.this, "You are not a registered user", Toast.LENGTH_SHORT).show();
                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            Log.d(TAG, "Response: " + response.toString());
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d(TAG, "onErrorResponse: ",  error);
                    if (error != null){
                        edPhoneNumber.setText("");

                        Toast.makeText(LoginActivity.this, "Network error occured, Try again", Toast.LENGTH_SHORT).show();
                    }
                }
            }){

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("Content-Type", "application/json");
                    return params;
                }
            };

            // add queue to request
            queue.add(getRequest);

        }else {
            edPhoneNumber.setText("");
            Toast.makeText(LoginActivity.this, "Number not valid, Try again", Toast.LENGTH_SHORT).show();
        }
    }

    public void startMainActivity(){
        // login user
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
