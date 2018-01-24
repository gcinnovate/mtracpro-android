package com.example.eq62roket.mtracpro.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;

import com.android.volley.VolleyError;
import com.example.eq62roket.mtracpro.Adapters.RecyclerAdapter;
import com.example.eq62roket.mtracpro.Helpers.BackgroundTask;
import com.example.eq62roket.mtracpro.Helpers.History;
import com.example.eq62roket.mtracpro.Interfaces.VolleyCallBack;
import com.example.eq62roket.mtracpro.R;
import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {
    private static final String TAG = "HistoryActivity";
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    public SearchView search;
    ArrayList<History> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        search = (SearchView)findViewById(R.id.action_search);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        //To improve performance
        recyclerView.setHasFixedSize(true);


        //Initialising the adapter
        BackgroundTask backgroundTask = new BackgroundTask(HistoryActivity.this);
        arrayList = backgroundTask.getHistoryList(new VolleyCallBack() {
            @Override
            public void onStart() {
                Log.d(TAG, "Call back started:>>>>>>>>>> " );
            }

            @Override
            public void onSuccess(ArrayList<History> historyArrayList) {
                Log.d(TAG, "ArrayList in onCallback: " + historyArrayList);

            }

            @Override
            public void onFailure(VolleyError volleyError) {
                Log.d(TAG, "onFailure in Callback: " + volleyError);
            }
        });
        adapter = new RecyclerAdapter(arrayList, HistoryActivity.this);
        recyclerView.setAdapter(adapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
