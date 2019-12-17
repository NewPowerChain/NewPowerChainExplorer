package com.reptile;

import feign.codec.Encoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignEncoderConfig {

    @Bean
    public Encoder encoder(){
        return new FeignFormSupportEncoder();
    }
}