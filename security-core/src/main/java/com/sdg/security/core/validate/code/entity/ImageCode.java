package com.sdg.security.core.validate.code.entity;

import lombok.Data;

import java.awt.image.BufferedImage;

@Data
public class ImageCode extends Code{
    private BufferedImage image;

    public ImageCode(BufferedImage image,String code,int expireIn){
        super(code,expireIn);
        this.image = image;
    }

}
