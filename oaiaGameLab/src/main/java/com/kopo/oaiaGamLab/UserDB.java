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
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:XE", "oaiagame", "oaiagame");
			// use
			signupUser.pwd = sha256(signupUser.pwd);

			// 아이디,닉네임 중복확인
			String query1 = "SELECT * FROM users WHERE user_id=" + "'" + signupUser.id + "' OR user_nickname=" + "'"
					+ signupUser.nickname + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(query1);
			ResultSet resultdata = preparedStatement.executeQuery();
			if (resultdata.next()) {
				String user_id = resultdata.getString("user_id");
				String user_nickname = resultdata.getString("user_nickname");
				if (user_id.equals(signupUser.id)) {
					resultString = "중복된 아이디가 존재합니다.";
				} else if (user_nickname.equals(signupUser.nickname)) {
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
				System.out.println("오류1?" + signupUser.id);
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

	   // 자유게시판 관리자 게시글
	   public String noticeSelect() {
	      String resultString = "";
	      try {
	         // open
	         Connection connection = null;
	         Statement statement = null;
	         ResultSet resultset = null;

	         Class.forName("oracle.jdbc.driver.OracleDriver");
	         connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:XE", "oaiagame", "oaiagame");

	         // use
	         String query = "SELECT * FROM board WHERE user_nickname = '" + "관리자" + "'";
	         PreparedStatement preparedStatement = connection.prepareStatement(query);
	         ResultSet resultSet = preparedStatement.executeQuery();

	         while (resultSet.next()) {
	            int idx = resultSet.getInt("idx");
	            String user_title = resultSet.getString("user_title");
	            String user_content = resultSet.getString("user_content");
	            String user_nickname = resultSet.getString("user_nickname");
	            String created = resultSet.getString("created");
	            resultString = resultString + "<tr>";
	            resultString = resultString + "<td class='idx'>" + "**공지**" + "</td><td class='title'><a href='#'>"
	                  + user_title + "</a></td><td class='name'>" + user_nickname + "</td><td class='date'>" + created
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
	   
	   
	   // 자유게시판 일반 게시글
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
	         String query = "SELECT * FROM board WHERE user_nickname != '" + "관리자" + "'";
	         PreparedStatement preparedStatement = connection.prepareStatement(query);
	         ResultSet resultSet = preparedStatement.executeQuery();

	         while (resultSet.next()) {
	            int idx = resultSet.getInt("idx");
	            String user_title = resultSet.getString("user_title");
	            String user_content = resultSet.getString("user_content");
	            String user_nickname = resultSet.getString("user_nickname");
	            String created = resultSet.getString("created");
	            resultString = resultString + "<tr>";
	            resultString = resultString + "<td class='idx'>" + idx + "</td><td class='title'><a href='#'>"
	                  + user_title + "</a></td><td class='name'>" + user_nickname + "</td><td class='date'>" + created
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

	   // 자유게시판 닉네임 가져오기
	   public String nicknameSelect(SignupUser user) {
	      System.out.println("오류1");
	      System.out.println(user.id);
	      String user_nickname = "";
	      try {
	         // open
	         Connection connection = null;
	         Statement statement = null;
	         ResultSet resultset = null;

	         Class.forName("oracle.jdbc.driver.OracleDriver");
	         connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:XE", "oaiagame", "oaiagame");

	         String query = "SELECT user_nickname FROM users WHERE user_id ='" + user.id + "'"; // 닉네임 가져오기
	         PreparedStatement preparedStatement = connection.prepareStatement(query);
	         ResultSet resultSet = preparedStatement.executeQuery();
	         if (resultSet.next()) {
	            user_nickname = resultSet.getString("user_nickname");
	         }
	         System.out.println("오류2" + user_nickname);
	         preparedStatement.close();
	         connection.close();
	         return user_nickname;
	         // close
	      } catch (ClassNotFoundException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      } catch (SQLException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }
	      return user_nickname;
	   }

	   // 자유게시판 게시글 등록
	   public boolean boardInsert(Board board) {
	      System.out.println("게시판 : " + board.user_title);
	      System.out.println("게시판내용 : " + board.user_content);
	      System.out.println("글쓴이 : " + board.user_nickname);
	      try {
	         // open
	         Connection connection = null;
	         Statement statement = null;
	         ResultSet resultset = null;

	         Class.forName("oracle.jdbc.driver.OracleDriver");
	         connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:XE", "oaiagame", "oaiagame");

	         String fieldString = "idx, user_title, user_content, user_nickname, created";
	         String query = "INSERT INTO board (" + fieldString + ") VALUES (board_idx.NEXTVAL, ?, ?, ?, ?)";
	         PreparedStatement preparedStatement = connection.prepareStatement(query);
	         preparedStatement.setString(1, board.user_title);
	         preparedStatement.setString(2, board.user_content);
	         preparedStatement.setString(3, board.user_nickname);
	         preparedStatement.setString(4, board.created);

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

	public Board boardContentDatail(int contentsIdx) {
		Board resultData = new Board();
		try {
			// open
			Connection connection = null;
			Statement statement = null;
			ResultSet resultset = null;

			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:XE", "oaiagame", "oaiagame");

			// use
			String query = "SELECT * FROM board where idx=" + contentsIdx;
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				resultData.idx = resultSet.getInt("idx");
				resultData.user_title = resultSet.getString("user_title");
				resultData.user_nickname = resultSet.getString("user_nickname");
				resultData.user_content = resultSet.getString("user_content");
				resultData.created = resultSet.getString("created");
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
		return resultData;
	}

	// 자유게시판 update
	public boolean boardUpdate(Board board) {
		try {
			// open
			Connection connection = null;
			Statement statement = null;
			ResultSet resultset = null;

			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:XE", "oaiagame", "oaiagame");
			// use
			String query = "UPDATE board SET user_title=?, user_content=? WHERE idx=" + board.idx;
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, board.user_title);
			preparedStatement.setString(2, board.user_content);
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

	// 자유게시판 update
	public boolean boardDelete(int idx) {
		try {
			// open
			Connection connection = null;
			Statement statement = null;
			ResultSet resultset = null;

			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:XE", "oaiagame", "oaiagame");
			// use
			String query = "DELETE FROM board WHERE idx=" + idx;
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			int result = preparedStatement.executeUpdate();
			if (result < 1) {
				preparedStatement.close();
				connection.close();
				return false;
			}
			preparedStatement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// Q&A 유저 질문 등록
	public boolean questionInsert(Question question) {
		System.out.println("Q&A 제목: " + question.question_title);
		System.out.println("작성자: " + question.user_id);
		System.out.println("Q&A 내용 : " + question.question_content);
		try {
			// open
			Connection connection = null;
			Statement statement = null;
			ResultSet resultset = null;

			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:XE", "oaiagame", "oaiagame");

			String fieldString = "idx, question_title, user_id, question_content, created";
			String query = "INSERT INTO question (" + fieldString + ") VALUES (question_idx.NEXTVAL, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, question.question_title);
			preparedStatement.setString(2, question.user_id);
			preparedStatement.setString(3, question.question_content);
			preparedStatement.setString(4, question.created);

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
	
	// 유저 게임 score
		public String userScore(String userId) {
			try {
				// open
				Connection connection = null;
				Statement statement = null;
				ResultSet resultSet = null;
				
				Class.forName("oracle.jdbc.driver.OracleDriver");
				connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:XE", "oaiagame", "oaiagame");

				String query1 = "SELECT user_nickName FROM users WHERE user_id ='" + userId + "'"; // 닉네임 가져오기
				PreparedStatement preparedStatement = connection.prepareStatement(query1);
				ResultSet resultSet1 = preparedStatement.executeQuery();
				String user_nickName = "";
				if (resultSet1.next()) {
					user_nickName = resultSet1.getString("user_nickName");
				}
				
				String query2 = "SELECT * FROM ranking WHERE nickname=" + "'" + user_nickName + "'";
				preparedStatement = connection.prepareStatement(query2);
				ResultSet resultSet2 = preparedStatement.executeQuery();
				if (resultSet2.next()) {
					
				} else {
					String query3 = "INSERT INTO ranking (idx, nickname, score)" + "VALUES (ranking_idx.nextval, '" + user_nickName + "', 0)"; // ranking에 닉, score = 0 추가
					Statement statement1 = connection.createStatement();
					int result = statement1.executeUpdate(query3); // executeUpdate는 결과값만 나옴 (update, insert에 사용!)
					statement1.close();
				}
				preparedStatement.close();
				connection.close();
				
				return user_nickName;

			} catch (Exception e) {
				e.printStackTrace();
				return "ERROR";
			}
		}


}
