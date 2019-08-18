package com.sdg.security.core.propertities.pro;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SmsCodeProperties {
    //需要验证码的URL
    private List<String> url = new ArrayList<>();
    //验证码长度
    private int length = 6;
    //过期时间
    private int expireIn = 120;
}
