package com.sdg.security.core.propertities.pro;

import com.sdg.security.core.constant.UrlConstant;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BrowserProperties {


    private String signUpUrl = "/imooc-signUp.html";        //默认的注册页面
    private String loginPage = "/imooc-signIn.html";        //默认的登录页面
    private String loginByFormProcessingUrl = UrlConstant.DEFAULT_FORM_LOGIN_URL;       //默认表单登录请求路径
    private String loginBySmsProcessingUrl = UrlConstant.DEFAULT_SMS_LOGIN_URL;         //默认短信登录请求路径
    private int rememberMeSeconds = 60*60*24*7;              //token有效时间一周
    private List<String> ignoreUrls = new ArrayList<>();      //跳过权限校验的路径
}
