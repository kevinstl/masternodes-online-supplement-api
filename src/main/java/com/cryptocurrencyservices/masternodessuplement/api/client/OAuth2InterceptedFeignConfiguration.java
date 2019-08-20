package com.cryptocurrencyservices.masternodessuplement.api.client;

import java.io.IOException;

import org.springframework.context.annotation.Bean;

import feign.RequestInterceptor;

import com.cryptocurrencyservices.masternodessuplement.api.security.oauth2.AuthorizationHeaderUtil;

public class OAuth2InterceptedFeignConfiguration {

    @Bean(name = "oauth2RequestInterceptor")
    public RequestInterceptor getOAuth2RequestInterceptor(AuthorizationHeaderUtil authorizationHeaderUtil) throws IOException {
        return new TokenRelayRequestInterceptor(authorizationHeaderUtil);
    }
}
