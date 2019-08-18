package com.sdg.security.core.social.qq.api.impl;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sdg.security.core.social.qq.api.QQ;
import com.sdg.security.core.social.qq.entity.QQUserInfo;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

@Slf4j
@Data
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {

    //获取openId
    private static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";

    //获取用户信息
    private static final String URL_GET_USERINFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";

    //appId
    private String appId;

    //openId
    private String openId;

    private ObjectMapper objectMapper = new ObjectMapper();


    public QQImpl(String accessToken ,String appId){
        super(accessToken, TokenStrategy.AUTHORIZATION_HEADER);
        this.appId = appId;
        String urlGetOpenId = String.format(URL_GET_OPENID,accessToken);
        String result = super.getRestTemplate().getForObject(urlGetOpenId, String.class);
        this.openId = StrUtil.subBetween(result,"openid\":\"","\"}");
        log.info("In QQImpl's Construtor,result = ,openId = {}",result,openId);
    }

    @Override
    public QQUserInfo getUserInfo(){
        try {
        String urlGetUserInfo = String.format(URL_GET_USERINFO,appId,openId);
        String result = getRestTemplate().getForObject(urlGetUserInfo,String.class);
        QQUserInfo qqUserInfo = objectMapper.readValue(result, QQUserInfo.class);
        qqUserInfo.setOpenId(openId);
        log.info("result = {},qqUserInfo = {}",result,qqUserInfo);
        return qqUserInfo;
        }catch (Exception ex){
            throw new RuntimeException("获取QQ用户信息失败");
        }
    }
}
