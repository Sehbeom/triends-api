package com.ssafy.triends.domain.comment.service;

import com.ssafy.triends.domain.comment.mapper.CommentMapper;
import com.ssafy.triends.domain.comment.model.CommentDto;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
	private CommentMapper commentMapper;

	public CommentServiceImpl(CommentMapper commentMapper) {
		super();
		this.commentMapper = commentMapper;
	}

	@Override
	public int register(CommentDto commentDto) throws Exception {
		return 0;
	}

	@Override
	public int modify(int commentId) throws Exception {
		return 0;
	}

	@Override
	public int delete(int commentId) throws Exception {
		return 0;
	}
	

}
