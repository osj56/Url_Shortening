package com.test.musinsa.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.musinsa.dto.UrlConverterDto;
import com.test.musinsa.service.UrlConverterService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UrlShorteningContoller {
	private final UrlConverterService urlConverterService;
	
    @GetMapping("/api/v1")
    public ResponseEntity<UrlConverterDto> urlShorteningApi(@RequestParam("url") String inputUrl){
    	return new ResponseEntity<UrlConverterDto>(urlConverterService.getResultUrl(inputUrl), HttpStatus.OK);
    }
}
