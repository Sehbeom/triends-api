package com.ssafy.triends.domain.user.model;

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
public class UserDto{
	private int userId;
	private String id;
	private String password;
	private String name;
	private String tel;
	private String profileimg;
	private String email;
	private int isAdmin;
}
