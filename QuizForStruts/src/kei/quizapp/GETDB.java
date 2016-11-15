package kei.quizapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.ArrayList;

public class GETDB {
	static Connection conn = null;
	
	public static q_beans GetAll(int qmax){
		q_beans quizList = new q_beans();
		quizbean qb;
		try{
		Conect();
		
		String sql = "SELECT que,a1,a2,a3,a4,ans FROM questions ORDER BY RAND() limit "+ qmax + ";";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			qb = new quizbean();
			
			qb.setQuestion(rs.getString("que"));
			qb.setCh1(rs.getString("a1"));
			qb.setCh2(rs.getString("a2"));
			qb.setCh3(rs.getString("a3"));
			qb.setCh4(rs.getString("a4"));
			qb.setAnswer(rs.getInt("ans"));
			
			quizList.add(qb);
		}
		
		if (conn != null) {
			conn.close();
		}
		
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException:" + e.getMessage());
		} catch (SQLException e) {
			System.out.println("SQLException:" + e.getMessage());
		} catch (Exception e) {
			System.out.println("Exception:" + e.getMessage());
		}
		return quizList;
	}
	
	//コネクション確立用メソッド
	private static void Conect() throws Exception{
		conn = null;

		String url = "jdbc:mysql://localhost/quizappdb";
		String user = "quizmaster";
		String password = "quiz";

		// 接続の確立
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url, user, password);
		
		
	}

}
