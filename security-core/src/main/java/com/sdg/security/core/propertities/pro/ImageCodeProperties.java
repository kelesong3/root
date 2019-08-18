package com.sdg.security.core.propertities.pro;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ImageCodeProperties {
    //需要验证码的URL
    private List<String> url = new ArrayList<>();
    //过期时间
    private int expireIn = 120;

    //图片长度
    private String width = "120";
    //图片高度
    private String height = "50";
   // 验证码字数
    private String length = "4";
    //有无边界
    private String border = "no";
    //字体颜色
    private String fontColor = "black";
    //字体大小
    private String size = "40";
    //字间空格
    private String charSpace = "6";

}
