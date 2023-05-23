package com.ssafy.triends.global.error.exception;

public class UserInfoNotFoundException extends RuntimeException {

    public UserInfoNotFoundException() {
    }

    public UserInfoNotFoundException(String message) {
        super(message);
    }
}
