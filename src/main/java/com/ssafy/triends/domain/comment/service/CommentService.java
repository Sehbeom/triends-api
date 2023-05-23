package com.ssafy.triends.domain.comment.service;

import com.ssafy.triends.domain.comment.model.CommentDto;

public interface CommentService {
	int register(CommentDto commentDto) throws Exception;
	int modify(int commentId) throws Exception;
	int delete(int commentId) throws Exception;
}
