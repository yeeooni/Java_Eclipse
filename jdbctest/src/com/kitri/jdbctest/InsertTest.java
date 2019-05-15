package com.kitri.jdbctest;

import java.sql.*;

public class InsertTest {

	public InsertTest() {
		try {
//			1.
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 로딩 성공!");
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
	}

	public Connection makeConnection() throws SQLException {
		Connection con = null;

		con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.14.52:1521:orcl", "kitri", "kitri");
		System.out.println("DB연결 성공!");

		return con;
	}

	public static void main(String[] args) {
		InsertTest test = new InsertTest();
		
		String name = "김의연";
		String id = "오늘";
		
		Connection con = null;
		Statement stmt = null;
		int cnt = 0;
		try {
//			2.
			con = test.makeConnection();

//			3.
			String sql = "";
			sql += "insert into jdbctest (no, name, id, joindate) \n";
			sql += "values(jdbctest_no_seq.nextval, '" + name + "' , '" + id + "', sysdate)";
//			System.out.println(sql);
			stmt = con.createStatement();
			
//			4.
			cnt = stmt.executeUpdate(sql);
		} catch (SQLException e) {

			e.printStackTrace();
		}finally {
			try {
				if(stmt != null) {
					stmt.close();
				}
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

			if(cnt != 0) {
				System.out.println("insert success!!");
			} else {
				System.out.println("insert fail!!");
			}
	}
}
