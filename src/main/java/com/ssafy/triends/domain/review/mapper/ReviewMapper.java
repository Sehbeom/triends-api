package com.ssafy.triends.domain.review.mapper;

import com.ssafy.triends.domain.comment.model.CommentDto;
import com.ssafy.triends.domain.review.model.ReviewDto;
import org.apache.ibatis.annotations.Mapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Mapper
public interface ReviewMapper {
	List<ReviewDto> orderedList(int selected) throws SQLException;
	ReviewDto detailReview(int reviewId) throws SQLException;
	int writeReview(ReviewDto reviewDto) throws SQLException;
	int registComment(CommentDto commentDto) throws SQLException;
	List<CommentDto> getComment(int reviewId) throws SQLException;
	void increaseReviewLikes(int reviewId) throws SQLException;
	void insertToUserLikeReview(Map<String, Object> userAndReviewId) throws SQLException;
	List<ReviewDto> myReviews(int userId) throws SQLException;
	void modifyReview(ReviewDto reviewDto) throws SQLException;
	void deleteReview(int reviewId) throws SQLException;
}
