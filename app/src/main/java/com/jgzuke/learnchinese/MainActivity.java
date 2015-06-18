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
        if(!mIsRecording) {
            mRecordBtn.setBackgroundResource(R.color.recording_red);
            mRecordBtn.setDrawableIcon(mIconStopRecord);
            mAudioRecorder.startRecording();
        } else {
            mRecordBtn.setBackgroundResource(R.color.app_blue);
            mRecordBtn.setDrawableIcon(mIconRecord);
            mAudioRecorder.stopRecording();
        }
    }

    public void clickPlayRecording() {
        if(!mIsPlayingRecording) {
            mRecordBtn.setBackgroundResource(R.color.playing_green);
            mRecordBtn.setDrawableIcon(mIconPause);
            mAudioRecorder.startPlaying(true);
        } else {
            mRecordBtn.setBackgroundResource(R.color.app_blue);
            mRecordBtn.setDrawableIcon(mIconPlay);
            mAudioRecorder.stopPlaying();
        }
    }

    public void clickPlayCorrect() {
        if(!mIsPlayingCorrect) {
            mRecordBtn.setBackgroundResource(R.color.playing_green);
            mRecordBtn.setDrawableIcon(mIconPause);
            mAudioRecorder.startPlaying(false);
        } else {
            mRecordBtn.setBackgroundResource(R.color.app_orange);
            mRecordBtn.setDrawableIcon(mIconPlay);
            mAudioRecorder.stopPlaying();
        }
    }
}