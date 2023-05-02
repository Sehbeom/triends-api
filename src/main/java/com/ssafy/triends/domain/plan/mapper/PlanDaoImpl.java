//package com.ssafy.triends.domain.plan.mapper;
//
//import com.ssafy.enjoytrip.plan.model.PlanDto;
//import org.json.simple.JSONArray;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class PlanDaoImpl implements com.ssafy.enjoytrip.plan.mapper.PlanMapper {
//
//	private static com.ssafy.enjoytrip.plan.mapper.PlanMapper instance = null;
//	private DBUtil db;
//
//	private PlanDaoImpl() {
//		db = DBUtil.getInstance();
//	}
//
//	public static com.ssafy.enjoytrip.plan.mapper.PlanMapper getInstance() {
//		if (instance == null)
//			instance = new PlanDaoImpl();
//		return instance;
//	}
//
//	@Override
//	public int createPlan(String startDate, String endDate, String title, String thumbnail, JSONArray members,
//			JSONArray course) throws SQLException {
//		int pk = 0;
//
//		Connection conn;
//		PreparedStatement pstmt;
//		ResultSet rs = null;
//		conn = db.getConnection();
//		StringBuilder sb = new StringBuilder("");
//		sb.append("insert into plan(title, members, course, start_date, end_date, thumbnail) \n");
//		sb.append("values(?, ?, ?, ?, ?, ?) \n");
//		pstmt = conn.prepareStatement(sb.toString(), Statement.RETURN_GENERATED_KEYS);
//		pstmt.setString(1, title);
//		pstmt.setString(2, members.toJSONString());
//		pstmt.setString(3, course.toJSONString());
//		pstmt.setString(4, startDate);
//		pstmt.setString(5, endDate);
//		pstmt.setString(6, thumbnail);
//
//		pstmt.executeUpdate();
//		rs = pstmt.getGeneratedKeys();
//
//		if (rs.next()) {
//			pk = rs.getInt(1);
//
//////			update user set plan = JSON_ARRAY_APPEND(plan, '$', CAST('{"plan_id" : "1"}' AS JSON)) where user_id = 6;
////
////			PreparedStatement pstmt2;
////			StringBuilder sb2 = new StringBuilder("");
////			sb2.append("update user set plan = JSON_ARRAY_APPEND(plan, '$', CAST('{\"plan_id\" : \"");
////			sb2.append(pk);
////			sb2.append("\" }' AS JSON)) where user_id = ? \n");
////			pstmt2 = conn.prepareStatement(sb2.toString());
////			System.out.println(pstmt2.toString());
////			pstmt2.setInt(1, Integer.parseInt(String.valueOf(((JSONObject) members.get(0)).get("userId"))));
////
////			int cnt = pstmt2.executeUpdate();
////			System.out.println("cnt is " + cnt);
//////			if (cnt != 1) throw new SQLException();
//		}
//
//		return pk;
//	}
//
//	@Override
//	public int updatePlan(int planId, String startDate, String endDate, String title, String thumbnail,
//			JSONArray members, JSONArray course) throws SQLException {
//		int result = 0;
//
//		Connection conn;
//		PreparedStatement pstmt;
//		ResultSet rs = null;
//		conn = db.getConnection();
//		StringBuilder sb = new StringBuilder("");
//		sb.append("update plan set title = ?, members = ?, course = ?, start_date = ?, end_date = ?, thumbnail = ? \n");
//		sb.append("where plan_id = ? \n");
//		pstmt = conn.prepareStatement(sb.toString(), Statement.RETURN_GENERATED_KEYS);
//		pstmt.setString(1, title);
//		pstmt.setString(2, members.toJSONString());
//		pstmt.setString(3, course.toJSONString());
//		pstmt.setString(4, startDate);
//		pstmt.setString(5, endDate);
//		pstmt.setString(6, thumbnail);
//		pstmt.setInt(7, planId);
//
//		result = pstmt.executeUpdate();
//
//		return result;
//	}
//
//	@Override
//	public PlanDto getPlan(int planId) throws SQLException, ParseException {
//		PlanDto plan = null;
//
//		Connection conn;
//		PreparedStatement pstmt;
//		ResultSet rs = null;
//		conn = db.getConnection();
//
//		StringBuilder sb = new StringBuilder("");
//
//		sb.append("select plan_id, title, members, course, start_date, end_date, thumbnail\n");
//		sb.append("from plan \n");
//		sb.append("where plan_id = ? \n");
//		pstmt = conn.prepareStatement(sb.toString());
//		pstmt.setInt(1, planId);
//
//		rs = pstmt.executeQuery();
//
//		if (rs.next()) {
//			plan = new PlanDto();
//			plan.setPlanId(rs.getInt("plan_id"));
//			plan.setTitle(rs.getString("title"));
//
//			JSONParser parser = new JSONParser();
//			JSONArray members = (JSONArray) parser.parse(rs.getString("members"));
//			JSONArray course = (JSONArray) parser.parse(rs.getString("course"));
//			plan.setMembers(members);
//			plan.setCourse(course);
//			plan.setStartDate(rs.getString("start_date"));
//			plan.setEndDate(rs.getString("end_date"));
//			plan.setThumbnail(rs.getString("thumbnail"));
//		}
//
//		return plan;
//	}
//
//	@Override
//	public int updatePlanMembers(int planId, int userId, String userName) throws SQLException {
//		int cnt = 0;
//
//		Connection conn;
//		PreparedStatement pstmt;
//		conn = db.getConnection();
//		StringBuilder sb = new StringBuilder("");
//
//		sb.append("update plan set members = JSON_ARRAY_APPEND(members, '$', CAST('{\"userId\" : \"");
//		sb.append(userId).append("\", \"name\" : \"");
//		sb.append(userName).append("\"}' AS JSON)) where plan_id = ?\n");
//		pstmt = conn.prepareStatement(sb.toString());
//		pstmt.setInt(1, planId);
//		cnt = pstmt.executeUpdate();
//
//		return cnt;
//	}
//
//	@Override
//	public int deletePlan(int userId, int planId) throws SQLException {
//		int result = 0;
//
////      UPDATE plan SET members = IFNULL( JSON_REMOVE(
////		members, JSON_UNQUOTE( REPLACE( JSON_SEARCH(members, 'one', '5', NULL, '$**.userId'), '.userId', '' ) ) ), members ) where plan_id = 20;
//
//		Connection conn;
//		PreparedStatement pstmt;
//		ResultSet rs = null;
//		conn = db.getConnection();
//		StringBuilder sb = new StringBuilder("");
//
//		sb.append("update plan set members = IFNULL(JSON_REMOVE(members, JSON_UNQUOTE(REPLACE(JSON_SEARCH(members, 'one', '").append(userId);
//		sb.append("', NULL, '$**.userId'), '.userId', ''))),members) where plan_id = ? \n");
//
//		pstmt = conn.prepareStatement(sb.toString());
//		pstmt.setInt(1, planId);
//
//		result = pstmt.executeUpdate();
//
////		UPDATE user SET plan = IFNULL( JSON_REMOVE(
////		plan, JSON_UNQUOTE( REPLACE( JSON_SEARCH(plan, 'one', '5', NULL, '$**.plan_id'), '.plan_id', '' ) ) ), plan ) where user_id = 6;
//
////		if (result != 0) {
////			StringBuilder sb2 = new StringBuilder("update user set plan = ifnull( JSON_REMOVE(plan, JSON_UNQUOTE(REPLACE(JSON_SEARCH(plan, 'one', '").append(planId);
////			sb2.append("', NULL, '$**.plan_id'), '.plan_id', ''))), plan) where user_id = ? \n");
////
////			PreparedStatement pstmt2 = conn.prepareStatement(sb2.toString());
////			pstmt2.setInt(1, userId);
////
////			result = pstmt2.executeUpdate();
////		} else return result;
//
//		return result;
//	}
//
//	@Override
//	public List<PlanDto> getPlans(int userId) throws SQLException, ParseException {
//		List<PlanDto> list = new ArrayList<>();
//
//		Connection conn;
//		PreparedStatement pstmt;
//		ResultSet rs = null;
//		conn = db.getConnection();
//
//		StringBuilder sb = new StringBuilder("");
//
////		select * from plan where JSON_SEARCH(members, 'all', '6', NULL, '$**.userId') is not null;
//
////		sb.append("select * from plan where JSON_SEARCH(members, 'all', '").append(userId).append("', NULL, '$**.userId') is not null\n");
//		sb.append("select * from plan where members like '%\"userId\"%: \"");
//		sb.append(userId).append("\"%';");
//		pstmt = conn.prepareStatement(sb.toString());
//		rs = pstmt.executeQuery();
//
//		while (rs.next()) {
//			PlanDto plan = new PlanDto();
//			plan.setPlanId(rs.getInt("plan_id"));
//			plan.setTitle(rs.getString("title"));
//
//			JSONParser parser = new JSONParser();
//			JSONArray members = (JSONArray) parser.parse(rs.getString("members"));
//			JSONArray course = (JSONArray) parser.parse(rs.getString("course"));
//			plan.setMembers(members);
//			plan.setCourse(course);
//			plan.setStartDate(rs.getString("start_date"));
//			plan.setEndDate(rs.getString("end_date"));
//			plan.setThumbnail(rs.getString("thumbnail"));
//
//			list.add(plan);
//		}
//
//		return list;
//	}
//
//	@Override
//	public int duplicatePlan(int userId, String name, int planId) throws SQLException {
//		int result = 0;
//
//		Connection conn;
//		PreparedStatement pstmt;
//		ResultSet rs = null;
//		conn = db.getConnection();
//		StringBuilder sb = new StringBuilder("");
//
//		sb.append("insert into plan(title, members, course, start_date, end_date, thumbnail) select title, ");
//		sb.append("'[{\"name\": \"").append(name).append("\", \"userId\": \"").append(userId).append("\"}]',");
//		sb.append(" course, start_date, end_date, thumbnail from plan where plan_id = ? \n");
//		pstmt = conn.prepareStatement(sb.toString());
//		pstmt.setInt(1, planId);
//
//		System.out.println(pstmt.toString());
//
//		result = pstmt.executeUpdate();
//		System.out.println(result);
//
//		return result;
//	}
//
//}
