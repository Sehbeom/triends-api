package com.ssafy.triends.domain.review.service;

import com.ssafy.triends.domain.review.mapper.ReviewMapper;
import com.ssafy.triends.domain.review.model.ReviewDto;
import com.ssafy.triends.domain.review.model.ReviewListItemDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReviewServiceImpl implements ReviewService {
	private ReviewMapper reviewMapper;

	public ReviewServiceImpl(ReviewMapper reviewMapper) {
		super();
		this.reviewMapper = reviewMapper;
	}

	@Override
	public List<ReviewListItemDto> orderedList(String order) throws Exception {
		return reviewMapper.orderedList(order);
	}

	@Override
	public ReviewDto detailReview(Map<String, Object> userIdAndReviewId) throws Exception {
		return reviewMapper.detailReview(userIdAndReviewId);
	}

	@Override
	public int writeReview(ReviewDto reviewDto) throws Exception {
		return reviewMapper.writeReview(reviewDto);
	}

	@Override
	public void likeReview(Map<String, Object> reviewAndUserId) throws Exception {
		reviewMapper.insertToUserLikeReview(reviewAndUserId);
		reviewMapper.increaseReviewLikes(Integer.parseInt((String) reviewAndUserId.get("reviewId")));
	}

	@Override
	public void unlikeReview(Map<String, Object> reviewAndUserId) throws Exception {
		reviewMapper.deleteFromUserLikeReview(reviewAndUserId);
		reviewMapper.decreaseReviewLikes(Integer.parseInt((String) reviewAndUserId.get("reviewId")));
	}

	@Override
	public List<ReviewDto> myReviews(int userId) throws Exception {
		return reviewMapper.myReviews(userId);
	}

	@Override
	public void modifyReview(ReviewDto reviewDto) throws Exception {
		reviewMapper.modifyReview(reviewDto);
	}

	@Override
	public void deleteReview(int reviewId) throws Exception {
		reviewMapper.deleteReview(reviewId);
	}

	@Override
	public void increaseReviewScrapped(int reviewId) throws Exception {
		reviewMapper.increaseReviewScrapped(reviewId);
	}
}
