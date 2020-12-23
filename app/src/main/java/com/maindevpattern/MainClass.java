package com.maindevpattern;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.adjust.sdk.Adjust;
import com.adjust.sdk.AdjustAttribution;
import com.adjust.sdk.AdjustConfig;
import com.adjust.sdk.LogLevel;
import com.adjust.sdk.OnAttributionChangedListener;



public class MainClass extends Application {

    //initializing variables
    public static String trackerToken, trackerName, network, campaign, adgroup, creative, adid;
    public static Float font;


    @Override
    public void onCreate() {
        super.onCreate();
        // Configure adjust SDK.
        String appToken = "75ekkl5aivleyo";
        String environment = AdjustConfig.ENVIRONMENT_SANDBOX;
        AdjustConfig config = new AdjustConfig(this, appToken, environment);
        // enable all logs
        config.setLogLevel(LogLevel.VERBOSE);
        font = getResources().getConfiguration().fontScale;
        config.setOnAttributionChangedListener(new OnAttributionChangedListener() {
            @Override
            public void onAttributionChanged(AdjustAttribution attribution) {
                SharedPreferences settings = getSharedPreferences("LOCAL", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = settings.edit();
                trackerToken = attribution.trackerToken;
                trackerName = attribution.trackerName;
                network = attribution.network;
                campaign = attribution.campaign;
                adgroup = attribution.adgroup;
                creative = attribution.creative;
                adid = attribution.adid;


                //put to sharedprefs
                editor.putString("trackerToken", trackerToken);
                editor.putString("trackerName", trackerName);
                editor.putString("network", network);
                editor.putString("campaign", campaign);
                editor.putString("adgroup", adgroup);
                editor.putString("creative", creative);
                editor.putString("adid", adid);
                editor.apply();
            }
        });
        Adjust.onCreate(config);

        registerActivityLifecycleCallbacks(new AdjustLifecycleCallbacks());
    }

    private static final class AdjustLifecycleCallbacks implements ActivityLifecycleCallbacks {
        @Override
        public void onActivityPreCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {

        }

        @Override
        public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {

        }

        @Override
        public void onActivityPostCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {

        }

        @Override
        public void onActivityPreStarted(@NonNull Activity activity) {

        }

        @Override
        public void onActivityStarted(@NonNull Activity activity) {

        }

        @Override
        public void onActivityPostStarted(@NonNull Activity activity) {

        }

        @Override
        public void onActivityPreResumed(@NonNull Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {
            Adjust.onResume();
        }

        @Override
        public void onActivityPostResumed(@NonNull Activity activity) {

        }

        @Override
        public void onActivityPrePaused(@NonNull Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {
            Adjust.onPause();
        }

        @Override
        public void onActivityPostPaused(@NonNull Activity activity) {

        }

        @Override
        public void onActivityPreStopped(@NonNull Activity activity) {

        }

        @Override
        public void onActivityStopped(@NonNull Activity activity) {

        }

        @Override
        public void onActivityPostStopped(@NonNull Activity activity) {

        }

        @Override
        public void onActivityPreSaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

        }

        @Override
        public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

        }

        @Override
        public void onActivityPostSaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

        }

        @Override
        public void onActivityPreDestroyed(@NonNull Activity activity) {

        }

        @Override
        public void onActivityDestroyed(@NonNull Activity activity) {

        }

        @Override
        public void onActivityPostDestroyed(@NonNull Activity activity) {

        }

    }


}
