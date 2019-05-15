package com.kitri.jdbctest;

import java.sql.*;

public class DeleteTest {

	
	public DeleteTest() {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 연결 성공!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private Connection makeConnection() throws SQLException {
		Connection con = null;
		con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.14.52:1521:orcl", "kitri", "kitri");
		System.out.println("DB연결 성공!");
		return con;
	}
	
	public static void main(String[] args) {
		DeleteTest test = new DeleteTest();
		Connection con = null;
		Statement stmt = null;
		
		String id = "업데이트됬네";
		int number = 0;
		try {
			
			con = test.makeConnection();
			
			String sql = "";
			sql += "delete from jdbctest ";
			sql += "where id = '" + id + "'";
			
			stmt = con.createStatement();
			stmt.executeUpdate(sql);
			
			System.out.println(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
				try {
					if(stmt != null)
					stmt.close();
					
					if(con != null)
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		System.out.println(number + "Delete 문 삭제 되었습니다.");
	}




}
