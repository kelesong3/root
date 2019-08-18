package com.sdg.security.core.config;

import com.sdg.security.core.propertities.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCorePropertiesConfig {
}
