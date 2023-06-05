package com.ssafy.triends.domain.user.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "UserDto(유저)", description = "유저의 상세 정보를 담고 있다.")
public class UserDto{
	@ApiModelProperty(value = "유저 pk")
	private int userId;
	@ApiModelProperty(value = "유저 아이디 (로그인)")
	private String id;
	@ApiModelProperty(value = "유저 비밀번호 (로그인)")
	private String password;
	@ApiModelProperty(value = "유저 이름")
	private String name;
	@ApiModelProperty(value = "유저 전화번호")
	private String tel;
	@ApiModelProperty(value = "유저 프로필 사진")
	private String profileimg;
	@ApiModelProperty(value = "유저 이메일")
	private String email;
	@ApiModelProperty(value = "유저 관리자 여부")
	private int isAdmin;
}
