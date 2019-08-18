package com.sdg.security.core.validate.code.generator.impl;

import com.sdg.security.core.propertities.SecurityProperties;
import com.sdg.security.core.validate.code.entity.Code;
import com.sdg.security.core.validate.code.generator.ValidateCodeGenerator;
import lombok.Data;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

@Component("smsValidateCodeGenerator")
@Data
public class SmsValidateCodeGenerator implements ValidateCodeGenerator {
    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public Code generate(ServletWebRequest request) {
        String code = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
        return new Code(code,securityProperties.getCode().getSms().getExpireIn());
    }
}
