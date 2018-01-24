package com.example.eq62roket.mtracpro.Interfaces;

import com.android.volley.VolleyError;
import com.example.eq62roket.mtracpro.Helpers.History;

import java.util.ArrayList;

/**
 * Created by probuse on 1/24/18.
 */

public interface VolleyCallBack {
    void onStart();
    void onSuccess(ArrayList<History> historyArrayList);
    void onFailure(VolleyError volleyError);
}
