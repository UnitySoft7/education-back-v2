package com.system.education.auth.core.exception.response;

import org.springframework.security.core.AuthenticationException;

public class PasswordException extends AuthenticationException {
    public PasswordException(String message) {
        super(message);
    }
}