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

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.maindevpattern.R;
import com.maindevpattern.models.get.Data;
import com.maindevpattern.models.get.Liste;
import com.maindevpattern.network.Initializator;
import com.maindevpattern.network.Interface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SplashActivity extends Activity {
    public static String tt, tn, net, cam, adg, cre;

    public static List<Liste> listDataAll;
    public static List<Liste> listDataBad;
    public static List<Liste> listDataZero;


    //url base
    public static final String APP_ID = "com.orkotkreditru";
    public static Boolean isEmpty;
    public static int numberOfTabs;
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
        FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        Bundle bundle = new Bundle();
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.APP_OPEN, bundle);

        //get ad_id
        getID();

        //getting carrier ISO name
        getCarrier();

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

    public void getBeforeMain() {
        startActivity(new Intent(SplashActivity.this, BeforeMainActivity.class));
    }

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

    //setting to get json file and parse it to models
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
                getBeforeMain();
            }

            @Override
            public void onFailure(@NonNull Call<Data> call, @NonNull Throwable t) {

            }
        });

    }

     private void getJsonDataCloak(){
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

               getBeforeMain();
             }

             @Override
             public void onFailure(@NonNull Call<Data> call, @NonNull Throwable t) {

             }
         });

     }


}