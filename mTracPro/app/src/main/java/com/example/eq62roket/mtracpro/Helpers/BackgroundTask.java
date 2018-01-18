package com.example.eq62roket.mtracpro.Helpers;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by eq62roket on 1/11/18.
 */

public class BackgroundTask {
    Context context;
    ArrayList<History> arrayList = new ArrayList<>();
    String json_url = "http://192.168.42.152/history.php";

    public BackgroundTask(Context context){
        this.context = context;
    }

    //Method to connect to the api and return the jsonarray
    public ArrayList<History> getHistoryList(){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, json_url, null,
                new Response.Listener<JSONArray>() {
                    public static final String TAG ="BackgroundTask";

                    @Override
                    public void onResponse(JSONArray response) {
                        int count = 0;
                        while (count<response.length()){
                            try {
                                JSONObject jsonObject = response.getJSONObject(count);
                                Log.d(TAG, "onResponse: " + jsonObject);
                                History history = new History(jsonObject.getString("Id"),
                                        jsonObject.getString("RawMsg"), jsonObject.getString("Detail"),
                                        jsonObject.getString("Date"));
                                arrayList.add(history);
                                count++;
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(context, "Network Unavailable. Please try again later", Toast.LENGTH_SHORT).show();
                volleyError.printStackTrace();
            }
        });

        HistorySingleton.getInstance(context).addToRequestQue(jsonArrayRequest);
        return arrayList;
    }

}
