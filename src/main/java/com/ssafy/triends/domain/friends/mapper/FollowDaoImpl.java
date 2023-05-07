//package com.ssafy.triends.domain.friends.mapper;
//
//import com.ssafy.enjoytrip.user.model.UserDto;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class FollowDaoImpl implements com.ssafy.enjoytrip.friends.mapper.FriendsMapper {
//	private static com.ssafy.enjoytrip.friends.mapper.FriendsMapper instance = null;
//	private static DBUtil db = null;
//
//	private FollowDaoImpl() {
//		super();
//		db = DBUtil.getInstance();
//	}
//
//	public static com.ssafy.enjoytrip.friends.mapper.FriendsMapper getInstance() {
//		if (instance == null)
//			instance = new FollowDaoImpl();
//		return instance;
//	}
//
//	@Override
//	public int follow(int followerId, int followeeId) throws SQLException {
//		int cnt = -1;
//
//		Connection conn;
//		PreparedStatement pstmt;
//		conn = db.getConnection();
//		StringBuilder sb = new StringBuilder("");
//
//		sb.append("insert into follow (follower, followee) \n");
//		sb.append("values (?, ?) \n");
//		pstmt = conn.prepareStatement(sb.toString());
//		pstmt.setInt(1, followerId);
//		pstmt.setInt(2, followeeId);
//		cnt = pstmt.executeUpdate();
//
//		return cnt;
//	}
//
//	@Override
//	public int unfollow(int followerId, int followeeId) throws SQLException {
//		int cnt = -1;
//
//		Connection conn;
//		PreparedStatement pstmt;
//		conn = db.getConnection();
//		StringBuilder sb = new StringBuilder("");
//
//		sb.append("delete from follow \n");
//		sb.append("where follower = ? and followee = ? \n");
//		pstmt = conn.prepareStatement(sb.toString());
//		pstmt.setInt(1, followerId);
//		pstmt.setInt(2, followeeId);
//		cnt = pstmt.executeUpdate();
//
//		return cnt;
//	}
//
//	@Override
//	public List<UserDto> getFollowing(int followerId) throws SQLException {
//		List<UserDto> list = new ArrayList<UserDto>();
//		ResultSet rs;
//
//		Connection conn;
//		PreparedStatement pstmt;
//		conn = db.getConnection();
//		StringBuilder sb = new StringBuilder("");
//
//		sb.append("select follow.followee, user.name, user.profileimg from follow, user \n");
//		sb.append("where follow.followee = user.user_id and follow.follower = ? ");
//		pstmt = conn.prepareStatement(sb.toString());
//		pstmt.setInt(1, followerId);
//		rs = pstmt.executeQuery();
//
//		while (rs.next()) {
//			UserDto userDto = new UserDto();
//			userDto.setUserId(rs.getInt("follow.followee"));
//			userDto.setName(rs.getString("user.name"));
//			userDto.setProfileimg(rs.getString("user.profileimg"));
//			list.add(userDto);
//		}
//
//		return list;
//	}
//
//}
