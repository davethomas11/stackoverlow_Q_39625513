package com.daveanthonythomas.soundcloudplayer;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.daveanthonythomas.soundcloudplayer.ui.SoundCloudPlayerFragment;

import static android.R.attr.fragment;

public class MainActivity extends AppCompatActivity implements TextView.OnEditorActionListener {

    //http://stackoverflow.com/questions/39625513/soundcloud-embedded-player-on-mobile
    public static final String SO_Q_39625513_TRACK_ID = "271188615";
    private SoundCloudPlayerFragment mFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText trackId = (EditText) findViewById(R.id.trackid);
        trackId.setOnEditorActionListener(this);

        FragmentManager manager = getFragmentManager();
        mFragment = (SoundCloudPlayerFragment)
                manager.findFragmentById(R.id.sound_cloud_player_fragment);

        mFragment.loadTrack(SO_Q_39625513_TRACK_ID);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

        if (actionId == EditorInfo.IME_ACTION_GO) {
            mFragment.loadTrack(v.getText().toString());
        }

        return false;
    }
}
