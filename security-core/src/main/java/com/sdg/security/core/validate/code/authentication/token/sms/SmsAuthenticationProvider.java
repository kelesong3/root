package com.sdg.security.core.validate.code.authentication.token.sms;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * 提供短信验证用户信息的处理逻辑
 */
@Data
@Slf4j
public class SmsAuthenticationProvider  implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    /**
     * 处理逻辑
     * @param authentication
     * @return
     * @throws AuthenticationException
     */
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        SmsAuthenticationToken asmsAuthenticationToken = (SmsAuthenticationToken) authentication;
        UserDetails user = userDetailsService.loadUserByUsername((String) asmsAuthenticationToken.getPrincipal());
        if(user == null){
            throw new InternalAuthenticationServiceException("无法获取用户信息,mobile = " + (String) asmsAuthenticationToken.getPrincipal());
        }
        SmsAuthenticationToken smsAuthenticationTokenResult = new SmsAuthenticationToken(user, user.getAuthorities());
        smsAuthenticationTokenResult.setDetails(asmsAuthenticationToken.getDetails());
        log.info("短信验证通过,auth = {}",smsAuthenticationTokenResult);
        return smsAuthenticationTokenResult;
    }

    /**
     * 判断传进来的authentication是不是SmsAuthenticationToken类型的
     * @param authentication
     * @return
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return SmsAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
