package com.system.education.auth.core.exception.response;

import org.springframework.security.core.AuthenticationException;

public class DeactivateException extends AuthenticationException {
    public DeactivateException(String message){
        super(message);
    }
}
