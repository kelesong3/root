package com.sdg.security.core.social.qq.entity;

import lombok.Data;

@Data
public class QQUserInfo {
    /**
     * {
     * "ret":0,
     * "msg":"",
     * "nickname":"Peter",
     * "figureurl":"http://qzapp.qlogo.cn/qzapp/111111/942FEA70050EEAFBD4DCE2C1FC775E56/30",
     * "figureurl_1":"http://qzapp.qlogo.cn/qzapp/111111/942FEA70050EEAFBD4DCE2C1FC775E56/50",
     * "figureurl_2":"http://qzapp.qlogo.cn/qzapp/111111/942FEA70050EEAFBD4DCE2C1FC775E56/100",
     * "figureurl_qq_1":"http://q.qlogo.cn/qqapp/100312990/DE1931D5330620DBD07FB4A5422917B6/40",
     * "figureurl_qq_2":"http://q.qlogo.cn/qqapp/100312990/DE1931D5330620DBD07FB4A5422917B6/100",
     * "gender":"男",
     * "is_yellow_vip":"1",
     * "vip":"1",
     * "yellow_vip_level":"7",
     * "level":"7",
     * "is_yellow_year_vip":"1"
     * }
     */

    //返回码
    private Integer ret;
    //如果ret<0，会有相应的错误信息提示，返回数据全部用UTF-8编码。
    private String msg;
    //QQ号
    private String openId;
    //用户在QQ空间的昵称。
    private String nickname;
    //大小为30×30像素的QQ空间头像URL。
    private String figureurl;
    //大小为50×50像素的QQ空间头像URL。
    private String figureurl_1;
    //大小为100×100像素的QQ空间头像URL。
    private String figureurl_2;
    //大小为40×40像素的QQ头像URL。
    private String figureurl_qq_1;
    //大小为100×100像素的QQ头像URL。需要注意，不是所有的用户都拥有QQ的100x100的头像，但40x40像素则是一定会有。
    private String figureurl_qq_2;
    //性别。 如果获取不到则默认返回"男"
    private String gender;
    //是否黄钻的信息
    private String is_yellow_vip;
    //是否vip
    private String vip;
    //年费黄钻的等级
    private String yellow_vip_level;
    //等级
    private String level;
    //用户是否年费黄钻的信息
    private String is_yellow_year_vip;



}
