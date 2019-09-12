package com.opportunity.avitoparser.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class AppConfig {

    @Bean(name = "usedPrivateFiveHundred")
    public Map<String, String> laptopsSearchParams() {
        Map<String, String> params = new LinkedHashMap<>();
        params.put("pmax", "5000");
        params.put("s", "104");
        params.put("user", "1");

        return params;
    }
}
