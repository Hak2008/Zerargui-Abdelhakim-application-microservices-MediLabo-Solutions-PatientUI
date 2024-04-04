package com.medilabosolutions.PatientUI.config;

import feign.*;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import feign.RequestInterceptor;
import feign.okhttp.OkHttpClient;

@Configuration
public class FeignConfig {

    @Value("${feign.httpclient.enabled}")
    private boolean feignHttpClientEnabled;

    @Value("${feign.client.config.default.cache.enabled}")
    private boolean feignClientCacheEnabled;

    @Bean
    public RequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("user", "user");
    }

    @Bean
    public OkHttpClient client() {
        if (!feignHttpClientEnabled) {
            return new OkHttpClient();
        } else {
            return null;
        }
    }

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate template) {
                template.header("Cache-Control", "no-cache");
            }
        };
    }

}

