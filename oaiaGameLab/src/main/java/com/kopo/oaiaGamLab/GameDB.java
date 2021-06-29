package com.kopo.oaiaGamLab;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.sqlite.SQLiteConfig;

public class GameDB {

	public boolean createDB() {
		boolean isSuccess = false;
		// open
		try {
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			Connection connection = DriverManager.getConnection("jdbc:sqlite:/" + "C:/tomcat/oaiaGamLab.db",
					config.toProperties());
			// 쿼리문 입력하세요 (원하는거 정의)
			String query = "CREATE TABLE user(idx INTEGER PRIMARY KEY AUTOINCREMENT, user_id TEXT, user_pwd TEXT, user_name TEXT, user_birth TEXT, user_email TEXT, user_nickName TEXT, join_date TEXT)";
			Statement statement = connection.createStatement();
			int result = statement.executeUpdate(query);

			String query2 = "CREATE TABLE gameData(idx INTEGER PRIMARY KEY AUTOINCREMENT, id TEXT, pwd TEXT, name TEXT, birthday TEXT, address TEXT, joindate TEXT)";
			Statement statement2 = connection.createStatement();
			int result2 = statement2.executeUpdate(query2);

			String query3 = "CREATE TABLE admin(idx INTEGER PRIMARY KEY AUTOINCREMENT, admin_id TEXT, admin_pwd TEXT, admin_name TEXT)";
			Statement statement3 = connection.createStatement();
			int result3 = statement3.executeUpdate(query3);

			String query4 = "CREATE TABLE board(idx INTEGER PRIMARY KEY AUTOINCREMENT, user_title TEXT, user_content TEXT, user_idx INTEGER, created TEXT, updated TEXT)";
			Statement statement4 = connection.createStatement();
			int result4 = statement4.executeUpdate(query4);

			statement.close();
			statement2.close();
			statement3.close();
			statement4.close();
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
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			Connection connection = DriverManager.getConnection("jdbc:sqlite:/" + "c:\\tomcat\\oaiaGamLab.db",
					config.toProperties());

			String query = "SELECT * FROM admin;";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();

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
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			Connection connection = DriverManager.getConnection("jdbc:sqlite:/" + "c:\\tomcat\\oaiaGamLab.db",
					config.toProperties());

			String query = "INSERT INTO admin (admin_id, admin_pwd, admin_name) VALUES ('admin_gsj','admin_gsj','고수진'), ('admin_byh','admin_byh','배영현'), ('admin_keh','admin_keh','김은혜'), ('admin_pys','admin_pys','박영선')";
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
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			Connection connection = DriverManager.getConnection("jdbc:sqlite:/" + "c:\\tomcat\\oaiaGamLab.db",
					config.toProperties());

//			admin.admin_pwd = this.sha256(admin.admin_pwd);// password hash sha256 -> 주로사용

			String query = "SELECT * FROM admin WHERE admin_id=? AND admin_pwd=?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, admin.admin_id);
			preparedStatement.setString(2, admin.admin_pwd);
			ResultSet resultSet = preparedStatement.executeQuery();
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

}
