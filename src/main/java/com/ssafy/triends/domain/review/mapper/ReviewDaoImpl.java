//package com.ssafy.triends.domain.review.mapper;
//
//import com.ssafy.enjoytrip.review.model.ReviewDto;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class ReviewDaoImpl implements com.ssafy.enjoytrip.review.mapper.ReviewMapper {
//
//	private static com.ssafy.enjoytrip.review.mapper.ReviewMapper instance = null;
//	private static DBUtil db = null;
//
//	private ReviewDaoImpl() {
//		db = DBUtil.getInstance();
//	}
//
//	public static com.ssafy.enjoytrip.review.mapper.ReviewMapper getInstance() {
//		if (instance == null)
//			instance = new ReviewDaoImpl();
//
//		return instance;
//	}
//
//	@Override
//	public int register(ReviewDto reviewDto) throws SQLException {
//		Connection conn=null;
//		PreparedStatement pstmt=null;
//		StringBuilder sb=new StringBuilder();
//		int result=0;
//		conn=db.getConnection();
//		sb.append("insert into review (subject, content, user_id, plan_id) \n");
//		sb.append("values (?, ?, ?, ?) \n");
//		pstmt=conn.prepareStatement(sb.toString());
//		pstmt.setString(1, reviewDto.getSubject());
//		pstmt.setString(2, reviewDto.getContent());
//		pstmt.setInt(3, reviewDto.getUserId());
//		pstmt.setInt(4, reviewDto.getPlanId());
//		result=pstmt.executeUpdate();
//
//		return result;
//	}
//
//	@Override
//	public JSONArray list() throws SQLException {
//		JSONArray list=new JSONArray();
//		Connection conn=null;
//		PreparedStatement pstmt=null;
//		StringBuilder sb=new StringBuilder();
//		ResultSet rs=null;
//
//		conn=db.getConnection();
//		sb.append("select review.review_id, review.subject, review.content, review.user_id, review.regist_time, review.rating, review.plan_id, user.name, plan.thumbnail from review \n");
//		sb.append("inner join plan on review.plan_id = plan.plan_id \n");
//		sb.append("inner join user on review.user_id = user.user_id \n");
//		sb.append("order by review.review_id desc \n");
//		pstmt=conn.prepareStatement(sb.toString());
//		rs=pstmt.executeQuery();
//		while(rs.next()) {
//			JSONObject obj=new JSONObject();
//			obj.put("reviewId", rs.getString("review.review_id"));
//			obj.put("subject", rs.getString("review.subject"));
//			obj.put("content", rs.getString("review.content"));
//			obj.put("userId", rs.getInt("review.user_id"));
//			obj.put("registTime", rs.getString("review.regist_time"));
//			obj.put("rating", rs.getFloat("review.rating"));
//			obj.put("planId", rs.getInt("review.plan_id"));
//			obj.put("userName", rs.getString("user.name"));
//			obj.put("thumbnail", rs.getString("plan.thumbnail"));
//			list.add(obj);
//		}
//		return list;
//	}
//
//	@Override
//	public JSONObject view(int reviewId, int userId) throws SQLException {
//		JSONObject obj=new JSONObject();
//		Connection conn=null;
//		PreparedStatement pstmt=null;
//		StringBuilder sb=new StringBuilder();
//		ResultSet rs=null;
//
//		conn=db.getConnection();
//		sb.append("select review.review_id, review.subject, review.content, review.user_id, review.regist_time, review.rating, review.plan_id, review.rated_user_id, \n");
//		sb.append("plan.title, plan.start_date, plan.end_date, user.id, user.name, \n");
//		sb.append("(select count(review_id) from review ");
//		sb.append("where JSON_CONTAINS(rated_user_id, '{\"user_id\": \"").append(userId);
//		sb.append("\"}', '$') and review_id= ? ) as rated, \n");
//		sb.append("(select count(*) from follow where follower = ? and followee = review.user_id) as following \n");
//		sb.append("from review \n");
//		sb.append("inner join plan on review.plan_id = plan.plan_id \n");
//		sb.append("inner join user on review.user_id = user.user_id \n");
//		sb.append("where review_id = ? \n");
//		pstmt=conn.prepareStatement(sb.toString());
//		pstmt.setInt(1, reviewId);
//		pstmt.setInt(2, userId);
//		pstmt.setInt(3, reviewId);
//
//		rs=pstmt.executeQuery();
//		if(rs.next()) {
//			obj.put("reviewId", rs.getString("review.review_id"));
//			obj.put("subject", rs.getString("review.subject"));
//			obj.put("content", rs.getString("review.content"));
//			obj.put("userId", rs.getString("review.user_id"));
//			obj.put("registTime",rs.getString("review.regist_time"));
//			obj.put("rating", rs.getFloat("review.rating"));
//			obj.put("planId", rs.getString("review.plan_id"));
//			obj.put("ratedUserId", rs.getString("review.rated_user_id"));
//			obj.put("planTitle", rs.getString("plan.title"));
//			obj.put("planStartDate", rs.getString("plan.start_date"));
//			obj.put("planEndDate", rs.getString("plan.end_date"));
//			obj.put("id", rs.getString("user.id"));
//			obj.put("userName", rs.getString("user.name"));
//			if (userId == -1) obj.put("following", userId);
//			else obj.put("following", rs.getInt("following"));
//			if (reviewId == -1) obj.put("rated", reviewId);
//			else obj.put("rated", rs.getInt("rated"));
//		}
//		return obj;
//	}
//
//	@Override
//	public int delete(int reviewId) throws SQLException {
//		Connection conn=null;
//		PreparedStatement pstmt=null;
//		StringBuilder sb=new StringBuilder();
//		int result=0;
//
//		conn=db.getConnection();
//		sb.append("delete from review \n");
//		sb.append("where review_id = ? \n");
//		pstmt=conn.prepareStatement(sb.toString());
//		pstmt.setInt(1, reviewId);
//		result=pstmt.executeUpdate();
//		return result;
//	}
//
//	@Override
//	public int modify(int reviewId, JSONObject obj) throws SQLException {
//		Connection conn=null;
//		PreparedStatement pstmt=null;
//		StringBuilder sb=new StringBuilder();
//		int result=0;
//		conn=db.getConnection();
//		sb.append("update review \n");
//		sb.append("set subject = ? , content = ? \n");
//		sb.append("where review_id = ? \n");
//		pstmt=conn.prepareStatement(sb.toString());
//		pstmt.setString(1, (String) obj.get("subject"));
//		pstmt.setString(2, (String) obj.get("content"));
//		pstmt.setInt(3, reviewId);
//		result=pstmt.executeUpdate();
//		return result;
//	}
//
//	@Override
//	public JSONArray hotPlaces() throws SQLException {
//		JSONArray list=new JSONArray();
//		Connection conn=null;
//		PreparedStatement pstmt=null;
//		StringBuilder sb=new StringBuilder();
//		ResultSet rs=null;
//
//		conn=db.getConnection();
//		sb.append("select review.review_id, review.subject, review.content, review.user_id, review.regist_time, review.rating, review.plan_id, user.name, plan.thumbnail from review \n");
//		sb.append("inner join user on review.user_id = user.user_id \n");
//		sb.append("inner join plan on review.plan_id = plan.plan_id \n");
//		sb.append("order by review.rating desc limit 6 \n");
//		pstmt=conn.prepareStatement(sb.toString());
//		rs=pstmt.executeQuery();
//		while(rs.next()) {
//			JSONObject obj=new JSONObject();
//			obj.put("reviewId", rs.getString("review.review_id"));
//			obj.put("subject", rs.getString("review.subject"));
//			obj.put("content", rs.getString("review.content"));
//			obj.put("userId", rs.getInt("review.user_id"));
//			obj.put("registTime", rs.getString("review.regist_time"));
//			obj.put("rating", rs.getFloat("review.rating"));
//			obj.put("planId", rs.getInt("review.plan_id"));
//			obj.put("userName", rs.getString("user.name"));
//			obj.put("thumbnail", rs.getString("plan.thumbnail"));
//			list.add(obj);
//		}
//		return list;
//	}
//
//	@Override
//	public int updateRating(int reviewId, float score, JSONObject obj, int userId) throws SQLException {
//		Connection conn=null;
//		PreparedStatement pstmt=null;
//		StringBuilder sb=new StringBuilder();
//		System.out.println("inDao ::: "+obj.get("userId"));
//		int result=0;
//		conn=db.getConnection();
//		sb.append("update review \n");
//		sb.append("set rated_user_id=JSON_ARRAY_APPEND(rated_user_id, '$', CAST('{\"user_id\": \"").append(userId);
//		sb.append("\"}' as json)), \n");
//		sb.append("rating=(rating*(json_length(rated_user_id)-1)+ ? )/(json_length(rated_user_id)) \n");
//		sb.append("where review_id = ? \n");
//		pstmt=conn.prepareStatement(sb.toString());
//		pstmt.setFloat(1, score);
//		pstmt.setInt(2, reviewId);
//		System.out.println(pstmt);
//		result=pstmt.executeUpdate();
//		return result;
//	}
//
//}
