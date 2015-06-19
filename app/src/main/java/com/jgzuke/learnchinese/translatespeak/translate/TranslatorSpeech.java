package com.jgzuke.learnchinese.translatespeak.translate;

import java.io.IOException;
import java.net.URLEncoder;

import android.media.AudioManager;
import android.media.MediaPlayer;

import com.jgzuke.learnchinese.translatespeak.Language;
import com.jgzuke.learnchinese.translatespeak.web.URLGoogleAPI;

public class TranslatorSpeech implements Translatable {
	private String text;
	@SuppressWarnings("unused")
	private Language from;

	public TranslatorSpeech(String text, Language from) {
		this.text = text;
	}

	@SuppressWarnings("deprecation")
	public String getUrl() {
		String format = URLGoogleAPI.TRANSLATE_AUDIO.getUrl();
		String textEncoded = URLEncoder.encode(text);
		String url = String.format(format, textEncoded, "pt-BR");
		return url;
	}

	public void speack() {
		try {
			MediaPlayer mediaPlayer = new MediaPlayer();
			mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			mediaPlayer.setDataSource(getUrl());
			mediaPlayer.prepare();
			mediaPlayer.start();
		} catch (IOException e) {
			throw new RuntimeException();
		}

	}
}
