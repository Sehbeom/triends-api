//package com.ssafy.triends.domain.memo.mapper;
//
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//
//import java.sql.*;
//
//public class MemoDaoImpl implements com.ssafy.enjoytrip.memo.mapper.MemoMapper {
//
//	private static com.ssafy.enjoytrip.memo.mapper.MemoMapper instance = null;
//	private static DBUtil db = null;
//
//	private MemoDaoImpl() {
//		db = DBUtil.getInstance();
//	}
//
//	public static com.ssafy.enjoytrip.memo.mapper.MemoMapper getInstance() {
//		if (instance == null)
//			instance = new MemoDaoImpl();
//		return instance;
//	}
//
//	@Override
//	public int addMemo(int planId, int contentId, int userId, String content) throws SQLException {
//		int pk = 0;
//
//		Connection conn;
//		PreparedStatement pstmt;
//		ResultSet rs = null;
//
//		conn = db.getConnection();
//		StringBuilder sb = new StringBuilder("");
//
//		sb.append("insert into memo(memo_id, plan_id, content_id, user_id, content) \n");
//		sb.append("values(NULL, ?, ?, ?, ?) \n");
//
//		pstmt = conn.prepareStatement(sb.toString(), Statement.RETURN_GENERATED_KEYS);
//		pstmt.setInt(1, planId);
//		pstmt.setInt(2, contentId);
//		pstmt.setInt(3, userId);
//		pstmt.setString(4, content);
//
//		pstmt.executeUpdate();
//		rs = pstmt.getGeneratedKeys();
//
//		if (rs.next())
//			pk = rs.getInt(1);
//
//		return pk;
//	}
//
//
//	@Override
//	public int deleteMemo(int memoId) throws SQLException {
//		int cnt = 0;
//
//		Connection conn;
//		PreparedStatement pstmt;
//		ResultSet rs = null;
//
//		conn = db.getConnection();
//		StringBuilder sb = new StringBuilder("");
//
//		sb.append("delete from memo \n");
//		sb.append("where memo_id = ? \n");
//		pstmt = conn.prepareStatement(sb.toString());
//		pstmt.setInt(1, memoId);
//
//		cnt = pstmt.executeUpdate();
//
//		return cnt;
//	}
//
//	@Override
//	public JSONArray getMemos(int planId, int contentId) throws SQLException {
//		JSONArray list = new JSONArray();
//
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		StringBuilder sb = new StringBuilder();
//		ResultSet rs = null;
//
//		conn = db.getConnection();
//
//
////		select memo.memo_id, memo.plan_id, memo.user_id, user.name, user.profileimg, memo.content_id, memo.content
////		from memo
////		inner join user
////		on user.user_id = memo.user_id
////		where memo.plan_id = 5 and memo.content_id = 2685245;
//
//		sb.append("select memo.memo_id, memo.plan_id, memo.user_id, user.name, user.profileimg, memo.content_id, memo.content \n");
//		sb.append("from memo inner join user \n");
//		sb.append("on user.user_id = memo.user_id \n");
//		sb.append("where memo.content_id = ? and memo.plan_id = ? \n");
//
//		pstmt = conn.prepareStatement(sb.toString());
//		pstmt.setInt(1, contentId);
//		pstmt.setInt(2, planId);
//
//		rs = pstmt.executeQuery();
//
//		while(rs.next()) {
//			JSONObject obj=new JSONObject();
//
//			obj.put("memoId", rs.getInt("memo.memo_id"));
//			obj.put("planId", rs.getString("memo.plan_id"));
//			obj.put("userId", rs.getString("memo.user_id"));
//			obj.put("userName", rs.getString("user.name"));
//			obj.put("userProfileimg", rs.getString("user.profileimg"));
//			obj.put("contentId", rs.getString("memo.content_id"));
//			obj.put("content", rs.getString("memo.content"));
//
//			list.add(obj);
//		}
//
//		return list;
//	}
//
//}
