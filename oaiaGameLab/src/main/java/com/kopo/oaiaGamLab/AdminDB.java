package com.kopo.oaiaGamLab;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.sqlite.SQLiteConfig;

public class AdminDB {

	public boolean createDB() {
		boolean isSuccess = false;
		// open
		try {
			// open
			Connection connection = null;
			Statement statement = null;
			ResultSet resultset = null;

			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:XE", "oaiagame", "oaiagame");

			// 쿼리문 입력하세요 (원하는거 정의)
			String query = "CREATE TABLE users(" + " idx NUMBER(5) PRIMARY KEY," + " user_id VARCHAR2(1000) NOT NULL,"
					+ " user_pwd VARCHAR(4000) NOT NULL," + " user_name VARCHAR2(1000) NOT NULL,"
					+ " user_birth VARCHAR2(1000) NOT NULL," + " user_email VARCHAR2(1000),"
					+ " user_nickName VARCHAR2(1000) NOT NULL," + " join_date VARCHAR2(1000)" + ")";
			statement = connection.createStatement();
			int result = statement.executeUpdate(query);

			String query2 = "CREATE SEQUENCE users_idx INCREMENT BY 1 START WITH 1";
			Statement statement2 = connection.createStatement();
			int result2 = statement2.executeUpdate(query2);

			String query3 = "CREATE TABLE admins(" + " idx NUMBER(5) PRIMARY KEY," + " admin_id VARCHAR2(50),"
					+ " admin_pwd VARCHAR(50)," + " admin_name VARCHAR2(50)" + ")";
			Statement statement3 = connection.createStatement();
			int result3 = statement3.executeUpdate(query3);

			String query4 = "CREATE TABLE board(" + " idx NUMBER(5) PRIMARY KEY,"
					+ " user_title VARCHAR2(4000) NOT NULL," + " user_nickname VARCHAR2(4000) NOT NULL,"
					+ " user_content VARCHAR2(4000) NOT NULL," + " created VARCHAR2(4000) NOT NULL" + ")";
			Statement statement4 = connection.createStatement();
			int result4 = statement4.executeUpdate(query4);

			String query5 = "CREATE SEQUENCE board_idx INCREMENT BY 1 START WITH 1";
			Statement statement5 = connection.createStatement();
			int result5 = statement4.executeUpdate(query5);

			statement.close();
			statement2.close();
			statement3.close();
			statement4.close();
			statement5.close();
			connection.close();

			// close
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public Admin selectadminData() { // 관리자 정보 전체 조회

		Admin resultData = new Admin();
		try {
			// open
			Connection connection = null;
			Statement statement = null;
			ResultSet resultSet = null;

			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:XE", "oaiagame", "oaiagame");

			String query = "SELECT * FROM admins";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				resultData.idx = resultSet.getInt("idx");
				resultData.admin_id = resultSet.getString("admin_id");
				resultData.admin_pwd = resultSet.getString("admin_pwd");
				resultData.admin_name = resultSet.getString("admin_name");
			}
			preparedStatement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultData;
	}

	public boolean insertadminDB() { // 관리자 정보 삽입
		try {
			// open
			Connection connection = null;
			Statement statement = null;
			ResultSet resultSet = null;

			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:XE", "oaiagame", "oaiagame");

			String query = "INSERT ALL" + " INTO admins (idx, admin_id, admin_pwd, admin_name)"
					+ " VALUES (1, 'admin_gsj','admin_gsj','고수진')"
					+ " INTO admins (idx, admin_id, admin_pwd, admin_name)"
					+ " VALUES (2, 'admin_byh','admin_byh','배영현')"
					+ " INTO admins (idx, admin_id, admin_pwd, admin_name)"
					+ " VALUES (3, 'admin_keh','admin_keh','김은혜')"
					+ " INTO admins (idx, admin_id, admin_pwd, admin_name)"
					+ " VALUES (4, 'admin_pys','admin_pys','박영선')" + " SELECT * FROM DUAL";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			int result = preparedStatement.executeUpdate();

			if (result < 1) {
				preparedStatement.close();
				connection.close();
				return false;
			}
			preparedStatement.close();
			connection.close();
			return true;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean loginadminDB(Admin admin) { // 관리자 로그인
		try {
			// open
			Connection connection = null;
			Statement statement = null;
			ResultSet resultSet = null;

			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:XE", "oaiagame", "oaiagame");

//			admin.admin_pwd = this.sha256(admin.admin_pwd);// password hash sha256 -> 주로사용

			String query = "SELECT * FROM admins WHERE admin_id=? AND admin_pwd=?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, admin.admin_id);
			preparedStatement.setString(2, admin.admin_pwd);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				preparedStatement.close();
				connection.close();
				return true;
			} else {
				preparedStatement.close();
				connection.close();
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	// 관리자 정보 페이지 구현
	public Admin detalisAdmin(String admin_id) {
		Admin resultData = new Admin();
		try {
			// open
			Connection connection = null;
			Statement statement = null;
			ResultSet resultset = null;

			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:XE", "oaiagame", "oaiagame");
			// use
			String query = "SELECT * FROM admins WHERE admin_id='" + admin_id + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
//					resultData.idx = resultSet.getInt("idx");
				resultData.admin_id = resultSet.getString("admin_id");
				resultData.admin_pwd = resultSet.getString("admin_pwd");
				resultData.admin_name = resultSet.getString("admin_name");
			}
			preparedStatement.close();
			// close
			connection.close();
		} catch (Exception e) {
		}
		return resultData;
	}

	// 관리자 정보 수정
	public boolean updateAdmin(Admin admin) {
		try {
			// open
			Connection connection = null;
			Statement statement = null;
			ResultSet resultset = null;

			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:XE", "oaiagame", "oaiagame");
			// use
			String query = "UPDATE admins SET admin_pwd=?, admin_name=? WHERE admin_id=" + "'" + admin.admin_id + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, admin.admin_pwd);
			preparedStatement.setString(2, admin.admin_name);

			int result = preparedStatement.executeUpdate();
			if (result < 1) {
				return false;
			}
			preparedStatement.close();

			// close
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// 전체 회원 정보 조회
	public String selectuserData() { // 관리자 권한 - 전체 회원 정보 조회
		String resultString = "";
		try {
			// open
			Connection connection = null;
			Statement statement = null;
			ResultSet resultset = null;

			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:XE", "oaiagame", "oaiagame");

			// use
			String query = "SELECT * FROM users";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int idx = resultSet.getInt("idx");
				String id = resultSet.getString("user_id");
				String pwd = resultSet.getString("user_pwd");
				String name = resultSet.getString("user_name");
				String birth = resultSet.getString("user_birth");
				String email = resultSet.getString("user_email");
				String nickname = resultSet.getString("user_nickName");
				String join_date = resultSet.getString("join_date");
				resultString = resultString + "<tr>";
				resultString = resultString + "<td>" + idx + "</td><td>" + id + "</td><td>" + name + "</td><td>" + birth
						+ "</td><td>" + email + "</td><td>" + nickname + "</td><td>" + join_date + "</td><td>"
						+ "</td>";
				resultString = resultString + "</tr>";
			}

			// close
			preparedStatement.close();
			connection.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultString;
	}
}
