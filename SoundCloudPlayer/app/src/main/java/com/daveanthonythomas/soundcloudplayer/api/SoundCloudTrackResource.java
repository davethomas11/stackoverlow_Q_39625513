package com.daveanthonythomas.soundcloudplayer.api;

import com.daveanthonythomas.soundcloudplayer.model.SoundCloudTrack;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by dave on 2016-09-25.
 *
 * Downloads important track information from sound cloud
 * about a given track. So that we can stream it and play
 * it back to our wonderful users.
 *
 */
public interface SoundCloudTrackResource {
    @GET("tracks/{track_id}")
    Call<SoundCloudTrack> getTrack(@Path("track_id") String trackId,
                                   @Query("client_id") String clientId);
}
