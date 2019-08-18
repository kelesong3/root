package com.sdg.security.core.validate.code.controller;

import com.google.code.kaptcha.Producer;
import com.sdg.security.core.validate.code.ValidateCodeProcessorHolder;
import com.sdg.security.core.validate.code.entity.Code;
import com.sdg.security.core.validate.code.entity.ImageCode;
import com.sdg.security.core.validate.code.sender.SmsCodeSender;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
public class ValidateCodeController {

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Autowired
    private ValidateCodeProcessorHolder validateCodeProcessorHolder;

    @Autowired
    private SmsCodeSender smsCodeSender;

    @Autowired
    private Producer producer;

    @ApiOperation(value = "获取验证码，图片(/code/image) + 短信(/code/sms)")
    @GetMapping("/code/{type}")
    public void createCode(HttpServletRequest request, HttpServletResponse response, @ApiParam("验证码类型:image或sms") @PathVariable String type)
            throws Exception {
        validateCodeProcessorHolder.findValidateCodeProcessor(type).create(new ServletWebRequest(request, response));
    }

}
