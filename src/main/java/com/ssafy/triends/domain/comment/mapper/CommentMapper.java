package com.ssafy.triends.domain.comment.mapper;

import com.ssafy.triends.domain.comment.model.CommentDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface CommentMapper {
	CommentDto getOneComment(int commentId) throws SQLException;
	List<CommentDto> getAllCommentsOfOneReview(int reviewId) throws SQLException;
	List<CommentDto> getAllCommentsOfOneUser(int userId) throws SQLException;
	int registOneComment(CommentDto commentDto) throws SQLException;
	int deleteOneComment(int commentId) throws SQLException;
}
