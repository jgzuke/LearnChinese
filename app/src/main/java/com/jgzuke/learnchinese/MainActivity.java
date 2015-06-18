package com.jgzuke.learnchinese;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

import com.gc.materialdesign.views.ButtonFloat;

/**
 * Created by jgzuke on 15-06-18.
 */
public class MainActivity extends ActionBarActivity implements OnClickListener {
    private static final String LOG_TAG = "MainActivity";

    private ButtonFloat mRecordBtn;
    private ButtonFloat mPlayRecordingBtn;
    private ButtonFloat mPlayCorrectBtn;

    private boolean mIsRecording = false;
    private boolean mIsPlayingRecording = false;
    private boolean mIsPlayingCorrect = false;
    private MyAudioRecorder mAudioRecorder;

    private Drawable mIconPlay;
    private Drawable mIconPause;
    private Drawable mIconRecord;
    private Drawable mIconStopRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecordBtn = (ButtonFloat) findViewById(R.id.menu_record_btn);
        mPlayRecordingBtn = (ButtonFloat) findViewById(R.id.menu_recording_btn);
        mPlayCorrectBtn = (ButtonFloat) findViewById(R.id.menu_correct_btn);
        mRecordBtn.setOnClickListener(this);
        mPlayRecordingBtn.setOnClickListener(this);
        mPlayCorrectBtn.setOnClickListener(this);
        mAudioRecorder = new MyAudioRecorder(this);
    }

    private void getDrawables() {
        Resources res = getResources();
        mIconPlay = res.getDrawable(R.drawable.ic_play_arrow_white_18dp);
        mIconPause = res.getDrawable(R.drawable.ic_play_arrow_white_18dp);
        mIconRecord = res.getDrawable(R.drawable.ic_play_arrow_white_18dp);
        mIconStopRecord = res.getDrawable(R.drawable.ic_play_arrow_white_18dp);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.menu_record_btn:
                clickRecord();
                break;
            case R.id.menu_recording_btn:
                clickPlayRecording();
                break;
            case R.id.menu_correct_btn:
                clickPlayCorrect();
                break;
        }
    }

    public void clickRecord() {
        mIsRecording = !mIsRecording;
        if(mIsRecording) {
            setFloatButtonView(mRecordBtn, R.color.recording_red, mIconStopRecord);
            mAudioRecorder.startRecording();
        } else {
            setFloatButtonView(mRecordBtn, R.color.app_blue, mIconRecord);
            mAudioRecorder.stopRecording();
        }
    }

    public void clickPlayRecording() {
        mIsPlayingRecording = !mIsPlayingRecording;
        if(mIsPlayingRecording) {
            setFloatButtonView(mPlayRecordingBtn, R.color.playing_green, mIconPause);
            mAudioRecorder.startPlaying(true);
        } else {
            setFloatButtonView(mPlayRecordingBtn, R.color.app_blue, mIconPlay);
            mAudioRecorder.stopPlaying();
        }
    }

    public void clickPlayCorrect() {
        mIsPlayingCorrect = !mIsPlayingCorrect;
        if(mIsPlayingCorrect) {
            setFloatButtonView(mPlayCorrectBtn, R.color.playing_green, mIconPause);
            mAudioRecorder.startPlaying(false);
        } else {
            setFloatButtonView(mPlayCorrectBtn, R.color.app_orange, mIconPlay);
            mAudioRecorder.stopPlaying();
        }
    }

    private void setFloatButtonView(ButtonFloat button, int colorID, Drawable drawable) {
        button.setBackgroundResource(colorID);
        button.setDrawableIcon(drawable);
    }
}