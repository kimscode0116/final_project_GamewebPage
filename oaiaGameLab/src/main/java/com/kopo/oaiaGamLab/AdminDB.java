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
	         String query = "CREATE TABLE users(" + " idx NUMBER(5) PRIMARY KEY," + " user_id VARCHAR2(4000) NOT NULL,"
	               + " user_pwd VARCHAR2(4000) NOT NULL," + " user_name VARCHAR2(4000) NOT NULL,"
	               + " user_birth VARCHAR2(4000) NOT NULL," + " user_email VARCHAR2(4000),"
	               + " user_nickName VARCHAR2(4000) NOT NULL," + " join_date VARCHAR2(4000)" + ")";
	         statement = connection.createStatement();
	         int result = statement.executeUpdate(query);

	         String query2 = "CREATE SEQUENCE users_idx INCREMENT BY 1 START WITH 1";
	         Statement statement2 = connection.createStatement();
	         int result2 = statement2.executeUpdate(query2);

	         String query3 = "CREATE TABLE admins(" + " idx NUMBER(5) PRIMARY KEY," + " admin_id VARCHAR2(4000),"
	               + " admin_pwd VARCHAR(4000)," + " admin_name VARCHAR2(4000)" + ")";
	         Statement statement3 = connection.createStatement();
	         int result3 = statement3.executeUpdate(query3);

	         String query4 = "CREATE TABLE board(" + " idx NUMBER(5) PRIMARY KEY,"
	               + " user_title VARCHAR2(4000) NOT NULL," + " user_id VARCHAR2(4000) NOT NULL,"
	               + " user_content VARCHAR2(4000) NOT NULL," + " created VARCHAR2(4000) NOT NULL" + ")";
	         Statement statement4 = connection.createStatement();
	         int result4 = statement4.executeUpdate(query4);

	         String query5 = "CREATE SEQUENCE board_idx INCREMENT BY 1 START WITH 1";
	         Statement statement5 = connection.createStatement();
	         int result5 = statement4.executeUpdate(query5);

	         String query6 = "CREATE TABLE question(" + " idx NUMBER(5) PRIMARY KEY,"
	               + " question_title VARCHAR2(4000) NOT NULL," + " user_id VARCHAR2(4000) NOT NULL,"
	               + " question_content VARCHAR2(4000) NOT NULL," + " created VARCHAR2(4000) NOT NULL,"
	               + " answer VARCHAR2(4000)" + ")";
	         Statement statement6 = connection.createStatement();
	         int result6 = statement6.executeUpdate(query6);

	         String query7 = "CREATE SEQUENCE question_idx INCREMENT BY 1 START WITH 1";
	         Statement statement7 = connection.createStatement();
	         int result7 = statement7.executeUpdate(query7);
	         
	         String query8 = "CREATE TABLE ranking("
	               + " idx NUMBER(5) PRIMARY KEY,"
	               + " nickname VARCHAR2(4000) NOT NULL,"
	               + " score NUMBER(5)"
	               + ")";
	         Statement statement8 = connection.createStatement();
	         int result8 = statement8.executeUpdate(query8);
	         
	         String query9 = "CREATE SEQUENCE ranking_idx INCREMENT BY 1 START WITH 1";
	         Statement statement9 = connection.createStatement();
	         int result9 = statement9.executeUpdate(query9);
	         
	         String query10 = "CREATE TABLE COMMENTS(" + " idx NUMBER(5) PRIMARY KEY,"
	                     + " user_id VARCHAR2(4000) NOT NULL," + " comments VARCHAR2(4000) NOT NULL,"
	                     + " created VARCHAR2(4000) NOT NULL," + " board_idx NUMBER(5) NOT NULL" + ")";
	            Statement statement10 = connection.createStatement();
	            int result10 = statement10.executeUpdate(query10);
	               
	            String query11 = "CREATE SEQUENCE comments_idx INCREMENT BY 1 START WITH 1";
	            Statement statement11 = connection.createStatement();
	            int result11 = statement11.executeUpdate(query11);

			statement.close();
			statement2.close();
			statement3.close();
			statement4.close();
			statement5.close();
			statement6.close();
			statement7.close();
			statement8.close();
			statement9.close();
			statement10.close();
			statement11.close();
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
				String name = resultSet.getString("user_name");
				String birth = resultSet.getString("user_birth");
				String email = resultSet.getString("user_email");
				String nickname = resultSet.getString("user_nickName");
				String join_date = resultSet.getString("join_date");
				resultString = resultString + "<tr>";
				resultString = resultString + "<td class='idx'>" + idx + "</td><td class='id'>" + id
						+ "</td><td class='name'>" + name + "</td><td class='birth'>" + birth
						+ "</td><td class='email'>" + email + "</td><td class='nickname'>" + nickname
						+ "</td><td class='join_date'>" + join_date + "</td>";
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

	   // Q&A 리스트 조회
	   public String selectQuestion() { // 관리자 권한 - 전체 Q&A 조회
	      String resultString = "";
	      try {
	         // open
	         Connection connection = null;
	         Statement statement = null;
	         ResultSet resultset = null;

	         Class.forName("oracle.jdbc.driver.OracleDriver");
	         connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:XE", "oaiagame", "oaiagame");

	         // use
	         String query = "SELECT IDX, QUESTION_TITLE, USER_ID, QUESTION_CONTENT, CREATED, CASE WHEN ANSWER IS NOT NULL THEN 'O' ELSE 'X' END AS answer FROM QUESTION";
	         PreparedStatement preparedStatement = connection.prepareStatement(query);
	         ResultSet resultSet = preparedStatement.executeQuery();

	         while (resultSet.next()) {
	            int idx = resultSet.getInt("idx");
	            String question_title = resultSet.getString("question_title");
	            String user_id = resultSet.getString("user_id");
	            String created = resultSet.getString("created");
	            String answer = resultSet.getString("answer");
	            resultString = resultString + "<tr>";
	            resultString = resultString + "<td class='idx'>" + idx + "</td><td class='title'><a href='questionupdate?idx="+idx+"'>" + question_title
	                  + "</td><td class='id'>" + user_id + "</td><td class='created'>" + created
	                  + "</td><td class='answer'>" + answer + "</td>";
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
	   
	   // Q&A 리스트 조회 _ 답변 완료
	      public String answerYes() {
	         String resultString = "";
	         try {
	            // open
	            Connection connection = null;
	            Statement statement = null;
	            ResultSet resultset = null;

	            Class.forName("oracle.jdbc.driver.OracleDriver");
	            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:XE", "oaiagame", "oaiagame");

	            // use
	            String query = "SELECT IDX, QUESTION_TITLE, USER_ID, QUESTION_CONTENT, CREATED, CASE WHEN ANSWER IS NOT NULL THEN 'O' END AS answer FROM QUESTION WHERE ANSWER IS NOT NULL";
	            PreparedStatement preparedStatement = connection.prepareStatement(query);
	            ResultSet resultSet = preparedStatement.executeQuery();

	            while (resultSet.next()) {
	               int idx = resultSet.getInt("idx");
	               String question_title = resultSet.getString("question_title");
	               String user_id = resultSet.getString("user_id");
	               String created = resultSet.getString("created");
	               String answer = resultSet.getString("answer");
	               resultString = resultString + "<tr>";
	               resultString = resultString + "<td class='idx'>" + idx + "</td><td class='title'><a href='questionupdate?idx="+idx+"'>" + question_title
	                     + "</td><td class='id'>" + user_id + "</td><td class='created'>" + created
	                     + "</td><td class='answer'>" + answer + "</td>";
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
	      
	      // Q&A 리스트 조회 _ 답변 미완료
	      public String answerNo() {
	         String resultString = "";
	         try {
	            // open
	            Connection connection = null;
	            Statement statement = null;
	            ResultSet resultset = null;

	            Class.forName("oracle.jdbc.driver.OracleDriver");
	            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:XE", "oaiagame", "oaiagame");

	            // use
	            String query = "SELECT IDX, QUESTION_TITLE, USER_ID, QUESTION_CONTENT, CREATED, CASE WHEN ANSWER IS NULL THEN 'X' END AS answer FROM QUESTION WHERE ANSWER IS NULL";
	            PreparedStatement preparedStatement = connection.prepareStatement(query);
	            ResultSet resultSet = preparedStatement.executeQuery();

	            while (resultSet.next()) {
	               int idx = resultSet.getInt("idx");
	               String question_title = resultSet.getString("question_title");
	               String user_id = resultSet.getString("user_id");
	               String created = resultSet.getString("created");
	               String answer = resultSet.getString("answer");
	               resultString = resultString + "<tr>";
	               resultString = resultString + "<td class='idx'>" + idx + "</td><td class='title'><a href='questionupdate?idx="+idx+"'>" + question_title
	                     + "</td><td class='id'>" + user_id + "</td><td class='created'>" + created
	                     + "</td><td class='answer'>" + answer + "</td>";
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
	
	
	// Q&A update
	public boolean questionUpdate(Question question) {
		try {
			// open
			Connection connection = null;
			Statement statement = null;
			ResultSet resultset = null;

			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:XE", "oaiagame", "oaiagame");
			// use
			String query = "UPDATE question SET answer=? WHERE idx=" + question.idx;
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, question.answer);
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
	
	
	// 관리자 게임 scroe
		public String adminScore(String adminId) {
			try {
				// open
				Connection connection = null;
				Statement statement = null;
				ResultSet resultSet = null;
				
				Class.forName("oracle.jdbc.driver.OracleDriver");
				connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:XE", "oaiagame", "oaiagame");

				String query1 = "SELECT admin_name FROM admins WHERE admin_id ='" + adminId + "'"; // 닉네임 가져오기
				PreparedStatement preparedStatement = connection.prepareStatement(query1);
				ResultSet resultSet1 = preparedStatement.executeQuery();
				String adminName = "";
				if (resultSet1.next()) {
					adminName = resultSet1.getString("admin_name");
				}
				
				String query2 = "SELECT * FROM ranking WHERE nickname=" + "'" + adminName + "'";
				preparedStatement = connection.prepareStatement(query2);
				ResultSet resultSet2 = preparedStatement.executeQuery();
				if (resultSet2.next()) {
					
				} else {
					String query3 = "INSERT INTO ranking (idx, nickname, score)" + "VALUES (ranking_idx.nextval, '" + adminName + "', 0)"; // ranking에 닉, score = 0 추가
					Statement statement1 = connection.createStatement();
					int result = statement1.executeUpdate(query3); // executeUpdate는 결과값만 나옴 (update, insert에 사용!)
				}
				preparedStatement.close();
				connection.close();

				return adminName;
		
			} catch (Exception e) {
				e.printStackTrace();
				return "ERROR";
			}
		}
}
