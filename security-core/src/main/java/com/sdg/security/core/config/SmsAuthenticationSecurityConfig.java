package com.sdg.security.core.config;

import com.sdg.security.core.handler.MyAuthFailureHandler;
import com.sdg.security.core.handler.MyAuthSuccessHandler;
import com.sdg.security.core.validate.code.authentication.token.sms.SmsAuthenticationFilter;
import com.sdg.security.core.validate.code.authentication.token.sms.SmsAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * 短信验证配置类
 * 需要在配置类中配置后才可生效
 */
@Component
public class SmsAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {


    @Autowired
    private MyAuthSuccessHandler myAuthSuccessHandler;
    @Autowired
    private MyAuthFailureHandler myAuthFailureHandler;
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //Filter
        SmsAuthenticationFilter smsAuthenticationFilter = new SmsAuthenticationFilter();
        smsAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        smsAuthenticationFilter.setAuthenticationSuccessHandler(myAuthSuccessHandler);
        smsAuthenticationFilter.setAuthenticationFailureHandler(myAuthFailureHandler);
        //Provider
        SmsAuthenticationProvider smsAuthenticationProvider = new SmsAuthenticationProvider();
        smsAuthenticationProvider.setUserDetailsService(userDetailsService);
        //配置Filter和Provider
        http
                .authenticationProvider(smsAuthenticationProvider)
                .addFilterAfter(smsAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
