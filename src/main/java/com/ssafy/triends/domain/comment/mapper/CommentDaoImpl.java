//package com.ssafy.triends.domain.comment.mapper;
//
//import com.ssafy.enjoytrip.comment.model.CommentDto;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class CommentDaoImpl implements com.ssafy.enjoytrip.comment.mapper.CommentMapper {
//	private static com.ssafy.enjoytrip.comment.mapper.CommentMapper instance=null;
//	private static DBUtil db=null;
//
//	private CommentDaoImpl() {
//		db=DBUtil.getInstance();
//	}
//
//	public static com.ssafy.enjoytrip.comment.mapper.CommentMapper getInstance() {
//		if(instance==null) {
//			instance=new CommentDaoImpl();
//		}
//		return instance;
//	}
//
//	@Override
//	public int register(CommentDto commentDto) throws SQLException {
//		Connection conn=null;
//		PreparedStatement pstmt=null;
//		StringBuilder sb=new StringBuilder();
//		int result=0;
//		conn=db.getConnection();
//		sb.append("insert into comment (review_id, user_id, content, name) \n");
//		sb.append("values(?, ?, ?, ?) \n");
//		pstmt=conn.prepareStatement(sb.toString());
//		pstmt.setInt(1, commentDto.getReviewId());
//		pstmt.setInt(2, commentDto.getUserId());
//		pstmt.setString(3, commentDto.getContent());
//		pstmt.setString(4, commentDto.getName());
//		result=pstmt.executeUpdate();
//
//		return result;
//	}
//
//	@Override
//	public JSONArray list(int reviewId) throws SQLException {
//		JSONArray list=new JSONArray();
//		Connection conn=null;
//		PreparedStatement pstmt=null;
//		StringBuilder sb=new StringBuilder();
//		ResultSet rs=null;
//
//		conn=db.getConnection();
//		sb.append("select comment.review_id, comment.comment_id, comment.user_id, comment.content, comment.register_time, user.name from comment \n");
//		sb.append("inner join user on comment.user_id = user.user_id \n");
//		sb.append("where review_id= ? \n");
//		pstmt=conn.prepareStatement(sb.toString());
//		pstmt.setInt(1, reviewId);
//		rs=pstmt.executeQuery();
//		while(rs.next()) {
//			JSONObject obj=new JSONObject();
//			obj.put("commentId", rs.getString("comment.comment_id"));
//			obj.put("content", rs.getString("comment.content"));
//			obj.put("userId", rs.getInt("comment.user_id"));
//			obj.put("registTime", rs.getString("comment.register_time"));
//			obj.put("userName", rs.getString("user.name"));
//			list.add(obj);
//		}
//		return list;
//	}
//
//	@Override
//	public int modify(int commentId) throws SQLException {
//		return 0;
//	}
//
//	@Override
//	public int delete(int commentId) throws SQLException {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		StringBuilder sb = new StringBuilder();
//		int result = 0;
//
//		conn = db.getConnection();
//		sb.append("delete from comment \n");
//		sb.append("where comment_id = ? \n");
//		pstmt = conn.prepareStatement(sb.toString());
//		pstmt.setInt(1, commentId);
//		result = pstmt.executeUpdate();
//
//		return result;
//	}
//
//}
