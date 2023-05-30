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
@Api(tags = {"Review"})
public class ReviewController {
	private final Logger logger = LoggerFactory.getLogger(ReviewController.class);
	private ReviewService reviewService;

	public ReviewController(ReviewService reviewService) {
		super();
		this.reviewService = reviewService;
	}

	@GetMapping(value={"/list/{order}","list"})
	@ApiOperation(value = "리뷰 목록 조회", notes = "리뷰 목록 조회")
	@ApiImplicitParam(name = "order", value = "정렬 기준 (latest:최신순, likes:좋아요순, scrapped:스크랩순, subject:제목순)", dataTypeClass = String.class, defaultValue = "latest")
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
	@ApiImplicitParam(name = "userIdAndReviewAndWriterId",
			value = "userId : 로그인한 유저의 pk \n reviewId : 조회할 리뷰의 pk \n writerId : 리뷰 작성자 유저의 pk",
			dataTypeClass = Map.class,
			defaultValue = "{\"userId\":2,\"reviewId\":1,\"writerId\":3}")
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
	@ApiImplicitParam(name = "reviewDto",
			value = "userId : 로그인한 유저의 pk \n planId : 리뷰 작성할 플랜의 pk \n subject : 리뷰 제목 \n content : 리뷰 내용",
			dataTypeClass = Map.class,
			defaultValue = "{\"userId\":2,\"planId\":1,\"subject\":제목,\"content\":내용}")
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
	@ApiImplicitParam(name = "userId", value = "로그인한 유저의 pk", required = true, defaultValue = "2", dataTypeClass = Integer.class)
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
	@ApiImplicitParam(name = "reviewDto",
			value = "userId : 로그인한 유저의 pk \n reviewId : 수정할 리뷰의 pk \n subject : 리뷰 제목 \n content : 리뷰 내용",
			dataTypeClass = Map.class,
			defaultValue = "{\"userId\":2,\"reviewId\":1,\"subject\":제목,\"content\":내용}")
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
	@ApiImplicitParam(name = "reviewId", value = "삭제할 리뷰의 pk", dataTypeClass = Integer.class, defaultValue = "1", required = true)
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
	@ApiImplicitParam(name = "reviewAndUserId",
			value = "userId : 로그인한 유저의 pk \n reviewId : 좋아요 할 리뷰의 pk",
			dataTypeClass = Map.class,
			defaultValue = "{\"userId\":2,\"reviewId\":1}")
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
	@ApiImplicitParam(name = "reviewAndUserId",
			value = "userId : 로그인한 유저의 pk \n reviewId : 좋아요 취소 할 리뷰의 pk",
			dataTypeClass = Map.class,
			defaultValue = "{\"userId\":2,\"reviewId\":1}")
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
	@ApiOperation(value = "리뷰 스크랩 수 증가", notes = "리뷰 스크랩 버튼 클릭 시 호출")
	@ApiImplicitParam(name = "reviewId", value = "스크랩 수 증가 할 리뷰의 pk", dataTypeClass = Integer.class, defaultValue = "1", required = true)
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
	@ApiOperation(value = "리뷰 작성 시 여행지 평점 적용", notes = "리뷰 작성 시 여행지 평점 적용")
	@ApiImplicitParam(name = "userAndContentAndReviewIdAndScore",
			value = "userId : 로그인한 유저의 pk \n reviewId : 평점을 적용한 리뷰의 pk \n contentId : 평점을 적용할 여행지의 pk \n score : 평점(1~5)",
			dataTypeClass = Map.class,
			defaultValue = "{\"userId\":2,\"reviewId\":1,\"contentId\":337414,\"score\":3}")
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
	@ApiOperation(value = "여행지 평점 적용 취소", notes = "여행지 평점 적용 취소")
	@ApiImplicitParam(name = "userAndContentAndReviewId",
			value = "userId : 로그인한 유저의 pk \n reviewId : 평점 적용했던 리뷰의 pk \n contentId : 평점 적용 취소할 여행지의 pk",
			dataTypeClass = Map.class,
			defaultValue = "{\"userId\":2,\"reviewId\":1,\"contentId\":337414}")
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
