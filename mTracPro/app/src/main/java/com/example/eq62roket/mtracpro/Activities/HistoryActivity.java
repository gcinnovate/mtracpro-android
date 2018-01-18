package com.example.eq62roket.mtracpro.Activities;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

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
//        adapter = new RecyclerAdapter(historyData(), HistoryActivity.this);
        recyclerView.setAdapter(adapter);


    }

//    public ArrayList<History> historyData(){
//        ArrayList<History> history = new ArrayList<>();
//        history.add(new History("2", "cases.ma.4.dy.5.ch.1", "You reported:\\n 4 cases of malari\\n 5 cases of dysentery and\\n 1 case of cholera.", "2018-01-11 15:10"));
//        history.add(new History("2", "death.ab.3.tf.1", "You reported:\\n 3 deaths of animal bites\\n1 death of typhoid", "2018-01-11 13:11"));
//        history.add(new History("2", "cases.ma.0", "You reported:\\n 0 cases of malaria", "2018-01-11 12:10"));
//
//        return history;
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

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
            String details = history.getDetail().toLowerCase();
            String rawMsg = history.getRawMsg().toLowerCase();
            String date = history.getDate().toLowerCase();
            if (details.contains(newText)) {
                listHistory.add(history);
            }else if (rawMsg.contains(newText)){
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
