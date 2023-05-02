//package com.ssafy.triends.domain.notice.mapper;
//
//import com.ssafy.enjoytrip.notice.model.NoticeDto;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class NoticeDaoImpl implements com.ssafy.enjoytrip.notice.mapper.NoticeMapper {
//	private static com.ssafy.enjoytrip.notice.mapper.NoticeMapper instance =null;
//	private static DBUtil db=null;
//	private NoticeDaoImpl() {
//		db=DBUtil.getInstance();
//	}
//
//	public static com.ssafy.enjoytrip.notice.mapper.NoticeMapper getInstance() {
//		if(instance==null) {
//			instance=new NoticeDaoImpl();
//		}
//		return instance;
//	}
//
//	@Override
//	public int register(NoticeDto noticeDto) throws SQLException {
//		Connection conn=null;
//		PreparedStatement pstmt=null;
//		StringBuilder sb=new StringBuilder();
//		int result=0;
//		conn=db.getConnection();
//		sb.append("insert into notice (subject, content, user_id) \n");
//		sb.append("values (?, ?, ?) \n");
//		pstmt=conn.prepareStatement(sb.toString());
//		pstmt.setString(1, noticeDto.getSubject());
//		pstmt.setString(2, noticeDto.getContent());
//		pstmt.setInt(3, noticeDto.getUserId());
//		result=pstmt.executeUpdate();
//
//		return result;
//	}
//
//	@Override
//	public JSONArray list(int page) throws SQLException {
//		JSONArray list=new JSONArray();
//		Connection conn=null;
//		PreparedStatement pstmt=null;
//		StringBuilder sb=new StringBuilder();
//		ResultSet rs=null;
//
//		conn=db.getConnection();
//		sb.append("select notice.notice_id, notice.subject, notice.content, notice.user_id, notice.regist_time, user.name from notice \n");
//		sb.append("inner join user on notice.user_id=user.user_id \n");
//		sb.append("order by notice.notice_id desc limit 20 offset ? \n");
//		pstmt=conn.prepareStatement(sb.toString());
//		pstmt.setInt(1, (page - 1) * 20);
//		System.out.println(pstmt.toString());
//		rs=pstmt.executeQuery();
//		while(rs.next()) {
//			JSONObject obj=new JSONObject();
//			obj.put("noticeId", rs.getString("notice.notice_id"));
//			obj.put("subject", rs.getString("notice.subject"));
//			obj.put("content", rs.getString("notice.content"));
//			obj.put("userId", rs.getInt("notice.user_id"));
//			obj.put("registTime", rs.getString("notice.regist_time"));
//			obj.put("userName", rs.getString("user.name"));
//			list.add(obj);
//		}
//		return list;
//	}
//
//	@Override
//	public JSONObject view(int noticeId) throws SQLException {
//		JSONObject obj=new JSONObject();
//		Connection conn=null;
//		PreparedStatement pstmt=null;
//		StringBuilder sb=new StringBuilder();
//		ResultSet rs=null;
//
//		conn=db.getConnection();
//		sb.append("select notice.notice_id, notice.subject, notice.content, notice.user_id, notice.regist_time, user.name from notice \n");
//		sb.append("inner join user on notice.user_id=user.user_id \n");
//		sb.append("where notice.notice_id = ? \n");
//		pstmt=conn.prepareStatement(sb.toString());
//		pstmt.setInt(1, noticeId);
//		rs=pstmt.executeQuery();
//
//		if(rs.next()) {
//			obj.put("noticeId", rs.getString("notice.notice_id"));
//			obj.put("subject", rs.getString("notice.subject"));
//			obj.put("content", rs.getString("notice.content"));
//			obj.put("userId", rs.getString("notice.user_id"));
//			obj.put("registTime",rs.getString("notice.regist_time"));
//			obj.put("userName", rs.getString("user.name"));
//		}
//		return obj;
//	}
//
//	@Override
//	public int delete(int noticeId) throws SQLException {
//		Connection conn=null;
//		PreparedStatement pstmt=null;
//		StringBuilder sb=new StringBuilder();
//		int result=0;
//
//		conn=db.getConnection();
//		sb.append("delete from notice \n");
//		sb.append("where notice_id = ? \n");
//		pstmt=conn.prepareStatement(sb.toString());
//		pstmt.setInt(1, noticeId);
//		result=pstmt.executeUpdate();
//		return result;
//	}
//
//	@Override
//	public int modify(int noticeId, JSONObject obj) throws SQLException {
//		Connection conn=null;
//		PreparedStatement pstmt=null;
//		StringBuilder sb=new StringBuilder();
//		int result=0;
//		conn=db.getConnection();
//		sb.append("update notice \n");
//		sb.append("set subject = ? , content = ? \n");
//		sb.append("where notice_id = ? \n");
//		pstmt=conn.prepareStatement(sb.toString());
//		pstmt.setString(1, (String) obj.get("subject"));
//		pstmt.setString(2, (String) obj.get("content"));
//		pstmt.setInt(3, noticeId);
//		result=pstmt.executeUpdate();
//		return result;
//	}
//
//	@Override
//	public int total() throws SQLException {
//		JSONObject obj=new JSONObject();
//		Connection conn=null;
//		PreparedStatement pstmt=null;
//		StringBuilder sb=new StringBuilder();
//		ResultSet rs=null;
//
//		conn=db.getConnection();
//		sb.append("select count(notice_id) as cnt from notice; \n");
//		pstmt=conn.prepareStatement(sb.toString());
//		rs=pstmt.executeQuery();
//
//		if (rs.next())
//			return rs.getInt("cnt");
//		else return -1;
//	}
//
//}
