package com.rk.springbootmysql.exception.auth;

// For missing or incorrect data during signup
public class SignupException extends RuntimeException {
    public SignupException(String message) {
        super(message);
    }
}