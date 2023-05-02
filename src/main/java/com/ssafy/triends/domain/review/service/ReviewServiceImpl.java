package com.ssafy.triends.domain.review.service;

import com.ssafy.triends.domain.review.mapper.ReviewMapper;
import com.ssafy.triends.domain.review.model.ReviewDto;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {
	private ReviewMapper reviewMapper;

	public ReviewServiceImpl(ReviewMapper reviewMapper) {
		super();
		this.reviewMapper = reviewMapper;
	}

	@Override
	public int register(ReviewDto reviewDto) throws Exception {
		return 0;
	}

	@Override
	public JSONArray list() throws Exception {
		return null;
	}

	@Override
	public JSONArray hotPlaces() throws Exception {
		return null;
	}

	@Override
	public JSONObject view(int reviewId, int userId) throws Exception {
		return null;
	}

	@Override
	public int modify(int reviewId, JSONObject obj) throws Exception {
		return 0;
	}

	@Override
	public int updateRating(int reviewId, float score, JSONObject obj, int userId) throws Exception {
		return 0;
	}

	@Override
	public int delete(int reviewId) throws Exception {
		return 0;
	}
	

}
