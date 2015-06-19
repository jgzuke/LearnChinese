package com.jgzuke.learnchinese;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gc.materialdesign.views.ButtonFloat;
import com.jgzuke.learnchinese.translatespeak.Language;
import com.jgzuke.learnchinese.translatespeak.Translator;

/**
 * Created by jgzuke on 15-06-18.
 */
public class MainActivity extends ActionBarActivity implements OnClickListener, TextWatcher {
    private static final String LOG_TAG = "MainActivity";

    private static final int mIconPlayID = R.drawable.ic_play_arrow_white_36dp;
    private static final int mIconPauseID = R.drawable.ic_pause_white_36dp;
    private static final int mIconRecordID = R.drawable.ic_mic_white_36dp;
    private static final int mIconStopRecordID = R.drawable.ic_stop_white_36dp;
    private static final int mForwardIcon = R.drawable.ic_arrow_forward_white_24dp;
    private static final int mCheckIcon = R.drawable.ic_check_white_24dp;

    private ButtonFloat mRecordBtn;
    private ButtonFloat mPlayRecordingBtn;
    private ButtonFloat mPlayCorrectBtn;
    private ButtonFloat mTranslateBtn;

    private EditText mTranslateInput;
    private TextView mTranslateOutput;

    private String mTranslateInputString;
    private String mTranslateOutputString;

    private boolean mHasTranslated;

    private boolean mIsRecording = false;
    private boolean mIsPlayingRecording = false;
    private boolean mIsPlayingCorrect = false;
    private MyAudioRecorder mAudioRecorder;

    public Resources resources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        resources = getResources();

        setContentView(R.layout.activity_main);

        mRecordBtn = (ButtonFloat) findViewById(R.id.menu_record_btn);
        mPlayRecordingBtn = (ButtonFloat) findViewById(R.id.menu_recording_btn);
        mPlayCorrectBtn = (ButtonFloat) findViewById(R.id.menu_correct_btn);
        mTranslateBtn = (ButtonFloat) findViewById(R.id.menu_translate_btn);
        mTranslateInput = (EditText) findViewById(R.id.translate_input);

        mRecordBtn.setOnClickListener(this);
        mPlayRecordingBtn.setOnClickListener(this);
        mPlayCorrectBtn.setOnClickListener(this);
        mTranslateBtn.setOnClickListener(this);
        mTranslateInput.addTextChangedListener(this);

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
            case R.id.menu_translate_btn:
                translateText();
                break;
        }
    }

    private void translateText() {
        if(mHasTranslated == true) {
            return;
        }
        mHasTranslated = true;
        setFloatButtonView(mTranslateBtn, R.color.app_blue, mCheckIcon);
        mTranslateOutputString = Translator.translate(Language.ENGLISH, Language.CHINESE, mTranslateInputString);
        mTranslateOutput.setText(mTranslateOutputString);
    }

    private void invalidateText(String translationInput) {
        mTranslateInputString = translationInput;
        if(mHasTranslated == false) {
            return;
        }
        mHasTranslated = false;
        setFloatButtonView(mTranslateBtn, R.color.recording_red, mForwardIcon);
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

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        invalidateText((String) s);
    }

    @Override
    public void afterTextChanged(Editable s) {}
}