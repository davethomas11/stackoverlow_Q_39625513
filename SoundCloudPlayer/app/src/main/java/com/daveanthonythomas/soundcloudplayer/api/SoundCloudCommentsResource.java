package com.daveanthonythomas.soundcloudplayer.api;

import com.daveanthonythomas.soundcloudplayer.model.SoundCloudComment;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by dave on 2016-09-25.
 */

public interface SoundCloudCommentsResource {

    @GET("tracks/{track_id}/comments")
    Call<List<SoundCloudComment>> getComments(@Path("track_id") String trackId,
                                              @Query("client_id") String clientId);
}
