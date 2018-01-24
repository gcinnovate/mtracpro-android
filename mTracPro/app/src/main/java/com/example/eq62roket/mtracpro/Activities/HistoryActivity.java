package com.example.eq62roket.mtracpro.Activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.eq62roket.mtracpro.Adapters.RecyclerAdapter;
import com.example.eq62roket.mtracpro.Helpers.BackgroundTask;
import com.example.eq62roket.mtracpro.Helpers.History;
import com.example.eq62roket.mtracpro.R;
import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    RecyclerView recyclerView;
    RecyclerAdapter adapter;
    ArrayList<History> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        //To improve performance
        recyclerView.setHasFixedSize(true);

        //Initialising the adapter
        BackgroundTask backgroundTask = new BackgroundTask(HistoryActivity.this);
        arrayList = backgroundTask.getHistoryList();
        adapter = new RecyclerAdapter(arrayList);
        recyclerView.setAdapter(adapter);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        ((EditText)searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text)).setBackgroundColor(Color.WHITE);
        ((EditText)searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text)).setTextColor(Color.BLACK);
        ((EditText)searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text)).setHintTextColor(Color.GRAY);

        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        newText = newText.toLowerCase();
        ArrayList<History> listHistory = new ArrayList<>();
        for (History history : arrayList){
            String period = history.getPeriod().toLowerCase();
            String details = history.getDetails().toLowerCase();
            String rawMsg = history.getRawMsg().toLowerCase();
            String date = history.getDate().toLowerCase();
            if (details.contains(newText)) {
                listHistory.add(history);
            }else if (rawMsg.contains(newText)){
                listHistory.add(history);
            }else if (period.contains(newText)){
                listHistory.add(history);
            }
            else if (date.contains(newText)){
                listHistory.add(history);
            }
        }
        adapter.setFilter(listHistory);
        return true;
    }
}
