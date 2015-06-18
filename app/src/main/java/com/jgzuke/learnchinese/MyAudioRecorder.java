package com.jgzuke.learnchinese;

import android.os.Bundle;
import android.os.Environment;
import android.view.ViewGroup;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.content.Context;
import android.util.Log;
import android.media.MediaRecorder;
import android.media.MediaPlayer;

import java.io.IOException;

/**
 * Created by jgzuke on 15-06-18.
 */
public class MyAudioRecorder {
    private static final String LOG_TAG = "MyAudioRecorder";

    private static String mRecordedFileName = null;
    private static String mCorrectFileName = null;
    private MediaRecorder mRecorder = null;
    private MediaPlayer mPlayer = null;
    private MainActivity mActivity;

    private boolean mIsRecording = false;
    private boolean mIsPlayingRecording = false;
    private boolean mIsPlayingCorrect = false;

    public MyAudioRecorder(MainActivity activity) {
        mActivity = activity;
    }

    public void startRecording() {
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setOutputFile(mRecordedFileName);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mRecorder.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }

        mRecorder.start();
    }

    public void startPlaying(String fileName) {
        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(fileName);
            mPlayer.prepare();
            mPlayer.start();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }
    }

    public void clickRecord() {
        if(mIsRecording) {
            startRecording();
        } else {
            stopRecording();
        }
    }

    public void clickPlayRecording() {
        if(mIsPlayingRecording) {
            startPlaying(mRecordedFileName);
        } else {
            stopPlaying();
        }
    }

    public void clickPlayCorrect() {
        if(mIsPlayingCorrect) {
            startPlaying(mCorrectFileName);
        } else {
            stopPlaying();
        }
    }

    public void stopRecording() {
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
    }

    public void stopPlaying() {
        mPlayer.release();
        mPlayer = null;
    }
}
