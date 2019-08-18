package com.sdg.security.browser.config;

import com.sdg.security.core.config.ValidateCodeFilterConfig;
import com.sdg.security.core.config.SmsAuthenticationSecurityConfig;
import com.sdg.security.core.constant.UrlConstant;
import com.sdg.security.core.handler.MyAuthFailureHandler;
import com.sdg.security.core.handler.MyAuthSuccessHandler;
import com.sdg.security.core.propertities.SecurityProperties;
import com.sdg.security.core.social.config.MySpringSocialConfigurer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Configuration
@Slf4j
public class BrowserConfig extends WebSecurityConfigurerAdapter {

    //编码器1
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    //编码器2
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //配置类
    @Autowired
    private SecurityProperties securityProperties;

    //登录成功处理器
    @Autowired
    private MyAuthSuccessHandler myAuthSuccessHandler;
    //登录失败处理器
    @Autowired
    private MyAuthFailureHandler myAuthFailureHandler;

    //数据源
    @Autowired
    private DataSource dataSource;

    //记住我的配置
    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        //jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;
    };

    @Autowired
    private UserDetailsService userDetailsService;

    //添加Sms的配置类
    @Autowired
    private SmsAuthenticationSecurityConfig smsAuthenticationSecurityConfig;

    //添加Social配置类
    @Autowired
    private MySpringSocialConfigurer mySpringSocialConfigurer;

    //添加validateCode配置类
    @Autowired
    private ValidateCodeFilterConfig validateCodeFilterConfig;

    //需要忽略的Url集合
    private List<String> ignoreUrls = new ArrayList<>();

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //添加需要忽略的Url
        this.addIgnoreUrls();
        http
                //引用validateCode配置类
                .apply(validateCodeFilterConfig)
                    .and()
                //引用Sms的配置类
                .apply(smsAuthenticationSecurityConfig)
                    .and()
                //引用Social的配置类
                .apply(mySpringSocialConfigurer)
                    .and()
                //登录页面
                .formLogin()
                    .loginPage(UrlConstant.DEFAULT_REQUEST_PROCESSING_URL)        //请求处理路径
                    .loginProcessingUrl(securityProperties.getBrowser().getLoginByFormProcessingUrl())    //  "/auth/form"
                    .successHandler(myAuthSuccessHandler)   //登录成功
                    .failureHandler(myAuthFailureHandler)   //登录失败
                    .and()
                        .rememberMe()
                            .tokenRepository(persistentTokenRepository())  //记住我的Repository
                            .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())   //token的有效秒数
                            .userDetailsService(userDetailsService)
                .and()
                .authorizeRequests()
                .antMatchers(this.ignoreUrls.toArray(new String[this.ignoreUrls.size()])).permitAll() //允许跳过检验
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable()

        ;
    }

    private void addIgnoreUrls() {
        this.ignoreUrls = securityProperties.getBrowser().getIgnoreUrls();
        //添加需要忽略的url
        this.ignoreUrls.add(UrlConstant.DEFAULT_REQUEST_PROCESSING_URL);
        this.ignoreUrls.add(UrlConstant.DEFAULT_GET_CODE_URL);
        this.ignoreUrls.add(UrlConstant.DEFAULT_LOGIN_PAGE_URL);
        this.ignoreUrls.add(UrlConstant.DEFAULT_SINGUP_PAGE_URL);
        log.info("已配置的忽略URLS = {}",this.ignoreUrls);
    }
}
