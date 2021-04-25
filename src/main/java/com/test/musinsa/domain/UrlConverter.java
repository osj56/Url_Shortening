package com.test.musinsa.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Entity
@Table(name = "url_converter")
public class UrlConverter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "long_url")
    private String longUrl;
    @Column(name = "short_url")
    private String shortUrl;
    @Column(name = "call_count")
    private Integer callCount;

    public void save(String longUrl, String shorUrl, Integer callCount){
        this.longUrl = longUrl;
        this.shortUrl = shorUrl;
        this.callCount = callCount;
    }

}
