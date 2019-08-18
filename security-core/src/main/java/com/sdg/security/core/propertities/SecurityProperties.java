package com.sdg.security.core.propertities;

import com.sdg.security.core.propertities.pro.BrowserProperties;
import com.sdg.security.core.propertities.pro.SocialProperties;
import com.sdg.security.core.propertities.pro.ValidateCodeProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "sdg.security")
@Data
public class SecurityProperties {

    private BrowserProperties browser = new BrowserProperties();
    private ValidateCodeProperties code = new ValidateCodeProperties();
    private SocialProperties social = new SocialProperties();

}
