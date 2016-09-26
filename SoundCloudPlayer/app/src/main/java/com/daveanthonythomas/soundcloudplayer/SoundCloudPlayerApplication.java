package com.daveanthonythomas.soundcloudplayer;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by dave on 2016-09-25.
 */

public class SoundCloudPlayerApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Stetho.initializeWithDefaults(this);
    }
}
