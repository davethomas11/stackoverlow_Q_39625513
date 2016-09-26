package com.daveanthonythomas.soundcloudplayer.api;

import com.daveanthonythomas.soundcloudplayer.model.SoundCloudWaveform;

/**
 * Created by dave on 2016-09-25.
 */

public interface WaveformCallback {

    void onWaveform(SoundCloudWaveform waveform);
    void onError(Throwable t);
}
