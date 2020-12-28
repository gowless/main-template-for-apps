package com.maindevpattern.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;


import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import com.maindevpattern.R;


import java.util.Objects;


public class DetailsOfferActivity extends AppCompatActivity {



    //progressbar declaring
    ProgressBar progressBar;

    //scroll view declaring
    ScrollView scrollView;

    //declaring toolbar
    Toolbar toolbar;

    //string position number
    Integer position;

    //string textName
    TextView textName, textAdress, textNumber, textMail, textSite, textPercent, textLicense, textTerms, textFistCredit, textNextCredit, textYUR;

    //image of offer
    ImageView imageView;

    //progressBarImage
    ProgressBar progressBarImage;

    //progressBarName
    ProgressBar progressBarName;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_offer);

        //declaring items
        declareItems();

        //getting intent
        Intent intent = getIntent();

        //getting extra number from cloak adapter
        position = intent.getIntExtra("position", 0);

        //starting method
        parseDataToObjects();

/*        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailsOfferActivity.this, CloakActivity.class));
            }
        }); */

        textTerms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailsOfferActivity.this, InfoDetailsActivity.class);
                intent.putExtra("check", 1);
                startActivity(intent);
            }
        });

    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(DetailsOfferActivity.this, CloakActivity.class));
    }


    //declaring items
    private void declareItems(){
        //initializing toolbar
        //toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
//        Objects.requireNonNull(getSupportActionBar()).setTitle("");

        //initializing views
        textTerms = findViewById(R.id.textTerms);

        scrollView = findViewById(R.id.scrollView2);
        imageView = findViewById(R.id.imageOffer);
      //  textName = findViewById(R.id.textName);
        textAdress = findViewById(R.id.textAdress);
        textNumber = findViewById(R.id.textNumber);
        textMail = findViewById(R.id.textMail);
        textSite = findViewById(R.id.textSite);
        textPercent = findViewById(R.id.textPercent);
        textLicense = findViewById(R.id.textLicense);
        textFistCredit = findViewById(R.id.textFirstCredit);
        textNextCredit = findViewById(R.id.textNextCredit);
        textYUR = findViewById(R.id.textYUR);

        //initializing progressBars
        progressBar = findViewById(R.id.progressBar4);
        progressBarImage = findViewById(R.id.progressbarImage);
     //   progressBarName = findViewById(R.id.progressbarName);
    }

    // parse data to fields
    @SuppressLint("SetTextI18n")
    private void parseDataToObjects(){
        //parsing data to views
        scrollView.setVisibility(View.VISIBLE);
        progressBar.setIndeterminate(false);
        progressBar.setVisibility(View.GONE);
        String URL = SplashActivity.listDataAll.get(position).getImg();

        /*
       textName.setText(SplashActivity.listDataAll.get(position).getOfferName().toUpperCase());
       progressBarName.setIndeterminate(false);
       progressBarName.setVisibility(View.GONE);
         */

        textAdress.setText("• " + SplashActivity.listDataAll.get(position).getOfferName());
        textNumber.setText("• " + SplashActivity.listDataAll.get(position).getDetail().getPhone());
        textMail.setText("• " + SplashActivity.listDataAll.get(position).getDetail().getEmail());
        textSite.setText("• " + SplashActivity.listDataAll.get(position).getDetail().getSite());
        textPercent.setText("• " + SplashActivity.listDataAll.get(position).getDetail().getApr() + "%");
        textLicense.setText("• " + SplashActivity.listDataAll.get(position).getDetail().getLicense());
        textFistCredit.setText("• " + SplashActivity.listDataAll.get(position).getAmount().getFrom() + "₴");
        textNextCredit.setText("• " + SplashActivity.listDataAll.get(position).getAmount().getTo() + "₴");
        textYUR.setText("• " + SplashActivity.listDataAll.get(position).getDetail().getAddress());

        //parsing image to imageview
        Glide.with(DetailsOfferActivity.this)
                .load(URL)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        progressBarImage.setIndeterminate(false);
                        progressBarImage.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        progressBarImage.setIndeterminate(false);
                        progressBarImage.setVisibility(View.GONE);
                        return false;
                    }
                })
                .centerInside()
                .into(imageView);
    }



}