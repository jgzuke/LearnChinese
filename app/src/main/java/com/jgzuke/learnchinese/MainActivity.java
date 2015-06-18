package com.jgzuke.learnchinese;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

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

    private int mIconPlayID = R.drawable.ic_play_arrow_white_36dp;
    private int mIconPauseID = R.drawable.ic_pause_white_36dp;
    private int mIconRecordID = R.drawable.ic_mic_white_36dp;
    private int mIconStopRecordID = R.drawable.ic_stop_white_36dp;

    public Resources resources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        resources = getResources();

        setContentView(R.layout.activity_main);
        mRecordBtn = (ButtonFloat) findViewById(R.id.menu_record_btn);
        mPlayRecordingBtn = (ButtonFloat) findViewById(R.id.menu_recording_btn);
        mPlayCorrectBtn = (ButtonFloat) findViewById(R.id.menu_correct_btn);
        mRecordBtn.setOnClickListener(this);
        mPlayRecordingBtn.setOnClickListener(this);
        mPlayCorrectBtn.setOnClickListener(this);
        mAudioRecorder = new MyAudioRecorder(this);
    }

    public void makeToast(int resID) {
        Toast.makeText(this, resources.getString(resID), Toast.LENGTH_SHORT).show();
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

    private void clickRecord() {
        mIsRecording = !mIsRecording;
        if(mIsRecording) {
            if(!mAudioRecorder.startRecording()) {
                mIsRecording = false;
                return;
            }
            setFloatButtonView(mRecordBtn, R.color.recording_red, mIconStopRecordID);
        } else {
            setFloatButtonView(mRecordBtn, R.color.app_blue, mIconRecordID);
            mAudioRecorder.stopRecording();
        }
    }

    private void clickPlayRecording() {
        mIsPlayingRecording = !mIsPlayingRecording;
        if(mIsPlayingRecording) {
            if(!mAudioRecorder.startPlaying(true)) {
                mIsPlayingRecording = false;
                return;
            }
            setFloatButtonView(mPlayRecordingBtn, R.color.playing_green, mIconPauseID);
        } else {
            setFloatButtonView(mPlayRecordingBtn, R.color.app_blue, mIconPlayID);
            mAudioRecorder.stopPlaying();
        }
    }

    private void clickPlayCorrect() {
        mIsPlayingCorrect = !mIsPlayingCorrect;
        if(mIsPlayingCorrect) {
            if(!mAudioRecorder.startPlaying(false)) {
                mIsPlayingCorrect = false;
                return;
            }
            setFloatButtonView(mPlayCorrectBtn, R.color.playing_green, mIconPauseID);
        } else {
            setFloatButtonView(mPlayCorrectBtn, R.color.app_orange, mIconPlayID);
            mAudioRecorder.stopPlaying();
        }
    }

    public void onPlayFinished(boolean recordedVersion) {
        ButtonFloat button = recordedVersion? mPlayRecordingBtn : mPlayCorrectBtn;
        int colorID = recordedVersion? R.color.app_blue : R.color.app_orange;
        setFloatButtonView(button, colorID, mIconPlayID);
        mAudioRecorder.stopPlaying();
    }

    private void setFloatButtonView(ButtonFloat button, int colorID, int drawableID) {
        button.setBackgroundColor(resources.getColor(colorID));
        button.getIcon().setImageResource(drawableID);
    }
}