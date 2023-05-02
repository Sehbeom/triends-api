//package com.ssafy.triends.domain.alarm.mapper;
//
//import com.ssafy.triends.domain.alarm.model.AlarmDto;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class AlarmDaoImpl implements AlarmMapper {
//
//	private static AlarmMapper instance = null;
//
//	private AlarmDaoImpl() {
//		super();
//	}
//
//	public static AlarmMapper getInstance() {
//		if (instance == null)
//			instance = new AlarmDaoImpl();
//		return instance;
//	}
//
//	@Override
//	public int invite(int planId, int ownerId, JSONArray invitelist) throws SQLException {
//		int cnt = 0;
//
//		Connection conn = db.getConnection();
//		PreparedStatement pstmt = null;
//		StringBuilder sb = new StringBuilder("");
//
//		sb.append("insert into notify (notify_id, user_id, content, type, `read`, url) \n");
//		sb.append("values (null, ?, concat((select name from user where user_id = ").append(ownerId);
//		sb.append("), '님이 [', (select title from plan where plan_id =").append(planId);
//		sb.append("), ']에 초대했습니다.'), 'invite', 0, \"/plan?action=updatePlanMembers&planId=").append(planId);
//		sb.append("\");");
//
//		for (int i = 0; i < invitelist.size(); i++)
//		{
//			int targetUserId = Integer.parseInt(String.valueOf(((JSONObject)(invitelist.get(i))).get("user_id")));
//			System.out.println(targetUserId);
//			pstmt = conn.prepareStatement(sb.toString());
//			pstmt.setInt(1, targetUserId);
//			cnt = pstmt.executeUpdate();
//			if (cnt != 1) return cnt;
//		}
//
//		return cnt;
//	}
//
//	@Override
//	public int reply(int reviewId, int authorId) throws SQLException {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public List<AlarmDto> getNotify(int userId, int page) throws SQLException {
//		List<AlarmDto> list = new ArrayList<>();
//
//		Connection conn = db.getConnection();
//		PreparedStatement pstmt = null;
//		StringBuilder sb = new StringBuilder("");
//		ResultSet rs = null;
//
//		sb.append("select * from notify where user_id = ? order by notify_id desc limit 10 offset ? \n");
//		pstmt = conn.prepareStatement(sb.toString());
//		pstmt.setInt(1, userId);
//		pstmt.setInt(2, (page - 1) * 10);
//		rs = pstmt.executeQuery();
//
//		while (rs.next()) {
//			AlarmDto notify = new AlarmDto();
//
//			notify.setNotifyId(rs.getInt("notify_id"));
//			notify.setType(rs.getString("type"));
//			notify.setUrl(rs.getString("url"));
//			notify.setRead(rs.getInt("read"));
//			notify.setUserId(rs.getInt("user_id"));
//			notify.setContent(rs.getString("content"));
//
//			list.add(notify);
//		}
//
//		StringBuilder sb2 = new StringBuilder("");
//		sb2.append("update notify set `read` = 1 where user_id = ? and `read` = 0 order by notify_id desc limit ? \n");
//		PreparedStatement pstmt2 = conn.prepareStatement(sb2.toString());
//		pstmt2.setInt(1, userId);
//		pstmt2.setInt(2, (page * 10));
//		pstmt2.executeUpdate();
//
//		return list;
//	}
//
//	@Override
//	public int disable(int notifyId) throws SQLException {
//		int cnt = 0;
//
//		Connection conn = db.getConnection();
//		PreparedStatement pstmt = null;
//		StringBuilder sb = new StringBuilder("");
//
//		sb.append("update notify set type = 'refused' where notify_id = ?; \n");
//		pstmt = conn.prepareStatement(sb.toString());
//		pstmt.setInt(1, notifyId);
//		cnt = pstmt.executeUpdate();
//
//		return cnt;
//	}
//
//}
