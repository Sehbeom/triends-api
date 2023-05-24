package com.ssafy.triends.domain.comment.service;

import com.ssafy.triends.domain.comment.mapper.CommentMapper;
import com.ssafy.triends.domain.comment.model.CommentDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
	private CommentMapper commentMapper;

	public CommentServiceImpl(CommentMapper commentMapper) {
		super();
		this.commentMapper = commentMapper;
	}


	@Override
	public CommentDto getOneComment(int commentId) throws Exception {
		return commentMapper.getOneComment(commentId);
	}

	@Override
	public List<CommentDto> getAllCommentsOfOneReview(int reviewId) throws Exception {
		return commentMapper.getAllCommentsOfOneReview(reviewId);
	}

	@Override
	public List<CommentDto> getAllCommentsOfOneUser(int userId) throws Exception {
		return commentMapper.getAllCommentsOfOneUser(userId);
	}

	@Override
	public int registOneComment(CommentDto commentDto) throws Exception {
		return commentMapper.registOneComment(commentDto);
	}

	@Override
	public int deleteOneComment(int commentId) throws Exception {
		return commentMapper.deleteOneComment(commentId);
	}
}
