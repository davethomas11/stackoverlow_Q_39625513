package com.daveanthonythomas.soundcloudplayer.ui;

import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.daveanthonythomas.soundcloudplayer.R;
import com.daveanthonythomas.soundcloudplayer.api.SoundCloudAPI;
import com.daveanthonythomas.soundcloudplayer.api.WaveformCallback;
import com.daveanthonythomas.soundcloudplayer.databinding.SoundCloudPlayerFragmentBinding;
import com.daveanthonythomas.soundcloudplayer.model.SoundCloudPlayerViewModel;
import com.daveanthonythomas.soundcloudplayer.model.SoundCloudTrack;
import com.daveanthonythomas.soundcloudplayer.model.SoundCloudWaveform;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.URI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by dave on 2016-09-25.
 */

public class SoundCloudPlayerFragment extends Fragment implements
        Callback<SoundCloudTrack>, View.OnClickListener, MediaPlayer.OnPreparedListener, SeekBar
        .OnSeekBarChangeListener, WaveformCallback {

    public static final String ARG_TRACK_ID = "mTrackId";

    private MediaPlayer mMediaPlayer;
    private Handler mHandler;

    SoundCloudPlayerViewModel mPlayerViewModel = new SoundCloudPlayerViewModel();
    SoundCloudPlayerFragmentBinding mBinding = null;
    SoundCloudAPI mSoundCloudAPI;
    SoundCloudTrack mSoundCloudTrack;
    String mTrackId;

    public SoundCloudPlayerFragment() {
        mSoundCloudAPI = new SoundCloudAPI();
        mHandler = new Handler();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.sound_cloud_player_fragment, container, false);

        String trackId = null;
        if (getArguments() != null) {

            trackId = getArguments().getString(ARG_TRACK_ID, null);
        } else if (trackId == null && savedInstanceState != null) {

            trackId = savedInstanceState.getString(ARG_TRACK_ID, null);
        }

        loadTrack(trackId);

        mBinding.setPlayer(mPlayerViewModel);

        return mBinding.getRoot();
    }

    @Override
    public void onDestroyView() {
        mBinding = null;
        mMediaPlayer.release();
        mMediaPlayer = null;

        super.onDestroyView();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(ARG_TRACK_ID, mTrackId);
    }

    public void loadTrack(@Nullable String trackId) {
        if (trackId == null) {
            mTrackId = null;
            mSoundCloudTrack = null;
            mBinding.setTrack(mSoundCloudTrack);
            return;
        }

        mTrackId = trackId;
        mSoundCloudAPI.requestTrack(trackId, this);
        mSoundCloudAPI.requestWaveformForTrack(trackId, this);
    }

    @Override
    public void onResponse(Call<SoundCloudTrack> call, Response<SoundCloudTrack> response) {

        mSoundCloudTrack = response.body();
        mBinding.setTrack(mSoundCloudTrack);

        Picasso.with(getActivity())
                .load(mSoundCloudTrack.getArtworkUrl())
                .into(mBinding.trackImage);

        mBinding.trackSeekBar.setOnSeekBarChangeListener(this);
        mBinding.trackPlayButton.setOnClickListener(this);
        mBinding.trackSeekBar.setMax(mSoundCloudTrack.getDuration());

        mPlayerViewModel.setLoading(true);
        mBinding.trackLoading.show();
        Uri stream = Uri.parse(mSoundCloudTrack.getStreamUrl() + "?client_id=" +
                SoundCloudAPI.CLIENT_ID);
        try {
            if (mMediaPlayer != null) {
                mMediaPlayer.pause();
                mMediaPlayer.stop();
                mMediaPlayer.release();
            }

            mMediaPlayer = new MediaPlayer();
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mMediaPlayer.setDataSource(getActivity(), stream);
            mMediaPlayer.setOnPreparedListener(this);
            mMediaPlayer.prepareAsync();

        } catch (IOException e) {
            // TODO: Proper error handling in UI
        }
    }

    @Override
    public void onFailure(Call<SoundCloudTrack> call, Throwable t) {
        // TODO: Proper error handling in UI
    }

    @Override
    public void onClick(View v) {

        if (v == mBinding.trackPlayButton) {
            togglePlayback();
        }
    }

    public void togglePlayback() {
        if (mPlayerViewModel.isPlaying()) {
            pause();
        } else {
            play();
        }
    }

    public void play() {
        if (mPlayerViewModel.isPlaying()) {
            return ;
        }

        mPlayerViewModel.setPlaying(true);
        mMediaPlayer.start();

        mHandler.postDelayed(mPlaybackCheck, 500);
    }

    public void pause() {
        if (!mPlayerViewModel.isPlaying()) {
            return ;
        }

        mPlayerViewModel.setPlaying(false);

        mMediaPlayer.pause();
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        Log.d("Sound Cloud", "Track loaded");
        play();
        mPlayerViewModel.setLoading(false);
        mBinding.trackLoading.hide();
    }

    private Runnable mPlaybackCheck = new Runnable() {
        @Override
        public void run() {

            if (!mPlayerViewModel.isUserDraggingSeek()) {
                mBinding.trackSeekBar.setProgress(mMediaPlayer.getCurrentPosition());
            }

            if (mPlayerViewModel.isPlaying()) {
                mHandler.postDelayed(mPlaybackCheck, 500);
            }
        }
    };

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        if (fromUser) {
            mMediaPlayer.seekTo(progress);
        }

        mBinding.trackWaveForm.setProgress((float)progress / seekBar.getMax());
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        mPlayerViewModel.setUserDraggingSeek(true);
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        mPlayerViewModel.setUserDraggingSeek(false);
    }

    @Override
    public void onWaveform(SoundCloudWaveform waveform) {
        mBinding.trackWaveForm.setSoundCloudWaveForm(waveform);
    }

    @Override
    public void onError(Throwable t) {
        Log.e("SoundCloud Waveform", t.getMessage());
    }
}
