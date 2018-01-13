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

        search.setOnQueryTextListener(listener);


//        HistoryData();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    SearchView.OnQueryTextListener listener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextChange(String query) {
                query = query.toLowerCase();
                final ArrayList<History> filteredList = new ArrayList<>();
                for (int i = 0; i<arrayList.size(); i++) {
                    final String text = arrayList.get(i).getDetail().toLowerCase();
                if (text.contains(query)) {
                    filteredList.add(arrayList.get(i));
                }
            }
                recyclerView.setLayoutManager(new LinearLayoutManager(HistoryActivity.this));
                    adapter = new RecyclerAdapter(filteredList, HistoryActivity.this );
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();  // data set changed<br />
            return true;
           }
        public boolean onQueryTextSubmit(String query) {
            return false;
        }
    };

//    private void HistoryData() {
//        History history = new History("12", "cases.ma.4.dy.5.ch.1",
//                "You reported:\\n 4 cases of malari\\n 5 cases of dysentery and\\n 1 case of cholera",
//                "2018-01-11 15:10");
//        arrayList.add(history);
//
//        history = new History("4", "death.ab.3.tf.1",
//                "You reported:\\n 3 deaths of animal bites\\n1 death of typhoid",
//                "2018-01-11 13:11");
//        arrayList.add(history);
//
//        history = new History("86", "cases.ma.0",
//                "You reported:\\n 0 cases of malaria",
//                "2018-01-11 12:10");
//        arrayList.add(history);
//
//        history = new History("7", "death.ab.3.tf.1",
//                "You reported:\\n 3 deaths of animal bites\\n1 death of typhoid",
//                "2018-01-11 13:11");
//        arrayList.add(history);
//
//        history = new History("8", "cases.ma.0",
//                "You reported:\\n 0 cases of malaria",
//                "2018-01-11 12:10");
//        arrayList.add(history);
//
//        history = new History("54", "death.ab.3.tf.1",
//                "You reported:\\n 3 deaths of animal bites\\n1 death of typhoid",
//                "2018-01-11 13:11");
//        arrayList.add(history);
//
//        history = new History("34", "cases.ma.0",
//                "You reported:\\n 0 cases of malaria",
//                "2018-01-11 12:10");
//        arrayList.add(history);
//
//        history = new History("2", "death.ab.3.tf.1",
//                "You reported:\\n 3 deaths of animal bites\\n1 death of typhoid",
//                "2018-01-11 13:11");
//        arrayList.add(history);
//
//        history = new History("34", "cases.ma.0",
//                "You reported:\\n 0 cases of malaria",
//                "2018-01-11 12:10");
//        arrayList.add(history);
//
//        adapter.notifyDataSetChanged();
//    }
}
