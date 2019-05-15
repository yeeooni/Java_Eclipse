package com.kitri.jdbctest;

import java.sql.*;

public class ConnectionTest {
	
	//드라이버 연결
	public ConnectionTest() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 로딩 성공!!!");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
	}
	
	//디비 연결 
	private void dbConnect() {
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.14.52:1521:orcl", "kitri", "kitri");
			System.out.println("DB Connection Success!!!!!!!");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	
	
	
	public static void main(String[] args) {
		ConnectionTest ct = new ConnectionTest();
		ct.dbConnect();
	}





}
