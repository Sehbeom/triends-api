package com.ssafy.triends.domain.review.service;

import com.ssafy.triends.domain.review.model.ReviewDto;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public interface ReviewService {
	int register(ReviewDto reviewDto) throws Exception;
	JSONArray list() throws Exception;
	JSONArray hotPlaces() throws Exception;
	JSONObject view(int reviewId, int userId) throws Exception;
	int modify(int reviewId, JSONObject obj) throws Exception;
	int updateRating(int reviewId, float score, JSONObject obj, int userId) throws Exception;
	int delete(int reviewId) throws Exception;
}
