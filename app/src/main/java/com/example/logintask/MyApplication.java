package com.example.logintask;

import android.app.Application;
import com.facebook.FacebookSdk;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize the Facebook SDK
        FacebookSdk.sdkInitialize(getApplicationContext());
    }
}
