package com.sdg.security.core.propertities.pro;

import lombok.Data;
import org.springframework.boot.autoconfigure.social.SocialProperties;
@Data
public class QQProperties extends SocialProperties {

    private String providerId = "qq";

}
