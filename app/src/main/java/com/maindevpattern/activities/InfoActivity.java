package com.maindevpattern.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import com.maindevpattern.R;

import java.util.Objects;

public class InfoActivity extends AppCompatActivity {

    //init toolbar
   // Toolbar toolbar;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        //declaring toolbar ans set support
        //toolbar = findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);
//        Objects.requireNonNull(getSupportActionBar()).setTitle("");


        //onclick to back on main page
      /*  toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InfoActivity.this, MainActivity.class));
            }
        }); */
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(InfoActivity.this, MainActivity.class));
    }
}