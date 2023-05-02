package com.ssafy.triends.global.dto;

import lombok.Getter;

@Getter
public class ResponseDto<T> {
    private String message;
    private T data;

    public ResponseDto(String message, T data) {
        this.message = message;
        this.data = data;
    }

    public static <T> ResponseDto<T> createResponse(String message) {
        return new ResponseDto<>(message, null);
    }

    public static <T> ResponseDto<T> createResponse(String message, T data) {
        return new ResponseDto<>(message, data);
    }
}
