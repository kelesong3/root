package com.sdg.security.demo.config;

import com.sdg.security.demo.sender.DenoSmsSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public DenoSmsSender denoSmsSender(){return new DenoSmsSender();}

}
