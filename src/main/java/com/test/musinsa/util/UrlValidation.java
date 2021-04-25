package com.test.musinsa.util;

import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.stereotype.Service;

@Service
public class UrlValidation {
	
	public boolean getUrlFormatChk(String inputUrl) {
		UrlValidator urlValidator = new UrlValidator();
		if(urlValidator.isValid(inputUrl))
			return true;

		if(inputUrl.contains("localhost"))
			return true;
		return false;
	}
}
