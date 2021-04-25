package com.test.musinsa.api;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.Description;

import com.test.musinsa.domain.UrlConverter;
import com.test.musinsa.repository.UrlConverterRepository;
import com.test.musinsa.service.UrlConverterService;
import com.test.musinsa.util.Base62Module;
import com.test.musinsa.util.UrlValidation;

public class ServiceTest {
	private Base62Module base62Module;
	private UrlConverterRepository urlConverterRepository;
	private UrlValidation urlValidation;
	private UrlConverterService urlConverterService;
	
	@Before
	public void setUp() {
		base62Module = new Base62Module();
		urlValidation = new UrlValidation();
		urlConverterService = new UrlConverterService(base62Module, urlConverterRepository, urlValidation);
	}
	
	public UrlConverter setEntity() {
		UrlConverter urlConverter = new UrlConverter();
		urlConverter.save("https://www.musinsa.com", "http://localhost/test", 1);
		return urlConverter;
	}
	
	@Test
	@Description("URL 포멧 형식 체크 ")
	public void url_Format_Check() {
		String inputUrl = "https://www.musinsa.com";
		String inputUrl2 = "http://musinsa.com";

		boolean chkResult = urlValidation.getUrlFormatChk(inputUrl);
		boolean chkResult2 = urlValidation.getUrlFormatChk(inputUrl2);

		assertThat(chkResult).isEqualTo(true);
		assertThat(chkResult2).isEqualTo(true);
	}
	
	@Test
	@Description("잘못된 URL 포멧 형식 체크 ")
	public void wrong_Url_Format_Check() {
		String inputUrl = "htt://www.musinsa.com";
		String inputUrl3 = "musinsa";

		boolean chkResult = urlValidation.getUrlFormatChk(inputUrl);
		boolean chkResult3 = urlValidation.getUrlFormatChk(inputUrl3);

		assertThat(chkResult).isEqualTo(false);
		assertThat(chkResult3).isEqualTo(false);	
	}
	
	@Test
	@Description("Base62 인코딩 테스트")
	public void base62_Encoding_Test() {
		Integer input = 10;
		String expectOutput = "http://localhost/myWEbB";
		
		String output = base62Module.urlEncoder(input);
		
		assertThat(expectOutput).isEqualTo(output);
	}
	
	@Test
	@Description("Base62 인코딩 고유 값 테스트")
	public void base62_Original_Encoding_Test() {
		Integer input = 10;
		Integer input2 = 11;
		
		String output = base62Module.urlEncoder(input);
		String output2 = base62Module.urlEncoder(input2);
		
		assertThat(output).isNotEqualTo(output2);
	}
	
	@Test
	@Description("원본 URL이 요청될 경우 반환되는 단축 URL 혹은 단축 URL이 요청될 경우 원본 URL 반환 확인 ")
	public void first_request_url() {
		String longUrl = "https://www.musinsa.com";
		String shortUrl = "http://localhost/test";
		
		String returnShortUrl = urlConverterService.isLongUrlOrShortUrl(setEntity(), longUrl);
		String returnLongUrl = urlConverterService.isLongUrlOrShortUrl(setEntity(), shortUrl);
		
		assertThat(returnShortUrl).isEqualTo(shortUrl);
		assertThat(returnLongUrl).isEqualTo(longUrl);
	}
	
}

