package com.ssafy.triends.domain.comment.controller;

import com.ssafy.triends.domain.comment.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;

@RestController
public class CommentController extends HttpServlet {
	private final Logger logger = LoggerFactory.getLogger(CommentController.class);
	
	private CommentService commentService;
	public CommentController(CommentService commentService) {
		super();
		this.commentService = commentService;
	}
}
