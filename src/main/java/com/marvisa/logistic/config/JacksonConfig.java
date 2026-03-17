package com.marvisa.logistic.config;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class JacksonConfig {

    @Bean
    public JsonMapper jacksonBuilder() {
        var builder = JsonMapper.builder();
        builder.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        return builder.build();
    }
}