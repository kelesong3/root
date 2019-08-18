package com.sdg.security.core.validate.code.generator.impl;

import com.google.code.kaptcha.Producer;
import com.sdg.security.core.propertities.SecurityProperties;
import com.sdg.security.core.validate.code.entity.ImageCode;
import com.sdg.security.core.validate.code.generator.ValidateCodeGenerator;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import java.awt.image.BufferedImage;

@Component("imageValidateCodeGenerator")
@Data
public class ImageValidateCodeGenerator implements ValidateCodeGenerator {

    @Autowired
    private Producer producer;

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public ImageCode generate(ServletWebRequest request) {
        String text = producer.createText();
        BufferedImage image = producer.createImage(text);
        return new ImageCode(image,text, securityProperties.getCode().getImage().getExpireIn());
    }
}
