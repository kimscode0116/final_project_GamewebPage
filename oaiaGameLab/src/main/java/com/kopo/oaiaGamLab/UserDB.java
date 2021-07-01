package com.kopo.oaiaGamLab;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.sqlite.SQLiteConfig;

public class UserDB {

	// 회원가입 메서드
	   public String signup(SignupUser signupUser) {
	      String resultString = "";
	      try {
	         // open
	         Connection connection = null;
	         Statement statement = null;
	         ResultSet resultset = null;

	         Class.forName("oracle.jdbc.driver.OracleDriver");
	         connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "oaiagame", "oaiagame");
	         // use
	         signupUser.pwd = sha256(signupUser.pwd);

	         // 아이디,닉네임 중복확인
	         String query1 = "SELECT * FROM users WHERE user_id=" + "'" + signupUser.id + "' OR user_nickname=" + "'" + signupUser.nickname + "'";
	         PreparedStatement preparedStatement = connection.prepareStatement(query1);
	         ResultSet resultdata = preparedStatement.executeQuery();
	         if (resultdata.next()) {
	            String user_id = resultdata.getString("user_id");
	            String user_nickname = resultdata.getString("user_nickname");
	            if(user_id.equals(signupUser.id)) {
	               resultString = "중복된 아이디가 존재합니다.";
	            }else if(user_nickname.equals(signupUser.nickname)) {
	               resultString = "중복된 닉네임이 존재합니다.";
	            }
	         } else {
	            String query = "INSERT INTO users (idx,user_id,user_pwd,user_name,user_birth,user_email,user_nickName,join_date)"
	                  + " VALUES (users_idx.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
	            preparedStatement = connection.prepareStatement(query);
	            preparedStatement.setString(1, signupUser.id);
	            preparedStatement.setString(2, signupUser.pwd);
	            preparedStatement.setString(3, signupUser.name);
	            preparedStatement.setString(4, signupUser.birth);
	            preparedStatement.setString(5, signupUser.email);
	            preparedStatement.setString(6, signupUser.nickname);
	            preparedStatement.setString(7, signupUser.join_date);
	            System.out.println("오류1?"+signupUser.id);
	            int result = preparedStatement.executeUpdate();
	            System.out.println(result);

	            if (result < 1) {
	               resultString = "error";
	            } else {
	               resultString = "회원가입이 완료되었습니다.";
	            }
	         }
	         preparedStatement.close();

	         // close
	         connection.close();

	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	      return resultString;
	   }

	// 비밀번호 해시처리
	public String sha256(String msg) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(msg.getBytes());

			StringBuilder builder = new StringBuilder();
			for (byte b : md.digest()) {
				builder.append(String.format("%02x", b));
			}
			return builder.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	// 로그인 메서드
	public int userlogin(String id, String pwd) {
		try {
			// open
			Connection connection = null;
			Statement statement = null;
			ResultSet resultset = null;

			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:XE", "oaiagame", "oaiagame");
			// use
			pwd = this.sha256(pwd);

			// 아이디, 패스워드 있는지 검사
			String query = "SELECT * FROM users WHERE user_id=? AND user_pwd=?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, id);
			preparedStatement.setString(2, pwd);
			ResultSet resultdata = preparedStatement.executeQuery();
			if (resultdata.next()) {
				int idx = resultdata.getInt("idx");
				preparedStatement.close();
				connection.close();
				return idx;
			} else {
				preparedStatement.close();
				connection.close();
				return -1;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	// 내정보 페이지 구현
	public SignupUser detalisData(int idx) {
		SignupUser resultData = new SignupUser();
		try {
			// open
			Connection connection = null;
			Statement statement = null;
			ResultSet resultset = null;

			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:XE", "oaiagame", "oaiagame");
			// use
			String query = "SELECT * FROM users WHERE idx=" + idx;
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				resultData.idx = resultSet.getInt("idx");
				resultData.id = resultSet.getString("user_id");
				resultData.pwd = resultSet.getString("user_pwd");
				resultData.name = resultSet.getString("user_name");
				resultData.birth = resultSet.getString("user_birth");
				resultData.email = resultSet.getString("user_email");
				resultData.nickname = resultSet.getString("user_nickName");
			}
			preparedStatement.close();
			// close
			connection.close();
		} catch (Exception e) {
		}
		return resultData;
	}

	// 내정보 수정
	public boolean updateData(SignupUser signupUser) {
		try {
			// open
			Connection connection = null;
			Statement statement = null;
			ResultSet resultset = null;

			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:XE", "oaiagame", "oaiagame");
			// use
			String query = "UPDATE users SET user_pwd=?, user_name=?, user_birth=?, user_email=?, user_nickName=? WHERE idx="
					+ signupUser.idx;
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, signupUser.pwd);
			preparedStatement.setString(2, signupUser.name);
			preparedStatement.setString(3, signupUser.birth);
			preparedStatement.setString(4, signupUser.email);
			preparedStatement.setString(5, signupUser.nickname);

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

	// 자유게시판
	public String boardList() {
		String resultString = "";
		try {
			// open
			Connection connection = null;
			Statement statement = null;
			ResultSet resultset = null;

			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:XE", "oaiagame", "oaiagame");

			// use
			String query = "SELECT * FROM board";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int idx = resultSet.getInt("idx");
				String user_title = resultSet.getString("user_title");
				String user_content = resultSet.getString("user_content");
				String id = resultSet.getString("id");
				String created = resultSet.getString("created");
				String updated = resultSet.getString("updated");
				resultString = resultString + "<tr>";
				resultString = resultString + "<td>" + idx + "</td><td>" + user_title + "</td><td>" + id + "</td><td>"
						+ created + "</td><td>" + "</td>";
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

	public String boardSelect() {
		String resultString = "";
		try {
			// open
			Connection connection = null;
			Statement statement = null;
			ResultSet resultset = null;
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:XE", "oaiagame", "oaiagame");

			// use
			String query = "SELECT * FROM board";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int idx = resultSet.getInt("idx");
				String user_title = resultSet.getString("user_title");
				String user_content = resultSet.getString("user_content");
				String id = resultSet.getString("id");
				String created = resultSet.getString("created");
				String updated = resultSet.getString("updated");
				resultString = resultString + "<tr>";
				resultString = resultString + "<td>" + idx + "</td><td>" + user_title + "</td><td>" + user_content
						+ "</td><td>" + id + "</td><td>" + created + "</td><td>" + "</td>";
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

	public boolean boardInsert(Board board) {
		try {
			// open
			Connection connection = null;
			Statement statement = null;
			ResultSet resultset = null;
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:XE", "oaiagame", "oaiagame");

			String fieldString = "idx, user_title, user_content, user_idx, created, updated";
			String query = "INSERT INTO board (" + fieldString + ") VALUES (?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, "board_idx.NEXTVAL");
			preparedStatement.setString(2, board.user_title);
			preparedStatement.setString(3, board.user_content);
			preparedStatement.setInt(4, board.user_idx);
			preparedStatement.setString(5, board.created);
			preparedStatement.setString(6, board.updated);

			int finalResult = preparedStatement.executeUpdate();

			if (finalResult < 1) {
				preparedStatement.close();
				connection.close();
				return false;
			}
			preparedStatement.close();
			connection.close();
			return true;
			// close
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

}
