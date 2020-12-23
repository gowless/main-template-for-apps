package com.maindevpattern.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.maindevpattern.R;

import static com.maindevpattern.MainClass.isEmpty;


public class BeforeMainActivity extends AppCompatActivity {

    //info tab icon declaring
    ImageView infoTabIcon;

    //img non-ithernet
    ImageView imageView;

    //text non-ithernet
    TextView textView;

    //progress
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before_main);

        //info tab icon init
        infoTabIcon = findViewById(R.id.info_tab_icon);

        infoTabIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //starting info activity
                startActivity(new Intent(BeforeMainActivity.this, InfoActivity.class));
            }
        });

        progressBar = findViewById(R.id.progressBar3);

        //textview and image of non-inherent case
        textView = findViewById(R.id.text_non_Ithernet);
        imageView = findViewById(R.id.non_Ithernet);


        //check for network connection
        if (isNetworkAvailable()) {

        } else {
            textView.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            infoTabIcon.setVisibility(View.GONE);
        }


        if (isEmpty!=null) {
            if (isEmpty) {
                progressBar.setIndeterminate(false);
                nonCategoriesStart();
            } else {
                progressBar.setIndeterminate(false);
                mainStart();
            }
        } else {
            nonCategoriesStart();
        }
    }


    //starts NonCategory
    private void nonCategoriesStart() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(BeforeMainActivity.this, NonCategoriesActivity.class));
                finish();
            }
        }, 500);
    }

    private void mainStart() {
        startActivity(new Intent(BeforeMainActivity.this, MainActivity.class));
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


}