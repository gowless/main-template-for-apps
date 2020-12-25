package com.maindevpattern.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.maindevpattern.R;
import com.maindevpattern.adapters.uitabbed.SectionsPagerAdapter;
import com.maindevpattern.models.get.Data;
import com.maindevpattern.models.get.Liste;
import com.maindevpattern.network.Initializator;
import com.maindevpattern.network.Interface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SplashActivity extends AppCompatActivity {

    //attribution strings
    public static String  net, cam, adg, cre;

    //fragments lists of offers
    public static List<Liste> listDataAll;
    public static List<Liste> listDataBad;
    public static List<Liste> listDataZero;

    //https://api.mocki.io/v1/fe341b7d
    //url base
    public static final String APP_ID = "e0f0c81b-2bf4-482e-8862-80474131223b";
    //category empty check field
    public static Boolean isEmpty;
    //number of tabs field
    public static int numberOfTabs;
    //names of each tab
    public static String first, second, third;


    //carrier name string
    String carrier;


    //static AD_ID
    public static String ad_id;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //get firebase instance
        FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        //add event to firebase
        Bundle bundle = new Bundle();
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.APP_OPEN, bundle);

        //get ad_id
        getID();

        //getting carrier ISO name
        getCarrier();

        //checking carrier to match current country code SIM
        if (carrier.equals("ua")) {
            getJsonData();
        } else {
            getJsonDataCloak();
        }

    }


    //starting CloakActivity
    public void getCloak() {
        startActivity(new Intent(SplashActivity.this, CloakActivity.class));
    }

    //get carrier name
    public void getCarrier() {
        TelephonyManager manager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        carrier = manager.getSimCountryIso();
    }

    //starting BeforeMain activity
    public void getBeforeMain() {
        startActivity(new Intent(SplashActivity.this, BeforeMainActivity.class));
    }

    //getting advertising ID
    public void getID() {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    AdvertisingIdClient.Info adInfo = AdvertisingIdClient.getAdvertisingIdInfo(SplashActivity.this);
                    ad_id = adInfo != null ? adInfo.getId() : null;

                    assert ad_id != null;

                } catch (Exception e) {

                }
            }
        });
    }

    //setting to get json file and parse it to models in main case
    public void getJsonData() {
        Interface apiInterfaceCount = Initializator.getClient().create(Interface.class);
        Call<Data> call = apiInterfaceCount.getData(APP_ID);
        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(@NonNull Call<Data> call, @NonNull Response<Data> response) {
                assert response.body() != null;
                listDataAll = response.body().getList();
                isEmpty = response.body().getCategories().isEmpty();
                numberOfTabs = response.body().getCategories().size();
                listDataBad = response.body().getList();
                listDataZero = response.body().getList();

                //switching between numbers of tabs (maximum - 3)
                switch (numberOfTabs) {
                    case 0:
                        break;
                    case 1:
                        first = response.body().getCategories().get(0).getLabel();
                        break;
                    case 2:
                        first = response.body().getCategories().get(0).getLabel();
                        second = response.body().getCategories().get(1).getLabel();
                        break;
                    case 3:
                        first = response.body().getCategories().get(0).getLabel();
                        second = response.body().getCategories().get(1).getLabel();
                        third = response.body().getCategories().get(2).getLabel();
                        break;
                }
                //open MainActivity
                getBeforeMain();
            }

            @Override
            public void onFailure(@NonNull Call<Data> call, @NonNull Throwable t) {
                //starting MainActivity when failed to connect
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }
        });

    }



    //setting to get json file and parse it to models in case of cloak
     public void getJsonDataCloak(){
         Interface apiInterfaceCount = Initializator.getClient().create(Interface.class);
         Call<Data> call = apiInterfaceCount.getData(APP_ID);
         call.enqueue(new Callback<Data>() {
             @Override
             public void onResponse(@NonNull Call<Data> call, @NonNull Response<Data> response) {
                 assert response.body() != null;
                 listDataAll = response.body().getList();
                 isEmpty = response.body().getCategories().isEmpty();
                 numberOfTabs = response.body().getCategories().size();
                 listDataBad = response.body().getList();
                 listDataZero = response.body().getList();

                 //switching between numbers of tabs (maximum - 3)
                 switch (numberOfTabs) {
                     case 0:
                         break;
                     case 1:
                         first = response.body().getCategories().get(0).getLabel();
                         break;
                     case 2:
                         first = response.body().getCategories().get(0).getLabel();
                         second = response.body().getCategories().get(1).getLabel();
                         break;
                     case 3:
                         first = response.body().getCategories().get(0).getLabel();
                         second = response.body().getCategories().get(1).getLabel();
                         third = response.body().getCategories().get(2).getLabel();
                         break;
                 }
                 //open cloak
               getCloak();
             }

             @Override
             public void onFailure(@NonNull Call<Data> call, @NonNull Throwable t) {
                 //starting MainActivity when failed to connect
                 startActivity(new Intent(SplashActivity.this, MainActivity.class));
             }
         });

     }



}