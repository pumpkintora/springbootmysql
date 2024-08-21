package com.rk.springbootmysql.exception;

import com.rk.springbootmysql.controller.AuthController;
import com.rk.springbootmysql.exception.auth.LoginException;
import com.rk.springbootmysql.exception.auth.SignupException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@ControllerAdvice
public class AuthExceptionHandler {

    @ExceptionHandler(SignupException.class)
    public ResponseEntity<String> handleSignupException(SignupException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(LoginException.class)
    public ResponseEntity<String> handleLoginException(LoginException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    // Handle other exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

// Auth-related exception handler
//@RestControllerAdvice(assignableTypes = AuthController.class)
//public class AuthExceptionHandler {
//
//    @ExceptionHandler(AuthenticationException.class)
//    public ResponseEntity<String> handleAuthenticationException(AuthenticationException ex) {
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Wrong credentials.");
//    }
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<String> handleGeneralException(Exception ex) {
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred in AuthController.");
//    }
//}