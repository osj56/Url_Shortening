package com.test.musinsa.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.stereotype.Service;

@Service
public class UrlValidation {
	private static final String urlRegexp = "(http:\\/\\/|https:\\/\\/)|(www.)?[a-zA-Z0-9]+.[a-zA-Z0-9]+.[a-z]{3}.?[a-z]+?";
	private static final Pattern pattern = Pattern.compile(urlRegexp);
	
	public boolean getUrlFormatChk(String inputUrl) {
		UrlValidator urlValidator = new UrlValidator();
		if(urlValidator.isValid(inputUrl))
			return true;

		if(inputUrl.contains("localhost"))
			return true;
		return false;
	}
}
