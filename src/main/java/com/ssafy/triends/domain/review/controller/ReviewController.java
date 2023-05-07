package com.ssafy.triends.domain.review.controller;

import com.ssafy.triends.domain.comment.model.CommentDto;
import com.ssafy.triends.domain.review.constant.ReviewResponseMessage;
import com.ssafy.triends.domain.review.model.ReviewDto;
import com.ssafy.triends.domain.review.service.ReviewService;
import com.ssafy.triends.domain.user.constant.UserResponseMessage;
import com.ssafy.triends.domain.user.model.UserDto;
import com.ssafy.triends.global.constant.SessionDataName;
import com.ssafy.triends.global.dto.ResponseDto;
import com.ssafy.triends.global.interceptor.LoginRequired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/review")
public class ReviewController {
	private final Logger logger = LoggerFactory.getLogger(ReviewController.class);
	private ReviewService reviewService;

	public ReviewController(ReviewService reviewService) {
		super();
		this.reviewService = reviewService;
	}
	@GetMapping(value={"/list/{order}","list"})
	public ResponseEntity<?> reviewList(@PathVariable(required = false) Integer order){
		if(order==null){
			order=0;
		}
		System.out.println("what order? ::: "+order+order.getClass());
		try {
			List<ReviewDto> list=null;
			list = reviewService.orderedList(Integer.parseInt(String.valueOf(order)));
			return ResponseEntity.ok(ResponseDto.createResponse(ReviewResponseMessage.GET_ORDEREDREVIEWLIST.getMessage(),list));
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	@GetMapping("/detail/{reviewId}")
	public ResponseEntity<?> reviewDetail(@PathVariable("reviewId") int reviewId){
		try {
			ReviewDto reviewDto=reviewService.detailReview(reviewId);
			return ResponseEntity.ok(ResponseDto.createResponse(ReviewResponseMessage.GET_REVIEW_DETAIL.getMessage(),reviewDto));
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	@PostMapping
	@LoginRequired
	public ResponseEntity<?> writeReview(ReviewDto reviewDto, HttpSession session){
		UserDto sessionDto=(UserDto)session.getAttribute(SessionDataName.USER_INFO.getName());
		int userId=sessionDto.getUserId();
		try {
			reviewDto.setUserId(userId);
			System.out.println(reviewDto);
			reviewService.writeReview(reviewDto);
			List<ReviewDto> list=reviewService.orderedList(0);
			return ResponseEntity.ok(ResponseDto.createResponse(ReviewResponseMessage.REGIST_REVIEW.getMessage(),list));
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	@PostMapping("detail/comment")
	@LoginRequired
	public ResponseEntity<?> registComment(CommentDto commentDto, HttpSession session){
		UserDto sessionDto=(UserDto)session.getAttribute(SessionDataName.USER_INFO.getName());
		int userId=sessionDto.getUserId();
		String name=sessionDto.getName();
		commentDto.setUserId(userId);
		commentDto.setName(name);
		try {
			reviewService.registComment(commentDto);
			int reviewId=commentDto.getReviewId();
			List<CommentDto> list = reviewService.getComment(reviewId);
			return ResponseEntity.ok(ResponseDto.createResponse(ReviewResponseMessage.REGIST_COMMENT.getMessage(),list));
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	@GetMapping
	@LoginRequired
	public ResponseEntity<?> getMyReview(HttpSession session){
		UserDto sessionDto=(UserDto)session.getAttribute(SessionDataName.USER_INFO.getName());
		int userId=sessionDto.getUserId();
		try {
			List<ReviewDto>list = reviewService.myReview(userId);
			return ResponseEntity.ok(ResponseDto.createResponse(ReviewResponseMessage.GET_MY_REVIEW.getMessage(),list));
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	@PutMapping
	@LoginRequired
	public ResponseEntity<?> modifyReview(int reviewId){
		try {
			ReviewDto reviewDto=reviewService.detailReview(reviewId);
			reviewService.modifyReview(reviewDto);
			List<ReviewDto> list=reviewService.orderedList(2);
			return ResponseEntity.ok(ResponseDto.createResponse(ReviewResponseMessage.MODIFY_REVIEW.getMessage(),list));
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	private ResponseEntity<ResponseDto<?>> exceptionHandling(Exception e) {
		e.printStackTrace();
		return (ResponseEntity<ResponseDto<?>>) ResponseEntity.notFound();
	}
}
