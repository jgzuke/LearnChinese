package com.jgzuke.learnchinese;

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
    private MyAudioRecorder mAudioRecorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecordBtn = (ButtonFloat) findViewById(R.id.menu_record_btn);
        mPlayRecordingBtn = (ButtonFloat) findViewById(R.id.menu_recording_btn);
        mPlayCorrectBtn = (ButtonFloat) findViewById(R.id.menu_correct_btn);
        mAudioRecorder = new MyAudioRecorder(this);
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
                mAudioRecorder.clickRecord();
                break;
            case R.id.menu_recording_btn:
                mAudioRecorder.clickPlayRecording();
                break;
            case R.id.menu_correct_btn:
                mAudioRecorder.clickPlayCorrect();
                break;
        }
    }
}