package com.ssafy.triends.domain.comment.controller;

import com.ssafy.triends.domain.comment.constant.CommentResponseMessage;
import com.ssafy.triends.domain.comment.model.CommentDto;
import com.ssafy.triends.domain.comment.service.CommentService;
import com.ssafy.triends.global.dto.ResponseDto;
import com.ssafy.triends.global.interceptor.LoginRequired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;

@RestController
@RequestMapping("/comment")
public class CommentController extends HttpServlet {
	private final Logger logger = LoggerFactory.getLogger(CommentController.class);
	
	private CommentService commentService;
	public CommentController(CommentService commentService) {
		super();
		this.commentService = commentService;
	}

	@GetMapping("/review")
	public ResponseEntity<ResponseDto<?>> getAllCommentsOfOneReview(int reviewId) throws Exception {
		return ResponseEntity.ok(
				ResponseDto.createResponse(
						CommentResponseMessage.GET_ALL_COMMENTS_OF_ONE_REVIEW_SUCCESS.getMessage(),
						commentService.getAllCommentsOfOneReview(reviewId)
				)
		);
	}

	@GetMapping("/user")
	@LoginRequired
	public ResponseEntity<ResponseDto<?>> getAllCommentsOfOneUser(int userId) throws Exception {
		return ResponseEntity.ok(
				ResponseDto.createResponse(
						CommentResponseMessage.GET_ALL_COMMENTS_OF_ONE_USER_SUCCESS.getMessage(),
						commentService.getAllCommentsOfOneUser(userId)
				)
		);
	}

	@PostMapping
	@LoginRequired
	public ResponseEntity<ResponseDto<?>> registOneComment(@RequestBody CommentDto commentDto) throws Exception {
		commentService.registOneComment(commentDto);

		return ResponseEntity.ok(
				ResponseDto.createResponse(
						CommentResponseMessage.REGIST_ONE_COMMENT_SUCCESS.getMessage(),
						commentService.getOneComment(commentDto.getCommentId())
				)
		);
	}

	@DeleteMapping
	@LoginRequired
	public ResponseEntity<ResponseDto<?>> deleteOneComment(int commentId) throws Exception {
		commentService.deleteOneComment(commentId);

		return ResponseEntity.ok(
				ResponseDto.createResponse(CommentResponseMessage.DELETE_ONE_COMMENT_SUCCESS.getMessage())
		);
	}

}
