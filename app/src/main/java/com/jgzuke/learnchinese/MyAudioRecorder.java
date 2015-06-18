package com.jgzuke.learnchinese;

import android.util.Log;
import android.media.MediaRecorder;
import android.media.MediaPlayer;

import java.io.IOException;

/**
 * Created by jgzuke on 15-06-18.
 */
public class MyAudioRecorder {
    private static final String LOG_TAG = "MyAudioRecorder";

    private static final String mRecordedFileName = "learn_chinese_recorded_sample";
    private static final String mCorrectFileName = "learn_chinese_correct_sample";
    private MediaRecorder mRecorder = null;
    private MediaPlayer mPlayer = null;
    private MainActivity mActivity;

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
            mRecorder.start();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }
    }

    public void startPlaying(boolean recordedVersion) {
        String fileName = recordedVersion? mRecordedFileName : mCorrectFileName;
        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(fileName);
            mPlayer.prepare();
            mPlayer.start();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
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
