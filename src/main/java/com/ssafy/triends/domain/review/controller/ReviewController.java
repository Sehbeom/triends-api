package com.ssafy.triends.domain.review.controller;

import com.ssafy.triends.domain.review.constant.ReviewResponseMessage;
import com.ssafy.triends.domain.review.model.ReviewDto;
import com.ssafy.triends.domain.review.model.ReviewListItemDto;
import com.ssafy.triends.domain.review.service.ReviewService;
import com.ssafy.triends.global.dto.ResponseDto;
import com.ssafy.triends.global.interceptor.LoginRequired;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/review")
@Api(tags = {"리뷰 관리"})
public class ReviewController {
	private final Logger logger = LoggerFactory.getLogger(ReviewController.class);
	private ReviewService reviewService;

	public ReviewController(ReviewService reviewService) {
		super();
		this.reviewService = reviewService;
	}

	@GetMapping(value={"/list/{order}","list"})
	@ApiOperation(value = "리뷰 목록 조회", notes = "리뷰 목록 조회")
	@ApiImplicitParam(name = "order", value = "정렬 기준 (latest:최신순, likes:좋아요순, scrapped:스크랩순, subject:제목순)", dataTypeClass = Integer.class, defaultValue = "0")
	public ResponseEntity<?> reviewList(@PathVariable(required = false) String order){
		if(order == null || "".equals(order)){
			order="latest";
		}
		System.out.println("what order? ::: "+order+order.getClass());
		try {
			List<ReviewListItemDto> list = reviewService.orderedList(order);
			return ResponseEntity.ok(ResponseDto.createResponse(ReviewResponseMessage.GET_ORDEREDREVIEWLIST.getMessage(),list));
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@GetMapping("/detail")
	@ApiOperation(value = "리뷰 상세 정보 조회", notes = "리뷰 상세 정보 조회")
	@ApiImplicitParam(name = "reviewId", value = "조회할 리뷰 아이디", dataTypeClass = Integer.class, defaultValue = "1")
	public ResponseEntity<?> reviewDetail(@RequestParam Map<String, Object> userIdAndReviewAndWriterId){
		try {
			ReviewDto reviewDto=reviewService.detailReview(userIdAndReviewAndWriterId);
			return ResponseEntity.ok(ResponseDto.createResponse(ReviewResponseMessage.GET_REVIEW_DETAIL.getMessage(),reviewDto));
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	@PostMapping
	@LoginRequired
	@ApiOperation(value = "리뷰 작성", notes = "리뷰 작성 (로그인 필요)")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "subject", value = "제목", dataTypeClass = String.class, defaultValue = "제목", required = true),
			@ApiImplicitParam(name = "content", value = "내용", dataTypeClass = String.class, defaultValue = "내용", required = true),
			@ApiImplicitParam(name = "planId", value = "플랜 아이디", dataTypeClass = Integer.class, defaultValue = "1", required = true),
	})
	public ResponseEntity<?> writeReview(@RequestBody ReviewDto reviewDto){
		try {
			reviewService.writeReview(reviewDto);

			Map<String, Object> userIdAndReviewId = new HashMap<>();
			userIdAndReviewId.put("userId", reviewDto.getUserId());
			userIdAndReviewId.put("reviewId", reviewDto.getReviewId());

			return ResponseEntity.ok(ResponseDto.createResponse(ReviewResponseMessage.REGIST_REVIEW.getMessage(),
					reviewService.detailReview(userIdAndReviewId)));
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@GetMapping
	@LoginRequired
	@ApiOperation(value = "내가 쓴 리뷰 목록 조회", notes = "내가 쓴 리뷰 목록 조회 (로그인 필요)")
	public ResponseEntity<?> getMyReviews(int userId){
		try {
			List<ReviewDto>list = reviewService.myReviews(userId);
			return ResponseEntity.ok(ResponseDto.createResponse(ReviewResponseMessage.GET_MY_REVIEW.getMessage(),list));
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	@PutMapping
	@LoginRequired
	@ApiOperation(value = "리뷰 수정", notes = "리뷰 수정 (로그인 필요)")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "subject", value = "제목", dataTypeClass = String.class, defaultValue = "제목", required = true),
			@ApiImplicitParam(name = "content", value = "내용", dataTypeClass = String.class, defaultValue = "내용", required = true),
	})
	public ResponseEntity<?> modifyReview(@RequestBody ReviewDto reviewDto){
		try {
			reviewService.modifyReview(reviewDto);

			Map<String, Object> userIdAndReviewId = new HashMap<>();
			userIdAndReviewId.put("userId", reviewDto.getUserId());
			userIdAndReviewId.put("reviewId", reviewDto.getReviewId());

			return ResponseEntity.ok(
					ResponseDto.createResponse(ReviewResponseMessage.MODIFY_REVIEW.getMessage(),
							reviewService.detailReview(userIdAndReviewId)));
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@DeleteMapping
	@LoginRequired
	@ApiOperation(value = "리뷰 삭제", notes = "리뷰 삭제 (로그인 필요)")
	@ApiImplicitParam(name = "reviewId", value = "삭제할 리뷰 아이디", dataTypeClass = Integer.class, defaultValue = "1", required = true)
	public ResponseEntity<ResponseDto<?>> deleteReview(int reviewId)
			throws Exception {
		reviewService.deleteReview(reviewId);
		return ResponseEntity.ok(
				ResponseDto.createResponse(
						ReviewResponseMessage.DELETE_REVIEW.getMessage()
				)
		);
	}

	@GetMapping("/like")
	@LoginRequired
	@ApiOperation(value = "리뷰 좋아요", notes = "리뷰 좋아요 (로그인 필요)")
	@ApiImplicitParam(name = "reviewId", value = "좋아요 할 리뷰 아이디", dataTypeClass = Integer.class, defaultValue = "1", required = true)
	public ResponseEntity<ResponseDto<?>> likeReview(@RequestParam Map<String, Object> reviewAndUserId)
			throws Exception {
		reviewService.likeReview(reviewAndUserId);
		return ResponseEntity.ok(
				ResponseDto.createResponse(
						ReviewResponseMessage.ADD_LIKE_TO_REVIEW.getMessage()
				));
	}

	@GetMapping("/unlike")
	@LoginRequired
	@ApiOperation(value = "리뷰 좋아요 취소", notes = "리뷰 좋아요 취소 (로그인 필요)")
	@ApiImplicitParam(name = "reviewId", value = "좋아요 할 리뷰 아이디", dataTypeClass = Integer.class, defaultValue = "1", required = true)
	public ResponseEntity<ResponseDto<?>> unlikeReview(@RequestParam Map<String, Object> reviewAndUserId)
			throws Exception {
		reviewService.unlikeReview(reviewAndUserId);
		return ResponseEntity.ok(
				ResponseDto.createResponse(
						ReviewResponseMessage.UNLIKE_TO_REVIEW.getMessage()
				));
	}

	@GetMapping("/scrap")
	@LoginRequired
	public ResponseEntity<ResponseDto<?>> increaseReviewScrapped(int reviewId) throws Exception {
		reviewService.increaseReviewScrapped(reviewId);

		return ResponseEntity.ok(
				ResponseDto.createResponse(
						ReviewResponseMessage.INCREASE_REVIEW_SCRAPPED_SUCCESS.getMessage()
				)
		);
	}

	@PostMapping("/rate")
	@LoginRequired
	public ResponseEntity<ResponseDto<?>> rateAttraction(@RequestBody Map<String, Object> userAndContentAndReviewIdAndScore) throws Exception {
		return ResponseEntity.ok(
				ResponseDto.createResponse(
						ReviewResponseMessage.RATE_ATTRACTION_SUCCESS.getMessage(),
						reviewService.rateAttraction(userAndContentAndReviewIdAndScore)
				)
		);
	}

	@DeleteMapping("/rate")
	@LoginRequired
	public ResponseEntity<ResponseDto<?>> cancelRateAttraction(@RequestParam Map<String, Object> userAndContentAndReviewId) throws Exception {
		reviewService.deleteRateAttraction(userAndContentAndReviewId);
		return ResponseEntity.ok(
				ResponseDto.createResponse(
						ReviewResponseMessage.CANCEL_RATE_ATTRACTION_SUCCESS.getMessage()
				)
		);
	}

	private ResponseEntity<ResponseDto<?>> exceptionHandling(Exception e) {
		e.printStackTrace();
		return (ResponseEntity<ResponseDto<?>>) ResponseEntity.notFound();
	}
}
