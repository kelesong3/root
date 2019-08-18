package com.sdg.security.demo.sender;

import com.sdg.security.core.validate.code.sender.SmsCodeSender;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DenoSmsSender implements SmsCodeSender {
    @Override
    public void send(String mobile, String code) {
        log.info("【DenoSmsSender发送短信验证码】,mobile:{},code:{}",mobile,code);
    }
}
