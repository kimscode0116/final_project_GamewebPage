package com.kopo.oaiaGamLab;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GameDB {

	//게임 랭킹 조회
		public String rankData() {
			String resultString = "";
			try {
				// open
				Connection connection = null;
				Statement statement = null;
				ResultSet resultset = null;

				Class.forName("oracle.jdbc.driver.OracleDriver");
				connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:XE", "oaiagame", "oaiagame");

				// use
				String query = "SELECT * FROM ranking order by score desc";
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery();

				while (resultSet.next()) {
					String nickname = resultSet.getString("nickname");
					int score = resultSet.getInt("score");
					int index = resultSet.getRow();
						resultString = resultString + "<tr>";
						resultString = resultString + "<td>" + index + "</td><td>" + nickname + "</td><td>" + score + "</td>";
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
		
		//게임 랭킹 조회
		public String rank123Data() {
			String resultString = "";
			try {
				// open
				Connection connection = null;
				Statement statement = null;
				ResultSet resultset = null;

				Class.forName("oracle.jdbc.driver.OracleDriver");
				connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:XE", "oaiagame", "oaiagame");

				// use
				String query = "SELECT * FROM ranking WHERE rownum<=3 order by score desc";
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery();

				while (resultSet.next()) {
					String nickname = resultSet.getString("nickname");
					int score = resultSet.getInt("score");
					int index = resultSet.getRow();
						resultString = resultString + "<div class='ranking1'>";
						resultString = resultString + "<span>" + index+"<br>"+nickname + "<br> [ " + score + " ]</span>";
						resultString = resultString + "</div>";
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
