package com.test.musinsa.util;

import org.springframework.stereotype.Service;

@Service
public class Base62Module {
    private final String SHORT_URL_PREFIX = "http://localhost/";
    private final String ENCODE_BASE62 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    private String encoding(long param) {
        StringBuffer sb = new StringBuffer();
        while(param > 0) {
            sb.append(ENCODE_BASE62.charAt((int) (param % 62)));
            param /= 62;
        }
        return SHORT_URL_PREFIX + sb.toString();
    }

    public String urlEncoder(Integer seqStr) {
        String encodeStr = encoding(seqStr + 10000000*1000000);
        return encodeStr;
    }

}
