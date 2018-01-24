package com.example.eq62roket.mtracpro.Interfaces;

import com.android.volley.VolleyError;
import com.example.eq62roket.mtracpro.Helpers.Bulletin;

import java.util.ArrayList;

/**
 * Created by etwin on 1/24/18.
 */

public interface MohBulletinVolleyCallBack {
    void onSuccess(ArrayList<Bulletin> cBulletinArrayList);
    void onFailure(VolleyError volleyError);
}
