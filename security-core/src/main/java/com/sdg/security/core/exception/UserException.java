package com.sdg.security.core.exception;

import com.sdg.security.core.Enum.MessageEnum;

public class UserException extends RuntimeException {
    public UserException(String msg) {
        super(msg);
    }

    public UserException(MessageEnum msgEnum) {
        super(msgEnum.getMessage());
    }
}
