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
	List<ReviewDto> orderedList(int selected) throws Exception;
	ReviewDto detailReview(int reviewId) throws Exception;
	int writeReview(ReviewDto reviewDto) throws Exception;
	int registComment(CommentDto commentDto) throws Exception;
	List<CommentDto> getComment(int reviewId) throws Exception;
	void likeReview(Map<String, Object> map) throws Exception;
	List<ReviewDto> myReview(int userId) throws Exception;
	void modifyReview(ReviewDto reviewDto) throws Exception;
	ReviewDto getReview(int reviewId) throws Exception;
	void deleteMyReview(int reviewId) throws Exception;
}
