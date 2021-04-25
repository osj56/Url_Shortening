package com.test.musinsa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.musinsa.domain.UrlConverter;

public interface UrlConverterRepository extends JpaRepository<UrlConverter, Integer> {
	UrlConverter findByLongUrlOrShortUrl(String longUrl, String shortUrl);
}
