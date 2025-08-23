package com.system.education.auth.core.exception.response;

import org.springframework.security.core.AuthenticationException;

public class UsernameException extends AuthenticationException {
    public UsernameException(String message) {
        super(message);
    }
}