package com.example.eq62roket.mtracpro.Helpers;

import android.content.Context;
import android.preference.PreferenceManager;

/**
 * Created by probuse on 1/4/18.
 */

public class OurSharedPreferences {

    private Context mContext;
    private android.content.SharedPreferences preferences;

    public OurSharedPreferences(Context context){
        mContext = context;
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void writeSharedPreference(String key, String value){
        android.content.SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.apply();

    }

    public String getSharedPreference(String key){
        return preferences.getString(key, "No Value");
    }

    public void clearSharedPreference(){
        preferences.edit().clear().apply();
    }
}
