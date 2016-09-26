package com.daveanthonythomas.soundcloudplayer.api;

/**
 * Created by dave on 2016-09-25.
 */

public interface WaveformUrlRequestCallback {

    void onUrlFound(String url);
    void onError(Throwable t);
}
