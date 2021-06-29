package com.kopo.oaiaGamLab;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.sqlite.SQLiteConfig;

public class UserDB {
	
	//회원가입 메서드
	public String signup(SignupUser signupUser) {
		String resultString = "";
		try {
			// open
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			Connection connection = DriverManager.getConnection("jdbc:sqlite:/" + "C:/tomcat/oaiaGamLab.db",
					config.toProperties());
			// use
			signupUser.pwd = sha256(signupUser.pwd);

			//아이디 중복확인
			String query1 = "SELECT * FROM user WHERE user_id=" + "'" + signupUser.id + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(query1);
			ResultSet resultdata = preparedStatement.executeQuery();
			if (resultdata.next()) {
				resultString = "중복된 아이디가 존재합니다.";
			} else {
				String query = "INSERT INTO user (user_id,user_pwd,user_name,user_birth,user_email,user_nickName,join_date)"
						+ "VALUES(?, ?, ?, ?, ?, ?, ?)";
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, signupUser.id);
				preparedStatement.setString(2, signupUser.pwd);
				preparedStatement.setString(3, signupUser.name);
				preparedStatement.setString(4, signupUser.birth);
				preparedStatement.setString(5, signupUser.email);
				preparedStatement.setString(6, signupUser.nickname);
				preparedStatement.setString(7, signupUser.join_date);
				int result = preparedStatement.executeUpdate();
				if (result < 1) {
					resultString = "error";
				}else {
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
	//비밀번호 해시처리
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
	
	   //로그인 메서드
	   public int userlogin(String id, String pwd) {
	      try {
	         // open
	         Class.forName("org.sqlite.JDBC");
	         SQLiteConfig config = new SQLiteConfig();
	         Connection connection = DriverManager.getConnection("jdbc:sqlite:/" + "C:/tomcat/oaiaGamLab.db",
	               config.toProperties());
	         // use
	         pwd = this.sha256(pwd);
	         
	         //아이디, 패스워드 있는지 검사
	         String query = "SELECT * FROM user WHERE user_id=? AND user_pwd=?";
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
	   
	   //내정보 페이지 구현 
	   public SignupUser detalisData(int idx) {
	      SignupUser resultData = new SignupUser();
	      try {
	         // open
	         Class.forName("org.sqlite.JDBC");
	         SQLiteConfig config = new SQLiteConfig();
	         Connection connection = DriverManager.getConnection("jdbc:sqlite:/" + "C:/tomcat/oaiaGamLab.db",
	               config.toProperties());
	         // use
	         String query = "SELECT * FROM user WHERE idx=" + idx;
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
	   
	   //내정보 수정
	   public boolean updateData(SignupUser signupUser) {
	      try {
	         // open
	         Class.forName("org.sqlite.JDBC");
	         SQLiteConfig config = new SQLiteConfig();
	         Connection connection = DriverManager.getConnection("jdbc:sqlite:/" + "C:/tomcat/oaiaGamLab.db",
	               config.toProperties());
	         // use
	         String query = "UPDATE user SET user_pwd=?, user_name=?, user_birth=?, user_email=?, user_nickName=? WHERE idx="
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
}
