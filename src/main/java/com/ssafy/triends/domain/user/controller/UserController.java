package com.ssafy.triends.domain.user.controller;

import com.ssafy.triends.domain.comment.model.CommentDto;
import com.ssafy.triends.domain.user.model.UserDto;
import com.ssafy.triends.domain.user.service.UserService;
import com.ssafy.triends.global.dto.ResponseDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.server.handler.ExceptionHandlingWebHandler;

@RestController
public class UserController {
	private final Logger logger = LoggerFactory.getLogger(UserController.class);
	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	
	
	@PostMapping("/user/login")
	public ResponseEntity<?> loginUser(@RequestParam("userInfo") Map<String, String> map){
		Map<String, String> param=new HashMap<String, String>();
		param.put("userId", map.get("userId"));
		param.put("userPwd", map.get("userPwd"));
		try {
			UserDto userDto=userService.loginUser(param);
			return ResponseEntity.ok(ResponseDto.createResponse("login", userDto));
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	@PostMapping("/user/logout")
	public ResponseEntity<?> logoutUser(HttpSession session){
		session.invalidate();
		return ResponseEntity.ok(ResponseDto.createResponse("logout"));
	}
	
	@GetMapping("/user")
	public ResponseEntity<?> getUser(HttpSession session){
		try {
			UserDto sessionDto=(UserDto)session.getAttribute("userDto");
			UserDto userDto=userService.getUser((String)sessionDto.getId());
			return ResponseEntity.ok(ResponseDto.createResponse("getUser", userDto));
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@PostMapping("/user")
	public ResponseEntity<?> joinUser(@RequestBody UserDto userDto){
		try {
			userService.joinUser(userDto);
			List<UserDto> list=userService.userList();
			return ResponseEntity.ok(ResponseDto.createResponse("joinUser", list));
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	@PutMapping("/user")
	public ResponseEntity<?> modifyUser(@RequestBody UserDto userDto){
		try {
			userService.modifyUser(userDto);
			List<UserDto> list=userService.userList();
			return ResponseEntity.ok(ResponseDto.createResponse("modifyUser", list));
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@GetMapping("/user/comment")
	public ResponseEntity<?> getComment(HttpSession session){
		try {
			List<CommentDto> list=userService.getComment((String)session.getAttribute("userId"));
			return ResponseEntity.ok(ResponseDto.createResponse("getComment", list));
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	@DeleteMapping("/user/comment/{commentId}")
	public ResponseEntity<?> deleteComment(@PathVariable("commentId") int commentId){
		try {
			userService.deleteComment(commentId);
			return ResponseEntity.ok(ResponseDto.createResponse("deleteComment", commentId));
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	@PostMapping("/user/preference")
	public ResponseEntity<?> registPreference(@RequestBody List<Integer> listPreference, HttpSession session){
		try {
			userService.registPreference(listPreference);
			UserDto sessionDto=(UserDto)session.getAttribute("userDto");
			List<Integer> list=userService.getPreference((int)sessionDto.getUserId());
			return ResponseEntity.ok(ResponseDto.createResponse("registPreference", list));
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	@PutMapping("/user/preference")
	public ResponseEntity<?> modifyPreference(@RequestBody List<Integer> listPreference, HttpSession session){
		try {
			UserDto sessionDto=(UserDto)session.getAttribute("userDto");
			int userId=(int)sessionDto.getUserId();
			userService.modifyPreference(userId, listPreference);
			List<Integer> list=userService.getPreference(userId);
			return ResponseEntity.ok(ResponseDto.createResponse("modifyPreference", list));
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
