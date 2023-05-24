package com.ssafy.triends.domain.comment.service;

import com.ssafy.triends.domain.comment.model.CommentDto;

import java.util.List;

public interface CommentService {
	CommentDto getOneComment(int commentId) throws Exception;
	List<CommentDto> getAllCommentsOfOneReview(int reviewId) throws Exception;
	List<CommentDto> getAllCommentsOfOneUser(int userId) throws Exception;
	int registOneComment(CommentDto commentDto) throws Exception;
	int deleteOneComment(int commentId) throws Exception;
}
