package com.maindevpattern.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.maindevpattern.R;
import com.maindevpattern.adapters.AdapterCloak;


public class CloakActivity extends AppCompatActivity {

    ProgressBar progressBar;
    RecyclerView recyclerView;

    //img non-ithernet
    ImageView imageView;

    //text non-ithernet
    TextView textView;

    //info tab icon declaring
    ImageView infoTabIcon;

    //top text button
    TextView topTextCloak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloak);
        //declaring vars
        declareVars();

        //check for network connection
        if (isNetworkAvailable()) {

        } else {
           setNonEthernetCase();
        }

        // setting adapter
        settingAdapter();

/*        infoTabIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //starting info activity
                startActivity(new Intent(CloakActivity.this, InfoDetailsActivity.class));
            }
        }); */

        //top text onClick
        topTextCloak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CloakActivity.this, InfoDetailsActivity.class));
            }
        });




    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

    //checking network availability
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    //setting image and text in non-ethernet case
    private void setNonEthernetCase(){
        textView.setVisibility(View.VISIBLE);
        imageView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        infoTabIcon.setVisibility(View.GONE);
    }

    // setting cloak adapter
    private void settingAdapter(){
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        AdapterCloak recyclerAdapterCloakList = new AdapterCloak(getApplicationContext(), SplashActivity.listDataAll);
        recyclerAdapterCloakList.setDataList(SplashActivity.listDataAll);
        recyclerView.setAdapter(recyclerAdapterCloakList);
        progressBar.setIndeterminate(false);
        progressBar.setVisibility(View.GONE);
    }

    // declaring main vars
    private void declareVars(){
        //textview and image of non-inherent case
        textView = findViewById(R.id.text_non_Ithernet);
        imageView = findViewById(R.id.non_Ithernet);

        //info tab icon init
        infoTabIcon = findViewById(R.id.info_tab_icon);

        progressBar = findViewById(R.id.progressBar2);

        topTextCloak = findViewById(R.id.textCloakTop);
    }
}