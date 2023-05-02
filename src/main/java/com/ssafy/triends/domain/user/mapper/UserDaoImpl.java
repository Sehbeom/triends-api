//package com.ssafy.triends.domain.user.mapper;
//
//import com.ssafy.enjoytrip.user.model.UserDto;
//
//import java.sql.*;
//
//public class UserDaoImpl implements com.ssafy.enjoytrip.user.mapper.UserMapper {
//
//	private static com.ssafy.enjoytrip.user.mapper.UserMapper instance = null;
//	private DBUtil db;
//
//	private UserDaoImpl() {
//		db = DBUtil.getInstance();
//	}
//
//	public static com.ssafy.enjoytrip.user.mapper.UserMapper getInstance() {
//		if (instance == null)
//			instance = new UserDaoImpl();
//		return instance;
//	}
//
//	@Override
//	public int idCheck(String id) throws SQLException {
//		ResultSet rs;
//
//		Connection conn;
//		PreparedStatement pstmt;
//		conn = db.getConnection();
//		StringBuilder sb = new StringBuilder("");
//		sb.append("select user_id from user\n");
//		sb.append("where id = ?\n");
//		pstmt = conn.prepareStatement(sb.toString());
//		pstmt.setString(1, id);
//		rs = pstmt.executeQuery();
//
//		if (rs.next()) return -1;
//		else return 1;
//	}
//
//	@Override
//	public int joinMember(UserDto userDto) throws SQLException {
//		int pk = -1;
//
//		Connection conn;
//		PreparedStatement pstmt;
//		conn = db.getConnection();
//		StringBuilder sb = new StringBuilder("");
//		sb.append("insert into user (id, password, name, tel, profileimg, email) \n");
//		sb.append("values(?, ?, ?, ?, ?, ?) \n");
//		pstmt = conn.prepareStatement(sb.toString(), Statement.RETURN_GENERATED_KEYS);
//		pstmt.setString(1, userDto.getId());
//		pstmt.setString(2, userDto.getPassword());
//		pstmt.setString(3, userDto.getName());
//		pstmt.setString(4, userDto.getTel());
//		pstmt.setString(5, userDto.getProfileimg());
//		pstmt.setString(6, userDto.getEmail());
//
//		pstmt.executeUpdate();
//		ResultSet rs = pstmt.getGeneratedKeys();
//
//		if (rs.next())
//			pk = rs.getInt(1);
//
//		return pk;
//	}
//
//	@Override
//	public UserDto loginMember(String id, String password) throws SQLException {
//		UserDto userDto = null;
//		ResultSet rs;
//
//		Connection conn;
//		PreparedStatement pstmt;
//		conn = db.getConnection();
//		StringBuilder sb = new StringBuilder("");
//		sb.append("select user_id, id, name, tel, profileimg, admin from user \n");
//		sb.append("where id = ? and password = ?\n");
//		pstmt = conn.prepareStatement(sb.toString());
//		pstmt.setString(1, id);
//		pstmt.setString(2, password);
//		rs = pstmt.executeQuery();
//
//		if (rs.next()) {
//			userDto = new UserDto();
//			userDto.setUserId(rs.getInt("user_id"));
//			userDto.setId(rs.getString("id"));
//			userDto.setName(rs.getString("name"));
//			userDto.setTel(rs.getString("tel"));
//			userDto.setProfileimg(rs.getString("profileimg"));
//			userDto.setIsAdmin(rs.getInt("admin"));
//		}
//		return userDto;
//	}
//
//	@Override
//	public int changePwd(int userId, String id, String pw) throws SQLException {
//		int cnt = 0;
//
//		Connection conn;
//		PreparedStatement pstmt;
//		conn = db.getConnection();
//		StringBuilder sb = new StringBuilder("");
//		sb.append("update user set password = ? \n");
//		sb.append("where user_id = ? or id = ? \n");
//		pstmt = conn.prepareStatement(sb.toString());
//		pstmt.setString(1, pw);
//		pstmt.setInt(2, userId);
//		pstmt.setString(3, id);
//		cnt = pstmt.executeUpdate();
//
//		return cnt;
//	}
//
//	@Override
//	public int checkIdEmail(String id, String email) throws SQLException {
//		ResultSet rs;
//
//		Connection conn;
//		PreparedStatement pstmt;
//		conn = db.getConnection();
//		StringBuilder sb = new StringBuilder("");
//		sb.append("select user_id from user\n");
//		sb.append("where id = ? and email = ? \n");
//		pstmt = conn.prepareStatement(sb.toString());
//		pstmt.setString(1, id);
//		pstmt.setString(2, email);
//		System.out.println(pstmt.toString());
//		rs = pstmt.executeQuery();
//
//		if (rs.next()) return 1;
//		else return -1;
//	}
//
//	@Override
//	public int resetPwd(String id, String tmppwd) throws SQLException {
//		int cnt = 0;
//
//		Connection conn;
//		PreparedStatement pstmt;
//		conn = db.getConnection();
//		StringBuilder sb = new StringBuilder("");
//		sb.append("update user set password = ? \n");
//		sb.append("where id = ? \n");
//		pstmt = conn.prepareStatement(sb.toString());
//		pstmt.setString(1, tmppwd);
//		pstmt.setString(2, id);
//		cnt = pstmt.executeUpdate();
//
//		return cnt;
//	}
//
//}
