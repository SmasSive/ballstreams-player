package com.smassive.ballstreamsplayer.app;

import android.app.Application;

public class BallStreamsPlayerApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        String apiKey = BuildConfig.API_KEY;
    }
}
