package com.example.eq62roket.mtracpro.Activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.android.volley.VolleyError;
import com.example.eq62roket.mtracpro.Adapters.RecyclerAdapter;
import com.example.eq62roket.mtracpro.Helpers.BackgroundTask;
import com.example.eq62roket.mtracpro.Helpers.History;
import com.example.eq62roket.mtracpro.Interfaces.HistoryVolleyCallBack;
import com.example.eq62roket.mtracpro.R;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{
    private static final String TAG = "HistoryActivity";

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

        //To improve performance
        recyclerView.setHasFixedSize(true);

        //Initialising the adapter
        BackgroundTask backgroundTask = new BackgroundTask(HistoryActivity.this);
        backgroundTask.getHistoryList(new HistoryVolleyCallBack() {
            @Override
            public void onStart() {
                Log.d(TAG, "Call back started:>>>>>>>>>> " );
            }

            @Override
            public void onSuccess(ArrayList<History> historyArrayList) {
                Log.d(TAG, "ArrayList in onCallback: " + historyArrayList);
                adapter = new RecyclerAdapter(historyArrayList);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(VolleyError volleyError) {
                Log.d(TAG, "onFailure in Callback: " + volleyError);
            }
        });

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

        //Get SearchView autocomplete object
        final SearchView.SearchAutoComplete searchAutoComplete = (SearchView.SearchAutoComplete)searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchAutoComplete.setDropDownBackgroundResource(android.R.color.background_light);

        //New ArrayAdapter to add data to search auto complete object
        String dataArr[] = {"Cases", "Death", "Apt", "Arv", "Mat", "Tra", "Malaria", "Dysentery", "Animal", "Cholera", "Guinew Worm", "Measles", "Tetanus"};
        ArrayAdapter<String> bulletinAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, dataArr);
        searchAutoComplete.setAdapter(bulletinAdapter);

        //Listen to search View item on click event.
        searchAutoComplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String queryString = (String)adapterView.getItemAtPosition(i);
                searchAutoComplete.setText("" + queryString);
//                Toast.makeText(HistoryActivity.this, "you clicked " + queryString, Toast.LENGTH_SHORT).show();

            }
        });

        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        AlertDialog alertDialog = new AlertDialog.Builder(HistoryActivity.this).create();
        alertDialog.setMessage("Search keyword is " + query);
        alertDialog.show();
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
