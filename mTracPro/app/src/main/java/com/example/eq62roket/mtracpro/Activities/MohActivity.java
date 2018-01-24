package com.example.eq62roket.mtracpro.Activities;

import android.graphics.Color;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

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

        //Initialising the adapter
        BackgroundTask backgroundTask = new BackgroundTask(MohActivity.this);
        bulletinArrayList = backgroundTask.getBulletin();
        adapter = new BulletinRecyclerAdapter(bulletinArrayList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search, menu);
        MenuItem menuItem = menu.findItem(R.id.search);
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
