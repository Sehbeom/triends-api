package com.ssafy.triends.domain.friends.controller;

import com.ssafy.triends.domain.friends.service.FriendsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FriendsController {
	private final Logger logger = LoggerFactory.getLogger(FriendsController.class);
	private FriendsService friendsService;

	public FriendsController(FriendsService friendsService) {
		super();
		this.friendsService = friendsService;
	}
	
}
