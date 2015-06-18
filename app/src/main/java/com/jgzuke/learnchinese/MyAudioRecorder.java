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

    private static String mRecordedFileName = null;
    private static String mCorrectFileName = null;
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
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }

        mRecorder.start();
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
