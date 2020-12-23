package com.maindevpattern.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.SparseLongArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.maindevpattern.MainClass;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloak);

        //textview and image of non-inherent case
        textView = findViewById(R.id.text_non_Ithernet);
        imageView = findViewById(R.id.non_Ithernet);

        //info tab icon init
        infoTabIcon = findViewById(R.id.info_tab_icon);

        progressBar = findViewById(R.id.progressBar2);

        //check for network connection
        if (isNetworkAvailable()) {

        } else {
            textView.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            infoTabIcon.setVisibility(View.GONE);
        }


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



        infoTabIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //starting info activity
                startActivity(new Intent(CloakActivity.this, InfoDetailsActivity.class));
            }
        });




    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}