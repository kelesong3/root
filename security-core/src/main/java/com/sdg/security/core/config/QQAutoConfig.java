package com.sdg.security.core.config;

import com.sdg.security.core.propertities.SecurityProperties;
import com.sdg.security.core.propertities.pro.QQProperties;
import com.sdg.security.core.propertities.pro.SocialProperties;
import com.sdg.security.core.social.qq.connect.QQConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

/**
 * 为连接工厂设置需要的值
 */

@Configuration
@Slf4j
@ConditionalOnProperty(prefix = "sdg.security.social.qq",name = "appId")
public class QQAutoConfig extends SocialAutoConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        QQProperties qqConfig = securityProperties.getSocial().getQq();
        log.info("In QQAutoConfig: appId = {},AppSecret = {},providerId = {}",qqConfig.getAppId(),qqConfig.getAppSecret(),qqConfig.getProviderId());
        return new QQConnectionFactory(qqConfig.getProviderId(),qqConfig.getAppId(), qqConfig.getAppSecret());
    }
}
