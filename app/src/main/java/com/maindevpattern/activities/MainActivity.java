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
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.maindevpattern.R;
import com.maindevpattern.adapters.uitabbed.SectionsPagerAdapter;




public class MainActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //section pager initialization to display tabs
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs_layout);
        tabs.setupWithViewPager(viewPager);


        //textview and image of non-inherent case
        textView = findViewById(R.id.text_non_Ithernet);
        imageView = findViewById(R.id.non_Ithernet);

        //recyclerview
        recyclerView = findViewById(R.id.recyclerView);

        //progress bar
        progressBar = findViewById(R.id.progressBar);


        //info tab icon init
        infoTabIcon = findViewById(R.id.info_tab_icon);

        //check for network connection
        if (isNetworkAvailable()) {

        } else {
            textView.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            infoTabIcon.setVisibility(View.GONE);
        }


        //calling function of clicked tab info icon on top
        infoTabIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //starting info activity
                startActivity(new Intent(MainActivity.this, InfoActivity.class));
            }
        });
        progressBar.setIndeterminate(false);
        progressBar.setVisibility(View.GONE);


    }



    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


}
