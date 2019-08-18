package com.sdg.security.core.validate.code.processor.impl;

import com.sdg.security.core.Enum.MessageEnum;
import com.sdg.security.core.exception.ValidateCodeException;
import com.sdg.security.core.validate.code.entity.Code;
import com.sdg.security.core.validate.code.sender.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.ServletRequest;

@Component("smsValidateCodeProcessor")
public class SmsValidateCodeProcessor extends AbstractValidateCodeProcessor<Code>{

    @Autowired
    private SmsCodeSender smsCodeSender;

    @Override
    protected void send(ServletWebRequest request, Code code) throws Exception {
        String mobile;
        try {
            mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), "mobile");
        }catch (Exception e){
            throw new ValidateCodeException(MessageEnum.SMS_CODE_MOBILE_NOT_NULL);
        }
        smsCodeSender.send(mobile,code.getCode());


    }
}
