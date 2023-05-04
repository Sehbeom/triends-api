package com.ssafy.triends.domain.user.model;

import com.ssafy.triends.global.dto.ResponseDto;

public class UserDto{
	private int userId;
	private String id;
	private String password;
	private String name;
	private String tel;
	private String profileimg;
	private String email;
	private int isAdmin;

	public int getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}

	public UserDto() {
	}

	public UserDto(int userId, String id, String password, String name, String tel, String profileimg, String email) {
		super();
		this.userId = userId;
		this.id = id;
		this.password = password;
		this.name = name;
		this.tel = tel;
		this.profileimg = profileimg;
		this.email = email;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getProfileimg() {
		return profileimg;
	}

	public void setProfileimg(String profileimg) {
		this.profileimg = profileimg;
	}
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "UserDto [userId=" + userId + ", id=" + id + ", password=" + password + ", name=" + name + ", tel=" + tel
				+ ", profileimg=" + profileimg + ", email=" + email + ", isAdmin=" + isAdmin + "]";
	}


}
