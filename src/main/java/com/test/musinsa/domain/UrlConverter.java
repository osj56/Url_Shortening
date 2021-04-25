package com.test.musinsa.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Entity
@Table(name = "URL_CONVERTER")
public class UrlConverter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String longUrl;
    private String shortUrl;
    private Integer callCount;

    public void save(String longUrl, String shorUrl, Integer callCount){
        this.longUrl = longUrl;
        this.shortUrl = shorUrl;
        this.callCount = callCount;
    }

}
