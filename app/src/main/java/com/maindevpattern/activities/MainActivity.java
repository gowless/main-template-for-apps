package com.maindevpattern.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.maindevpattern.R;
import com.maindevpattern.adapters.uitabbed.SectionsPagerAdapter;


public class MainActivity extends AppCompatActivity {

    //section pager
    SectionsPagerAdapter sectionsPagerAdapter;

    public static String  net, cam, adg, cre, adid;

    //recyclerview
    RecyclerView recyclerView;

    //progress bar
    ProgressBar progressBar;

    //info tab icon declaring
    ImageView infoTabIcon;

    //img non-ithernet
    ImageView imageView;

    //text non-ithernet
    TextView textView;

    //top alert
    ConstraintLayout topAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //init Section Pager instance
        sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());

        //declaring vars
        setDeclaring();

        //section pager initialization to display tabs
        setupViewPager();

        //network callback for getting data
        setNetworkCallBacks();

        //check for network connection
        if (isNetworkAvailable()) {

        } else {
            setNonEthernetCase();
        }


        //calling function of clicked tab info icon on top
     /*   infoTabIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //starting info activity
                startActivity(new Intent(MainActivity.this, InfoActivity.class));
            }
        }); */
        progressBar.setIndeterminate(false);
        progressBar.setVisibility(View.GONE);

        //setting top alert onClick
        topAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                topAlert.setVisibility(View.GONE);
            }
        });

    }



    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

    //network availability check
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    //setting ViewPager
    private void setupViewPager(){
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs_layout);
        tabs.setupWithViewPager(viewPager);
    }

    //setting Network Callbacks
    private void setNetworkCallBacks(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            if (capabilities == null){

               // Toast.makeText(MainActivity.this, "No Connection", Toast.LENGTH_LONG).show();
            }
            connectivityManager.registerDefaultNetworkCallback(new ConnectivityManager.NetworkCallback(){
                @Override
                public void onAvailable(@NonNull Network network) {
                     /*
                    here you can add some features when ethernet comes back
                     */
                }
                @Override
                public void onLost(@NonNull Network network) {
                     /*
                    here you can add some features when ethernet connection lost
                     */
                }
            });
        }
    }

    // declaring main objects
    private void setDeclaring(){
        //textview and image of non-inherent case
        textView = findViewById(R.id.text_non_Ithernet);
        imageView = findViewById(R.id.non_Ithernet);
        //recyclerview
        recyclerView = findViewById(R.id.recyclerView);
        //progress bar
        progressBar = findViewById(R.id.progressBar);
        //info tab icon init
        infoTabIcon = findViewById(R.id.info_tab_icon);
        //top alert
        topAlert = findViewById(R.id.topAlert);

    }

    //setting image and text in non-ethernet case
    private void setNonEthernetCase(){
        textView.setVisibility(View.VISIBLE);
        imageView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        infoTabIcon.setVisibility(View.GONE);
    }




}
