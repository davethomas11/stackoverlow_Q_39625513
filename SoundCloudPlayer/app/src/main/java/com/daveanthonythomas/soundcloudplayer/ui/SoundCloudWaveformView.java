package com.daveanthonythomas.soundcloudplayer.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.daveanthonythomas.soundcloudplayer.model.SoundCloudWaveform;

/**
 * Created by dave on 2016-09-26.
 */

public class SoundCloudWaveformView extends View {

    private float mProgress;
    private int mAccentColor = ContextCompat.getColor(getContext(),
            android.support.v7.appcompat.R.color.accent_material_light);

    public SoundCloudWaveformView(Context context) {
        super(context);
    }

    public SoundCloudWaveformView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SoundCloudWaveformView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SoundCloudWaveformView(Context context, AttributeSet attrs, int defStyleAttr, int
            defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private SoundCloudWaveform mSoundCloudWaveForm;
    private Paint mPaint = new Paint();

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mSoundCloudWaveForm == null) {
            return;
        }

        float lineWidth = mSoundCloudWaveForm.getSamples().length / (float)getWidth();
        float progressPoint =  getWidth() - ((1 - mProgress) * (float)getWidth());
        mPaint.setStrokeWidth(lineWidth);
        mPaint.setColor(mAccentColor);
        float x = 0;

        for (int sample : mSoundCloudWaveForm.getSamples()) {

            if (x > progressPoint) {
                mPaint.setColor(0xFF000000);
            }

            float ratio = sample / (float)mSoundCloudWaveForm.getHeight();
            float drawTo = getHeight() - ratio * getHeight();

            canvas.drawLine(x, getHeight(), x, drawTo, mPaint);
            x += lineWidth;
        }
    }

    public void setSoundCloudWaveForm(SoundCloudWaveform mSoundCloudWaveForm) {
        setWillNotDraw(false);
        this.mSoundCloudWaveForm = mSoundCloudWaveForm;
        invalidate();
    }
    
    public void setProgress(float progress) {
        mProgress =  progress;
        invalidate();
    }

    public void setAccentColor(int color) {
        mAccentColor = color;
        invalidate();
    }
}
