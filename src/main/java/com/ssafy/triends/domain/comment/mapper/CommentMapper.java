package com.ssafy.triends.domain.comment.mapper;

import com.ssafy.triends.domain.comment.model.CommentDto;
import org.apache.ibatis.annotations.Mapper;
import org.json.simple.JSONArray;

import java.sql.SQLException;

@Mapper
public interface CommentMapper {
	int register(CommentDto commentDto) throws SQLException;
	JSONArray list(int reviewId) throws SQLException;
	int modify(int commentId) throws SQLException;
	int delete(int commentId) throws SQLException;
}
