package com.daveanthonythomas.soundcloudplayer.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.Observable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dave on 2016-09-25.
 */

public class SoundCloudPlayerViewModel extends BaseObservable {

    private boolean isPlaying;
    private boolean isLoading;
    private boolean isUserDraggingSeek;


    @Bindable
    public boolean isPlaying() {
        return isPlaying;
    }

    @Bindable
    public boolean isLoading() {
        return isLoading;
    }

    @Bindable
    public boolean isUserDraggingSeek() {
        return isUserDraggingSeek;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
        notifyChange();
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
        notifyChange();
    }

    public void setUserDraggingSeek(boolean userDraggingSeek) {
        isUserDraggingSeek = userDraggingSeek;
        notifyChange();
    }
}
