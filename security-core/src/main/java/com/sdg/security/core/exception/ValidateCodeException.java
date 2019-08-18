package com.sdg.security.core.exception;

import com.sdg.security.core.Enum.MessageEnum;
import org.springframework.security.core.AuthenticationException;

import java.io.Serializable;

public class ValidateCodeException extends AuthenticationException implements Serializable {

    public ValidateCodeException(String msg) {
        super(msg);
    }
    public ValidateCodeException(MessageEnum messageEnum) {
        super(messageEnum.getMessage());
    }
}
