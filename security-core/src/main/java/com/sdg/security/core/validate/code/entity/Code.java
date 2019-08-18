package com.sdg.security.core.validate.code.entity;

import lombok.Data;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

@Data
public class Code {
    private String code;
    private LocalDateTime expireTime;

    public Code(String code, int expireIn){
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public Boolean isExpired(){
        return LocalDateTime.now().isAfter(expireTime);
    }
}
