package com.sdg.security.core.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import com.sdg.security.core.propertities.SecurityProperties;
import com.sdg.security.core.validate.code.sender.DefaultSmsCodeSender;
import com.sdg.security.core.validate.code.sender.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class OtherBeanConfig {

    /**
     * 配置Property
     */
    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 图片验证码的配置Bean
     * @return
     */
    @Bean
    public DefaultKaptcha producer() {
        Properties properties = new Properties();
        properties.put("kaptcha.border", securityProperties.getCode().getImage().getBorder());
        // 验证码字体颜色
        properties.put("kaptcha.textproducer.font.color", securityProperties.getCode().getImage().getFontColor());
        // 验证码字体大小
        properties.put("kaptcha.textproducer.font.size", securityProperties.getCode().getImage().getSize());
        // 验证码文本字符间距
        properties.put("kaptcha.textproducer.char.space", securityProperties.getCode().getImage().getCharSpace());
        // 验证码宽度
        properties.put("kaptcha.image.width", securityProperties.getCode().getImage().getWidth());
        // 验证码高度
        properties.put("kaptcha.image.height", securityProperties.getCode().getImage().getHeight());
        properties.put("kaptcha.textproducer.char.length", securityProperties.getCode().getImage().getLength());
        Config config = new Config(properties);
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }

    /**
     * 发送短信的默认实现
     * 当Context里有对SmsCodeSender接口的实现Bean时不装载此Bean
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeSender smsCodeSender(){
        return new DefaultSmsCodeSender();
    }


}
