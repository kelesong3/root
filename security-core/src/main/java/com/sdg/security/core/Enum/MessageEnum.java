package com.sdg.security.core.Enum;

public enum  MessageEnum {
    USER_NO_EXIST(185,"用户不存在"),




    CODE_ACCESS_FAILURE(11,"获取验证码的值失败"),
    CODE_CAN_NOT_NULL(11,"验证码不能为空"),
    CODE_NOT_EXIST(11,"验证码不存在"),
    CODE_IS_EXPIRED(11,"验证码已过期"),
    CODE_IS_WRANG(11,"验证码不正确"),

    SMS_CODE_MOBILE_NOT_NULL(12,"手机号不能为空"),

    ;
    private Integer code;
    private String message;
    private MessageEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    public Integer getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
}
