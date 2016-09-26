package com.daveanthonythomas.soundcloudplayer.api;

import android.support.annotation.NonNull;

import com.daveanthonythomas.soundcloudplayer.model.SoundCloudComment;
import com.daveanthonythomas.soundcloudplayer.model.SoundCloudTrack;
import com.daveanthonythomas.soundcloudplayer.model.SoundCloudWaveform;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dave on 2016-09-25.
 */

public class SoundCloudAPI {

    private static final String API_BASE_URL = "http://api.soundcloud.com";
    private static final String PLAYER_BASE_URL = "https://w.soundcloud.com";

    public static final String CLIENT_ID = "cUa40O3Jg3Emvp6Tv4U6ymYYO50NUGpJ";

    private Retrofit apiServerSoundCloud;
    private Retrofit playerServerSoundCloud;

    /**
     * Retrieve the information about an sound cloud track form the
     * sound cloud api
     *
     * @param trackId
     * @param callback
     */
    public void requestTrack(@NonNull String trackId,
                             @NonNull Callback<SoundCloudTrack> callback) {

        Retrofit server = getSoundCloudAPI();
        SoundCloudTrackResource resource = server.create(SoundCloudTrackResource.class);
        Call<SoundCloudTrack> call = resource.getTrack(trackId, CLIENT_ID);
        call.enqueue(callback);
    }

    /**
     * Retrieve the comments for a given sound cloud track
     *
     * @param trackId
     * @param callback
     */
    public void requestComments(@NonNull String trackId,
                                @NonNull Callback<List<SoundCloudComment>> callback) {

        Retrofit server = getSoundCloudAPI();
        SoundCloudCommentsResource resource = server.create(SoundCloudCommentsResource.class);
        Call<List<SoundCloudComment>> call = resource.getComments(trackId, CLIENT_ID);
        call.enqueue(callback);
    }

    /**
     * Makes two network calls to sound cloud to fetch information
     * about a tracks waveform. The first call fetches the embedded player
     * strips a url to find the waveform. Then uses that url to make second
     * request to retrieve the tracks waveform data.
     *
     * @param trackId
     * @param callback
     */
    public void requestWaveformForTrack(@NonNull String trackId,
                                        @NonNull final WaveformCallback callback) {

        stripWaveformUrlFromEmbeddedPlayer(trackId, new WaveformUrlRequestCallback() {

            @Override
            public void onUrlFound(String uri) {

                try {
                    URL url = new URL(uri);
                    requestWaveform(String.format("%s://%s",
                            url.getProtocol(),
                            url.getHost()),
                            url.getPath().replaceFirst("/",""),
                            callback);

                } catch (MalformedURLException e) {

                    callback.onError(e);
                }
            }

            @Override
            public void onError(Throwable t) {

                callback.onError(t);
            }
        });
    }

    /**
     * Retrieve the embed html code for a track
     *
     * @param trackId
     * @param callback
     */
    public void requestEmbedPlayerCode(@NonNull String trackId,
                                       @NonNull Callback<ResponseBody> callback) {

        Retrofit server = getSoundCloudPlayerServer();
        SoundCloudEmbeddedPlayerResource resource =
                server.create(SoundCloudEmbeddedPlayerResource.class);

        String url = "https%3A//api.soundcloud.com/tracks/" + trackId;
        Call<ResponseBody> call = resource.getPlayerHTML(url);
        call.enqueue(callback);
    }

    /**
     * Downloads a json file with a tracks waveform data.
     * Example: https://wis.sndcdn.com/sTEoteC5oW3r_m.json
     *
     * @param baseUrl      - based on the example this would be https://wis.sndcdn.com
     * @param resourcePath - based on the example this would be sTEoteC5oW3r_m.json
     * @param callback
     */
    public void requestWaveform(@NonNull String baseUrl,
                                @NonNull String resourcePath,
                                final @NonNull WaveformCallback callback) {

        Retrofit server = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getClient())
                .build();
        final SoundCloudWaveformResource resource = server.create(SoundCloudWaveformResource.class);
        resource.getWaveform(resourcePath).enqueue(new Callback<SoundCloudWaveform>() {

            @Override
            public void onResponse(Call<SoundCloudWaveform> call, Response<SoundCloudWaveform>
                    response) {

                callback.onWaveform(response.body());
            }

            @Override
            public void onFailure(Call<SoundCloudWaveform> call, Throwable t) {

                callback.onError(t);
            }
        });
    }

    /**
     * Strips the url that returns data about a tracks waveform from
     * the embedded player. This information is not available in
     * the main sound cloud API. But we can attempt to find it here.
     *
     * @param trackId
     * @param callback
     */
    public void stripWaveformUrlFromEmbeddedPlayer(@NonNull String trackId,
                                                   @NonNull WaveformUrlRequestCallback callback) {

        requestEmbedPlayerCode(trackId, new WaveformUrlStripper(callback));
    }

    private Retrofit getSoundCloudAPI() {

        if (apiServerSoundCloud == null) {

            apiServerSoundCloud = new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .client(getClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return apiServerSoundCloud;
    }

    private Retrofit getSoundCloudPlayerServer() {

        if (playerServerSoundCloud == null) {

            playerServerSoundCloud = new Retrofit.Builder()
                    .baseUrl(PLAYER_BASE_URL)
                    .client(getClient())
                    .build();
        }

        return playerServerSoundCloud;
    }

    private OkHttpClient getClient() {

        return new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();
    }

}
