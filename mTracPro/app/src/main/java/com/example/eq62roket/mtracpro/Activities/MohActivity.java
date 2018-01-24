package com.example.eq62roket.mtracpro.Activities;

import android.graphics.Color;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eq62roket.mtracpro.Adapters.BulletinRecyclerAdapter;
import com.example.eq62roket.mtracpro.Helpers.BackgroundTask;
import com.example.eq62roket.mtracpro.Helpers.Bulletin;
import com.example.eq62roket.mtracpro.R;

import java.util.ArrayList;

public class MohActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{
    RecyclerView recyclerView;
    BulletinRecyclerAdapter adapter;
    ArrayList<Bulletin> bulletinArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moh);

        recyclerView = (RecyclerView) findViewById(R.id.bulletin_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        //To improve performance
        recyclerView.setHasFixedSize(true);

        BackgroundTask backgroundTask = new BackgroundTask(MohActivity.this);
        bulletinArrayList = backgroundTask.getBulletin();
        adapter = new BulletinRecyclerAdapter(bulletinArrayList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search, menu);
        final MenuItem menuItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        ((EditText)searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text)).setBackgroundColor(Color.WHITE);
        ((EditText)searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text)).setTextColor(Color.BLACK);
        ((EditText)searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text)).setHintTextColor(Color.GRAY);

        //Get SearchView autocomplete object
        final SearchView.SearchAutoComplete searchAutoComplete = (SearchView.SearchAutoComplete)searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
//        searchAutoComplete.setBackgroundColor(Color.GRAY);
//        searchAutoComplete.setTextColor(Color.WHITE);
        searchAutoComplete.setDropDownBackgroundResource(android.R.color.holo_orange_dark);

        //New ArrayAdapter to add data to search auto complete object
        String dataArr[] = {"Malaria", "Immunization", "Measles"};
        ArrayAdapter<String> bulletinAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, dataArr);
        searchAutoComplete.setAdapter(bulletinAdapter);

        //Listen to search View item on click event.
        searchAutoComplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String queryString = (String)adapterView.getItemAtPosition(i);
                searchAutoComplete.setText("" + queryString);
                Toast.makeText(MohActivity.this, "you clicked " + queryString, Toast.LENGTH_SHORT).show();

            }
        });

        //Creating event to trigger when submit search query

        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        AlertDialog alertDialog = new AlertDialog.Builder(MohActivity.this).create();
        alertDialog.setMessage("Search keyword is " + query);
        alertDialog.show();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        newText = newText.toLowerCase();
        ArrayList<Bulletin> listBulletin = new ArrayList<>();
        for (Bulletin bulletin : bulletinArrayList){
            String content = bulletin.getContent().toLowerCase();
            String date = bulletin.getDate().toLowerCase();
            if (content.contains(newText)) {
                listBulletin.add(bulletin);
            }else if (date.contains(newText)){
                listBulletin.add(bulletin);
            }
        }
        adapter.setFilter(listBulletin);
        return true;
    }
}
