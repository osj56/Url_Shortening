package com.test.musinsa.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.test.musinsa.service.UrlConverterService;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class ApiTest {
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	UrlConverterService urlConverterService;
		
	@Test
	@Description("정상적으로 API 호출하는 테스트")
	@Transactional
	public void createEvent() throws Exception {
		String inputUrl = "https://www.musinsa.com";
		
		mockMvc.perform(get("/api/v1")
				.contentType(MediaType.APPLICATION_JSON)
				.param("url", inputUrl))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("url").isNotEmpty())
				.andExpect(jsonPath("callCount").isNotEmpty())
				.andExpect(jsonPath("message").isNotEmpty())
				.andExpect(jsonPath("message").value("SUPPORTED_FORMAT_OF_URL"))
				;
	}
	
	@Test
	@Description("값이 없는 상태로 API 호출했을 경우에도 정상반환")
	@Transactional
	public void createEvent_Bad_Input_Request() throws Exception {
		String inputUrl = "";

		mockMvc.perform(get("/api/v1")
				.contentType(MediaType.APPLICATION_JSON)
				.param("url", inputUrl))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("message").value("NOT_SUPPORTED_FORMAT_OF_URL_CHECK_HTTPS_OR_HTTP"))
				;
	}
	
	@Test
	@Description("두번 호출 했을 때의 카운트 개수 확인 ")
	@Transactional
	public void createEvent_Call_Twice_Count() throws Exception {
		String inputUrl = "https://www.musinsa.com";
		mockMvc.perform(get("/api/v1")
				.contentType(MediaType.APPLICATION_JSON)
				.param("url", inputUrl))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("message").value("SUPPORTED_FORMAT_OF_URL"));
		
		mockMvc.perform(get("/api/v1")
				.contentType(MediaType.APPLICATION_JSON)
				.param("url", inputUrl))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("message").value("SUPPORTED_FORMAT_OF_URL"))
				.andExpect(jsonPath("callCount").value("2"))
				;
	}
}
