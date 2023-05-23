package com.ssafy.triends.domain.review.service;

import com.ssafy.triends.domain.comment.model.CommentDto;
import com.ssafy.triends.domain.review.model.ReviewDto;

import java.util.List;
import java.util.Map;

public interface ReviewService {
	List<ReviewDto> orderedList(int selected) throws Exception;
	ReviewDto detailReview(int reviewId) throws Exception;
	int writeReview(ReviewDto reviewDto) throws Exception;
	int registComment(CommentDto commentDto) throws Exception;
	List<CommentDto> getComment(int reviewId) throws Exception;
	void likeReview(Map<String, Object> reviewAndUserId) throws Exception;
	List<ReviewDto> myReviews(int userId) throws Exception;
	void modifyReview(ReviewDto reviewDto) throws Exception;
	void deleteReview(int reviewId) throws Exception;
}
