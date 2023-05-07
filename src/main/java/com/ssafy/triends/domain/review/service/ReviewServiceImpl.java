package com.ssafy.triends.domain.review.service;

import com.ssafy.triends.domain.comment.model.CommentDto;
import com.ssafy.triends.domain.review.mapper.ReviewMapper;
import com.ssafy.triends.domain.review.model.ReviewDto;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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
	public int registComment(CommentDto commentDto) throws Exception {
		return reviewMapper.registComment(commentDto);
	}

	@Override
	public List<CommentDto> getComment(int reviewId) throws Exception {
		return reviewMapper.getComment(reviewId);
	}

	@Override
	public void likeReview(Map<String, Object> map) throws Exception {
		reviewMapper.likeReview(map);
	}

	@Override
	public List<ReviewDto> myReview(int userId) throws Exception {
		return reviewMapper.myReview(userId);
	}

	@Override
	public void modifyReview(ReviewDto reviewDto) throws Exception {
		reviewMapper.modifyReview(reviewDto);
	}

	@Override
	public ReviewDto getReview(int reviewId) throws Exception {
		return reviewMapper.getReview(reviewId);
	}

	@Override
	public void deleteMyReview(int reviewId) throws Exception {
		reviewMapper.deleteMyReview(reviewId);
	}
}
