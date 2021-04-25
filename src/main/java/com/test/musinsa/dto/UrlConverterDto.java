package com.test.musinsa.dto;

import com.test.musinsa.service.MessageEnum;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Builder
@Getter @Setter
public class UrlConverterDto {
    private String url;
    private Integer callCount;
    private MessageEnum message;
}
