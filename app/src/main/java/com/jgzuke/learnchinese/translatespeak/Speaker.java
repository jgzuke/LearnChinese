package com.jgzuke.learnchinese.translatespeak;

import android.content.Context;

import com.jgzuke.learnchinese.translatespeak.translate.OnCompleteLoad;
import com.jgzuke.learnchinese.translatespeak.translate.TranslatorSpeech;

public class Speaker {

	public static void speak(final String text, final Language from,
			final Context context) {
		TranslatorSpeech translatorSpeech = new TranslatorSpeech(text, from);
		translatorSpeech.speack();
	}

	public static void speak(final String text, final Language from,
			final Context context, final OnCompleteLoad onCompleteLoad) {
		new Thread() {
			public void run() {
				TranslatorSpeech translatorSpeech = new TranslatorSpeech(text, from);
				translatorSpeech.speack();
				onCompleteLoad.onCompleteLoaded();
			}
		}.start();
	}
}
