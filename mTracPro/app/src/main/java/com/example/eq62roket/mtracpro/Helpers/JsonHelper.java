package com.example.eq62roket.mtracpro.Helpers;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by probuse on 1/3/18.
 */

public class JsonHelper {

    private static final String TAG = "JsonHelper";

    private Context mContext;

    public JsonHelper(Context context){
        mContext = context;
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = mContext.getAssets().open("mappings.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public String getJsonValue(String id, String key){
        String value = null;
        try {
            JSONObject jsonObject = new JSONObject(loadJSONFromAsset());
            String identifier = jsonObject.getString(id);
            JSONObject valueObject = new JSONObject(identifier);
            value = valueObject.getString(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return value;
    }


}
