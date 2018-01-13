package com.example.eq62roket.mtracpro.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;

import com.example.eq62roket.mtracpro.Adapters.RecyclerAdapter;
import com.example.eq62roket.mtracpro.Helpers.BackgroundTask;
import com.example.eq62roket.mtracpro.Helpers.History;
import com.example.eq62roket.mtracpro.R;
import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {
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
        arrayList = backgroundTask.getHistoryList();
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
