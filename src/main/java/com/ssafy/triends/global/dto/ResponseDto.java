package com.ssafy.triends.global.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
@ApiModel(value = "ResponseDto(응답)", description = "api 응답 형식의 일관성을 위한 Dto이다.")
public class ResponseDto<T> {
    @ApiModelProperty(value = "응답 메세지")
    private String message;
    @ApiModelProperty(value = "응답 데이터")
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

	@Override
	public String toString() {
		return "ResponseDto [message=" + message + ", data=" + data + "]";
	}
    
    
}
