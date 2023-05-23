package com.ssafy.triends.domain.comment.mapper;

import com.ssafy.triends.domain.comment.model.CommentDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;

@Mapper
public interface CommentMapper {
	int register(CommentDto commentDto) throws SQLException;
	int modify(int commentId) throws SQLException;
	int delete(int commentId) throws SQLException;
}
