package com.ssafy.triends.domain.review.service;

import com.ssafy.triends.domain.review.mapper.ReviewMapper;
import com.ssafy.triends.domain.review.model.ReviewDto;
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
	public List<ReviewDto> orderedList(int selected) throws Exception {
		return reviewMapper.orderedList(selected);
	}

	@Override
	public ReviewDto detailReview(int reviewId) throws Exception {
		return reviewMapper.detailReview(reviewId);
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
}
