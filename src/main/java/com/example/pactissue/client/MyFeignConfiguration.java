package com.example.pactissue.client;

import org.springframework.context.annotation.Bean;
import feign.httpclient.ApacheHttpClient;

public class MyFeignConfiguration {
    @Bean
    public feign.Client client() {
        return new ApacheHttpClient();
    }
}
