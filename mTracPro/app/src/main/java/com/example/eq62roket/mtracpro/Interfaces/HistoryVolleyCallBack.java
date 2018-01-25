package com.example.eq62roket.mtracpro.Interfaces;

import com.android.volley.VolleyError;
import com.example.eq62roket.mtracpro.Models.History;

import java.util.ArrayList;

/**
 * Created by probuse on 1/24/18.
 */

public interface HistoryVolleyCallBack {
    void onStart();
    void onSuccess(ArrayList<History> arrayList);
    void onFailure(VolleyError volleyError);
}
