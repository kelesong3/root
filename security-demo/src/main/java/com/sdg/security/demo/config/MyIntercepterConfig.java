package com.sdg.security.demo.config;

import com.sdg.security.demo.handler.MyIntercepterHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MyIntercepterConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private MyIntercepterHandler myIntercepter;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myIntercepter).addPathPatterns("/**");
    }
}
