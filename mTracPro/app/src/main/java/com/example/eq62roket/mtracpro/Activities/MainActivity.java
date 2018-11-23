package com.example.eq62roket.mtracpro.Activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.example.eq62roket.mtracpro.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    LinearLayout imageArv, imageMat, imageApt, imageCases, imageDeath, imageTra, imageEpc,
            imageEpd, imageIpt, imageGp, imageTb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        imageApt = (LinearLayout) findViewById(R.id.imageApt);
        imageArv = (LinearLayout)findViewById(R.id.imageArv);
        imageMat = (LinearLayout)findViewById(R.id.imageMat);
        imageCases = (LinearLayout)findViewById(R.id.imageCases);
        imageDeath = (LinearLayout)findViewById(R.id.imageDeath);
        imageTra = (LinearLayout)findViewById(R.id.imageTra);
        imageEpc = (LinearLayout)findViewById(R.id.imageEpc);
        imageEpd = (LinearLayout)findViewById(R.id.imageEpd);
        imageIpt = (LinearLayout)findViewById(R.id.imageIpt);
        imageGp = (LinearLayout)findViewById(R.id.imageGp);
        imageTb = (LinearLayout)findViewById(R.id.imageTb);

        imageEpd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent epdIntent = new Intent(MainActivity.this, EpdActivity.class);
                startActivity(epdIntent);
            }
        });

        imageEpc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent idcIntent = new Intent(MainActivity.this, EpcActivity.class);
                startActivity(idcIntent);
            }
        });

        imageApt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent aptIntent = new Intent(MainActivity.this, AptActivity.class);
                startActivity(aptIntent);

            }
        });

        imageArv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent arvIntent = new Intent(MainActivity.this, ArvActivity.class);
                startActivity(arvIntent);

            }
        });

        imageMat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent matIntent = new Intent(MainActivity.this, MatActivity.class);
                startActivity(matIntent);

            }
        });

        imageCases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent casesIntent = new Intent(MainActivity.this, CasesActivity.class);
                startActivity(casesIntent);

            }
        });

        imageDeath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent deathIntent = new Intent(MainActivity.this, DeathActivity.class);
                startActivity(deathIntent);

            }
        });

        imageTra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent traIntent = new Intent(MainActivity.this, TraActivity.class);
                startActivity(traIntent);

            }
        });

        imageIpt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iptIntent = new Intent(MainActivity.this, IptActivity.class);
                startActivity(iptIntent);

            }
        });

        imageGp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gpIntent = new Intent(MainActivity.this, GPActivity.class);
                startActivity(gpIntent);

            }
        });

        imageTb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tbIntent = new Intent(MainActivity.this, TBActivity.class);
                startActivity(tbIntent);

            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.nav_profile) {
            // start ProfileActivity
            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            startActivity(intent);

        }else if (id == R.id.nav_history){
            Intent HistoryIntent = new Intent(MainActivity.this, HistoryActivity.class);
            startActivity(HistoryIntent);

        }
        else if (id == R.id.nav_about){
            Intent AboutIntent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(AboutIntent);

        }
        else if (id == R.id.nav_moh){
            Intent mohActivity = new Intent(MainActivity.this, MohActivity.class);
            startActivity(mohActivity);

        }
        else if (id == R.id.nav_logout) {
            // start LogoutActivity
            Intent intent = new Intent(MainActivity.this, LogoutActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
