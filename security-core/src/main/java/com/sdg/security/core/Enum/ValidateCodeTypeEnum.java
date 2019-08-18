package com.sdg.security.core.Enum;

public enum ValidateCodeTypeEnum {

    SMS("sms","短信"),
    IMAGE("image","图片"),
    ;
    private String type;
    private String message;
    private ValidateCodeTypeEnum(String type, String message) {
        this.type = type;
        this.message = message;
    }
    public String getType() {
        return type;
    }
    public String getMessage() {
        return message;
    }
}
