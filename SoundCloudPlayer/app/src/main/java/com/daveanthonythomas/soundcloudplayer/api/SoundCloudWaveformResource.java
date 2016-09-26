package com.daveanthonythomas.soundcloudplayer.api;

import com.daveanthonythomas.soundcloudplayer.model.SoundCloudWaveform;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by dave on 2016-09-25.
 */

public interface SoundCloudWaveformResource {

    @GET("{json_resource}")
    Call<SoundCloudWaveform> getWaveform(@Path("json_resource") String jsonResourceName);
}
