package com.ssafy.triends.domain.review.service;

import com.ssafy.triends.domain.review.model.ReviewDto;
import com.ssafy.triends.domain.review.model.ReviewListItemDto;

import java.util.List;
import java.util.Map;

public interface ReviewService {
	List<ReviewListItemDto> orderedList(String order) throws Exception;
	ReviewDto detailReview(Map<String, Object> userIdAndReviewId) throws Exception;
	int writeReview(ReviewDto reviewDto) throws Exception;
	void likeReview(Map<String, Object> reviewAndUserId) throws Exception;
	void unlikeReview(Map<String, Object> reviewAndUserId) throws Exception;
	List<ReviewDto> myReviews(int userId) throws Exception;
	void modifyReview(ReviewDto reviewDto) throws Exception;
	void deleteReview(int reviewId) throws Exception;
	void increaseReviewScrapped(int reviewId) throws Exception;
}
