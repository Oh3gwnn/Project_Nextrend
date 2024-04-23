package com.oh3g.nextrend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NaverApiConfig {
    @Value("${NAVER_API_CLIENT_ID}")
    private String clientId;

    @Value("${NAVER_API_CLIENT_SECRET}")
    private String clientSecret;

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }
}