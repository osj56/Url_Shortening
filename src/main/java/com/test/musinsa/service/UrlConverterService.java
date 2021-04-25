package com.test.musinsa.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.musinsa.domain.UrlConverter;
import com.test.musinsa.dto.UrlConverterDto;
import com.test.musinsa.repository.UrlConverterRepository;
import com.test.musinsa.util.Base62Module;
import com.test.musinsa.util.UrlValidation;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UrlConverterService {
    private final Base62Module base62Module;
    private final UrlConverterRepository urlConverterRepository;
    private final UrlValidation urlValidation;
    
    public UrlConverterDto getResultUrl(String inputUrl) {
        if(!isValidTypeOfUrl(inputUrl)) {
            return UrlConverterDto.builder()
            			.url("")
            			.callCount(0)
            			.message(MessageEnum.NOT_SUPPORTED_FORMAT_OF_URL)
            			.build();
        }
        return findResultUrl(inputUrl);
    }
    
    @Transactional(readOnly = true)
    public UrlConverterDto findResultUrl(String inputUrl) {
        UrlConverter entity = urlConverterRepository.findByLongUrlOrShortUrl(inputUrl, inputUrl);
        if(entity == null) {
            entity = saveNewUrlInfo(inputUrl);
        }else {
            saveCallCount(entity);
        }

        return getDtoInfo(entity, inputUrl);
    }

    public UrlConverterDto getDtoInfo(UrlConverter entity, String inputUrl) {
        return UrlConverterDto.builder()
        			.url(isLongUrlOrShortUrl(entity, inputUrl))
        			.callCount(entity.getCallCount())
        			.message(MessageEnum.SUPPORTED_FORMAT_OF_URL)
        			.build();
    }
    
    public boolean isValidTypeOfUrl(String inputUrl) {
        return urlValidation.getUrlFormatChk(inputUrl);
    }

    public String isLongUrlOrShortUrl(UrlConverter entity, String inputUrl) {
        return entity.getLongUrl().equals(inputUrl) ? entity.getShortUrl() : entity.getLongUrl();
    }
    
    public String getNewShorteningUrl(Integer inputId) {
        return base62Module.urlEncoder(inputId);
    }
    
    @Transactional
    public UrlConverter saveNewUrlInfo(String inputUrl) {
        UrlConverter entity= new UrlConverter();
        entity.save(inputUrl, null, 1);
        entity = urlConverterRepository.save(entity);
        return saveNewUrl(entity, inputUrl);
    }

    @Transactional
    public UrlConverter saveNewUrl(UrlConverter entity, String inputUrl) {
        entity.save(inputUrl, getNewShorteningUrl(entity.getId()), entity.getCallCount());
        entity = urlConverterRepository.save(entity);
        return entity;
    }
    
    @Transactional
    public void saveCallCount(UrlConverter entity) {
        entity.save(entity.getLongUrl(), entity.getShortUrl(), entity.getCallCount() + 1);
        urlConverterRepository.save(entity);
    }

}