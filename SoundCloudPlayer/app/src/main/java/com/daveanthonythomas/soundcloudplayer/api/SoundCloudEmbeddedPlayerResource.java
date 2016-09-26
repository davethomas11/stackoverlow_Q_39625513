package com.daveanthonythomas.soundcloudplayer.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by dave on 2016-09-25.
 */

public interface SoundCloudEmbeddedPlayerResource {

    @GET("player/")
    Call<ResponseBody> getPlayerHTML(@Query("url") String url);
}
