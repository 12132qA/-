package com.wx_back.utils;

import org.apache.commons.httpclient.HttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class HttpUtil {
    @Bean
    public HttpClient tryHttp(){

        return new HttpClient();
    }
}
