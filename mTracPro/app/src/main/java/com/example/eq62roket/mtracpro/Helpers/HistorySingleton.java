package com.example.eq62roket.mtracpro.Helpers;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


/**
 * Created by eq62roket on 1/11/18.
 */


public class HistorySingleton {
    private static HistorySingleton mInstance;
    private RequestQueue requestQueue;
    private static Context mContext;

    private HistorySingleton(Context context){
        mContext = context;
        requestQueue = getRequestQue();
    }
    //Variable to return a requestQueue
    public RequestQueue getRequestQue(){
        if (requestQueue == null){
            requestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return requestQueue;
    }

    public static synchronized HistorySingleton getInstance(Context context){
        if(mInstance == null){
            mInstance = new HistorySingleton((context));
        }
        return mInstance;
    }

    //method to add each of the requests to a requestQueue
    public<T> void addToRequestQue(Request<T> request){
        requestQueue.add(request);
    }
}
