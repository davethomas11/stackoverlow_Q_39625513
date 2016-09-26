package com.daveanthonythomas.soundcloudplayer.api;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Path;

/**
 * Created by dave on 2016-09-25.
 */

public class WaveformUrlStripper implements Callback<ResponseBody> {

    private static final String WAVEFORM_REGEX =
            ".*\"waveform_url\":\"([^\"]*)\".*";

    private static final String URL_REGEX =
            "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";

    private WaveformUrlRequestCallback callback;

    public WaveformUrlStripper(@NonNull WaveformUrlRequestCallback callback) {
        this.callback = callback;
    }

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

        try {

            String waveformUrl = null;
            if (response.body() != null) {
                String responseString = response.body().string();
                waveformUrl = stripWaveformUrl(responseString);
            }

            if (waveformUrl == null) {
                callback.onError(new Exception("Waveform URL not found in embedded player response"));
            } else {
                callback.onUrlFound(waveformUrl);
            }

        } catch (IOException e) {

            callback.onError(e);
        }


    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {
        callback.onError(t);
    }

    /**
     * Strips the json parameter waveform_url from the HTML response
     * This can be found in the HTML response for the sound cloud embeded player request
     * If this stripping does not find the value null will be returned
     *
     * @param html
     * @return
     */
    public @Nullable String stripWaveformUrl(@Nullable String html) {

        if (html == null) {
            return null;
        }

        String url = html.replaceFirst(WAVEFORM_REGEX, "$1");
        if (url != null && url.matches(URL_REGEX)) {
            return url;
        }

        return null;
    }
}
