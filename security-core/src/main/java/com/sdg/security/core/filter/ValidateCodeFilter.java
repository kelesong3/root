package com.sdg.security.core.filter;

import com.sdg.security.core.Enum.ValidateCodeTypeEnum;
import com.sdg.security.core.exception.ValidateCodeException;
import com.sdg.security.core.handler.MyAuthFailureHandler;
import com.sdg.security.core.propertities.SecurityProperties;
import com.sdg.security.core.validate.code.ValidateCodeProcessorHolder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Slf4j
@Data
@Component
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {


    /**
     * 需要在配置类中调用，给securityProperties赋值后，调用，不然urls为空
     * @throws ServletException
     */
    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        log.info("需要图片验证码的urls = {}",securityProperties.getCode().getImage().getUrl());
        log.info("需要短信验证码的urls = {}",securityProperties.getCode().getSms().getUrl());
        securityProperties.getCode().getImage().getUrl().stream().forEach(url ->{
            this.urlMap.put(url,ValidateCodeTypeEnum.IMAGE);
        });
        securityProperties.getCode().getSms().getUrl().stream().forEach(url ->{
            this.urlMap.put(url,ValidateCodeTypeEnum.SMS);
        });
        log.info("HashMap:urlMap = {}",this.urlMap);
    }

    //安全配置类
    @Autowired
    private SecurityProperties securityProperties;
    //url和验证类型Map
    private HashMap<String,ValidateCodeTypeEnum> urlMap = new HashMap<>();
    //登录失败处理类
    @Autowired
    private MyAuthFailureHandler myAuthFailureHandler;
    //Session存储工具类
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    //路径匹配器
    private AntPathMatcher antPathMatcher = new AntPathMatcher();
    //验证码处理类
    @Autowired
    private ValidateCodeProcessorHolder validateCodeProcessorHolder;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        //获取校验码的类型，如果当前请求不需要校验，则返回null
        ValidateCodeTypeEnum validateCodeType = getValidateCodeType(request);
        if(validateCodeType != null){
            try {
                log.info("需要验证码,uri = {},type = {}",request.getRequestURI(),validateCodeType.getType());
                validateCodeProcessorHolder.findValidateCodeProcessor(validateCodeType.getType()).validate(new ServletWebRequest(request));
            }catch (ValidateCodeException e){
                myAuthFailureHandler.onAuthenticationFailure(request,response,e);
                return;
            }
        }
        chain.doFilter(request,response);

    }

    /**
     * 获取校验码的类型，如果当前请求不需要校验，则返回null
     *
     * @param request
     * @return
     */
    private ValidateCodeTypeEnum getValidateCodeType(HttpServletRequest request) {
        ValidateCodeTypeEnum result = null;
        if (!StringUtils.equalsIgnoreCase(request.getMethod(), "get")) {
            Set<String> urls = urlMap.keySet();
            for (String url : urls) {
                if (antPathMatcher.match(url, request.getRequestURI())) {
                    result = urlMap.get(url);
                }
            }
        }
        return result;
    }
}
