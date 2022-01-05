package com.src.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLValidator {

	public static final URLValidator INSTANCE = new URLValidator();
	
	private static final String URL_REGEX = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
	
	private static final Pattern pattern = Pattern.compile(URL_REGEX);
	
	private URLValidator() {}
	
	public boolean validateURL(String url) {
		Matcher m= pattern.matcher(url);
		return m.matches();
	}
}
