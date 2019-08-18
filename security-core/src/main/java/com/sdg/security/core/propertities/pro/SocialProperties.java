package com.sdg.security.core.propertities.pro;

import lombok.Data;

@Data
public class SocialProperties {

    private String filterProcessesUrl = "/auth";
    private QQProperties qq = new QQProperties();

}
