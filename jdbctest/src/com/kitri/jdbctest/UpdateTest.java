package com.kitri.jdbctest;

import java.sql.*;

//java2의 가입을 현재시간으로 수정.
public class UpdateTest {

	
//	1. 드라이버 로딩
	public UpdateTest() {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 연결성공!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Connection makeConnection() throws SQLException {
		Connection con = null;
		con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.14.52:1521:orcl","kitri","kitri");
		System.out.println("db연결 성공!");
		return con;
	}
	
	public static void main(String[] args) {
		
		UpdateTest test = new UpdateTest();
		Connection con = null;
		Statement stmt = null;
		
		int number = 0;
		String name = "안효인";
		String id = "잘생겼다.";
		
		try {
			con = test.makeConnection();
			String sql = "";
			sql += "update jdbctest set id = + '" + id +"' \n";
			sql += "where name = '" + name + "'";
			
			stmt = con.createStatement();
			number = stmt.executeUpdate(sql);
//			System.out.println(number);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(stmt != null)
				stmt.close();
				
				if(con != null)
				con.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		System.out.println(number + "개 update성공!");
	}
	

}
