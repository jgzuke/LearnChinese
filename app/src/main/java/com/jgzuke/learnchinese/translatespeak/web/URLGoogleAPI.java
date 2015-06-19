package com.jgzuke.learnchinese.translatespeak.web;

public enum URLGoogleAPI {
	TRANSLATE_TEXT(
			"http://translate.google.com.br/translate_a/t?client=t&multires=1&prev=btn&ssel=0&tsel=0&sc=1&hl=%s&sl=%s&tl=%s"), TRANSLATE_AUDIO(
			"http://translate.google.com/translate_tts?q=%s&tl=%s"), TRANSLATE_DETECT(
			"http://www.google.com/uds/GlangDetect?v=1.0&q=%s");

	private String url;

	private URLGoogleAPI(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}
}
