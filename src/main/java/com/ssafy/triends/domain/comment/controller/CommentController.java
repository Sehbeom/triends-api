package com.ssafy.triends.domain.comment.controller;

import com.ssafy.triends.domain.comment.constant.CommentResponseMessage;
import com.ssafy.triends.domain.comment.model.CommentDto;
import com.ssafy.triends.domain.comment.service.CommentService;
import com.ssafy.triends.global.dto.ResponseDto;
import com.ssafy.triends.global.interceptor.LoginRequired;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;

@RestController
@RequestMapping("/comment")
@Api(tags = {"Comment"})
public class CommentController extends HttpServlet {
	private final Logger logger = LoggerFactory.getLogger(CommentController.class);
	
	private CommentService commentService;
	public CommentController(CommentService commentService) {
		super();
		this.commentService = commentService;
	}

	@GetMapping("/review")
	@ApiOperation(value="리뷰 내 모든 댓글 조회", notes = "하나의 리뷰 내 모든 댓글을 조회한다.")
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
	@ApiOperation(value="유저가 작성한 모든 댓글 조회 (LoginRequired)", notes = "현재 로그인한 유저가 작성한 모든 댓글을 조회한다.")
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
	@ApiOperation(value="댓글 작성 (LoginRequired)", notes = "댓글을 작성한다.")
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
	@ApiOperation(value="댓글 삭제 (LoginRequired)", notes = "댓글을 삭제한다.")
	public ResponseEntity<ResponseDto<?>> deleteOneComment(int commentId) throws Exception {
		commentService.deleteOneComment(commentId);

		return ResponseEntity.ok(
				ResponseDto.createResponse(CommentResponseMessage.DELETE_ONE_COMMENT_SUCCESS.getMessage())
		);
	}

}
