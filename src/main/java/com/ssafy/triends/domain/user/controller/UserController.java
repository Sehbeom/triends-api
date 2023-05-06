package com.ssafy.triends.domain.user.controller;

import com.ssafy.triends.domain.comment.model.CommentDto;
import com.ssafy.triends.domain.user.model.UserDto;
import com.ssafy.triends.domain.user.service.UserService;
import com.ssafy.triends.global.constant.SessionDataName;
import com.ssafy.triends.global.dto.ResponseDto;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONObject;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
	private final Logger logger = LoggerFactory.getLogger(UserController.class);
	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestParam Map<String, String> map, HttpSession session){
		Map<String, String> param=new HashMap<String, String>();
		System.out.println(map.toString());
		param.put("userId", map.get("userId"));
		param.put("userPwd", map.get("userPwd"));
		try {
			UserDto userDto=userService.loginUser(param);
			session.setAttribute(SessionDataName.USER_INFO.getName(), userDto);
			return ResponseEntity.ok(ResponseDto.createResponse("login", userDto));
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	@PostMapping("/logout")
	public ResponseEntity<ResponseDto<?>> logoutUser(HttpSession session){
		System.out.println("logoutSession : "+session.getAttribute(SessionDataName.USER_INFO.getName()));
		session.invalidate();
		return ResponseEntity.ok(ResponseDto.createResponse("logout"));
	}
	
	@GetMapping
	public ResponseEntity<ResponseDto<?>> getUser(HttpSession session){
		System.out.println("getUser : "+session.getAttributeNames());
		try {
			UserDto sessionDto=(UserDto)session.getAttribute(SessionDataName.USER_INFO.getName());
			System.out.println("sessionDto : "+sessionDto);
			return new ResponseEntity<ResponseDto<?>>(ResponseDto.createResponse("getUser", sessionDto), HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@PostMapping
	public ResponseEntity<ResponseDto<?>> joinUser(UserDto userDto){
		try {
			userService.joinUser(userDto);
			List<UserDto> list=userService.userList();
			return new ResponseEntity<ResponseDto<?>>(ResponseDto.createResponse("joinUser", list), HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	@PutMapping
	public ResponseEntity<?> modifyUser(UserDto userDto){
		try {
			userService.modifyUser(userDto);
			List<UserDto> list=userService.userList();
			return ResponseEntity.ok(ResponseDto.createResponse("modifyUser", list));
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@GetMapping("/comment")
	public ResponseEntity<?> getComment(HttpSession session){
		try {
			UserDto sessionDto=(UserDto)session.getAttribute(SessionDataName.USER_INFO.getName());
			int userId=sessionDto.getUserId();
			List<CommentDto> list=userService.getComment(userId);
			return ResponseEntity.ok(ResponseDto.createResponse("getComment", list));
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	@DeleteMapping("/comment/{commentId}")
	public ResponseEntity<?> deleteComment(@PathVariable("commentId") int commentId){
		try {
			userService.deleteComment(commentId);
			return ResponseEntity.ok(ResponseDto.createResponse("deleteComment", commentId));
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	@PostMapping("/preference")
	public ResponseEntity<?> registPreference(@RequestParam Map<String, Object> list, HttpSession session){
		UserDto sessionDto=(UserDto)session.getAttribute(SessionDataName.USER_INFO.getName());
		int userId=sessionDto.getUserId();
		System.out.println("userID : "+userId);
		System.out.println("list ::::: "+list);
		List<Map> prefList= (List<Map>) list.get("preferences");
		System.out.println("registPref ::::: "+prefList);
		try {
			for(Object pref:prefList){
				System.out.println("pref ::::: "+pref);
				int prefId=Integer.parseInt(String.valueOf(((JSONObject) pref).get("preference")));
				System.out.println("prefID ::::: "+prefId);
				Map<String, Integer> map=new HashMap<>();
				map.put("userId",userId);
				map.put("categoryId",prefId);
				userService.registPreference(map);
			}
			List<Integer>pList=userService.getPreference(userId);
			return ResponseEntity.ok(ResponseDto.createResponse("registPreference", pList));
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	@PutMapping("/preference")
	public ResponseEntity<?> modifyPreference(@RequestBody List<Integer> listPreference, HttpSession session){
		try {
			UserDto sessionDto=(UserDto)session.getAttribute(SessionDataName.USER_INFO.getName());
			int userId=(int)sessionDto.getUserId();
			userService.modifyPreference(userId, listPreference);
			List<Integer> list=userService.getPreference(userId);
			return ResponseEntity.ok(ResponseDto.createResponse("modifyPreference", list));
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	private ResponseEntity<ResponseDto<?>> exceptionHandling(Exception e) {
		e.printStackTrace();
		return (ResponseEntity<ResponseDto<?>>) ResponseEntity.notFound();
	}
}
