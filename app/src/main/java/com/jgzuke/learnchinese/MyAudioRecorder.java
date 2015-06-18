package com.jgzuke.learnchinese;

import android.os.Environment;
import android.util.Log;
import android.media.MediaRecorder;
import android.media.MediaPlayer;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

/**
 * Created by jgzuke on 15-06-18.
 */
public class MyAudioRecorder {
    private static final String LOG_TAG = "MyAudioRecorder";
    private static final String FILE_CORRECT_PREFIX = "/lc_correct";
    private static final String FILE_RECORDED_PREFIX = "/lc_recorded";
    private static final String FILE_EXTENTION = ".3gpp";

    private static String mRecordedFileName;
    private static String mCorrectFileName;
    private MediaRecorder mRecorder = null;
    private MediaPlayer mPlayer = null;
    private static MainActivity mActivity;

    public MyAudioRecorder(MainActivity activity) {
        mActivity = activity;
        setFilePaths();
    }

    private static void setFilePaths() {
        String BASE = Environment.getExternalStorageDirectory().toString();
        mCorrectFileName = BASE + FILE_CORRECT_PREFIX + FILE_EXTENTION;
        mRecordedFileName = BASE + FILE_RECORDED_PREFIX + FILE_EXTENTION;
    }

    public boolean startRecording() {
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setOutputFile(mRecordedFileName);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mRecorder.prepare();
        } catch (IOException e) {
            mActivity.makeToast(R.string.error_record);
            Log.e(LOG_TAG, "startRecording() failed");
            e.printStackTrace();
            return false;
        }

        mRecorder.start();
        return true;
    }

    public boolean startPlaying(final boolean recordedVersion) {
        String fileName = recordedVersion? mRecordedFileName : mCorrectFileName;
        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(fileName);
            mPlayer.prepare();
            mPlayer.start();
            mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mActivity.onPlayFinished(recordedVersion);
                }
            });
        } catch (IOException e) {
            mActivity.makeToast(R.string.error_play);
            Log.e(LOG_TAG, "startPlaying() failed");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void stopRecording() {
        if(mRecorder == null) return;
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
    }

    public void stopPlaying() {
        if(mPlayer == null) return;
        mPlayer.release();
        mPlayer = null;
    }
}
