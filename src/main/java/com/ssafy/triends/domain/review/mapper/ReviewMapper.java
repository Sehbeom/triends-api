package com.ssafy.triends.domain.review.mapper;

import com.ssafy.triends.domain.review.model.ReviewDto;
import com.ssafy.triends.domain.review.model.ReviewListItemDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Mapper
public interface ReviewMapper {
	List<ReviewListItemDto> orderedList(String order) throws SQLException;
	ReviewDto detailReview(Map<String, Object> userIdAndReviewId) throws SQLException;
	int writeReview(ReviewDto reviewDto) throws SQLException;
	void increaseReviewLikes(int reviewId) throws SQLException;
	void decreaseReviewLikes(int reviewId) throws SQLException;
	void insertToUserLikeReview(Map<String, Object> userAndReviewId) throws SQLException;
	void deleteFromUserLikeReview(Map<String, Object> userAndReviewId) throws SQLException;
	List<ReviewDto> myReviews(int userId) throws SQLException;
	void modifyReview(ReviewDto reviewDto) throws SQLException;
	void deleteReview(int reviewId) throws SQLException;
	void increaseReviewScrapped(int reviewId) throws SQLException;
}
