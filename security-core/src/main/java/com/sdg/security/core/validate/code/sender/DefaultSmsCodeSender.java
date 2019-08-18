package com.sdg.security.core.validate.code.sender;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultSmsCodeSender implements SmsCodeSender{
    @Override
    public void send(String mobile, String code) {
        log.info("【DefaultSmsCodeSender发送短信验证码】,mobile:{},code:{}",mobile,code);
    }
}
