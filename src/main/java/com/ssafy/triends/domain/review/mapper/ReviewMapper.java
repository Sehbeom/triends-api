package com.ssafy.triends.domain.review.mapper;

import com.ssafy.triends.domain.review.model.ReviewDto;
import org.apache.ibatis.annotations.Mapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.SQLException;

@Mapper
public interface ReviewMapper {
	int register(ReviewDto reviewDto) throws SQLException;
	JSONArray list() throws SQLException;
	JSONArray hotPlaces() throws SQLException;
	JSONObject view(int reviewId, int userId) throws SQLException;
	int modify(int reviewId, JSONObject obj) throws SQLException;
	int delete(int reviewId) throws SQLException;
	int updateRating(int reviewId, float score, JSONObject obj, int userId) throws SQLException;
}
