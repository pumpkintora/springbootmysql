package com.rk.springbootmysql.exception.auth;

// For incorrect login credentials
public class LoginException extends RuntimeException {
    public LoginException(String message) {
        super(message);
    }
}