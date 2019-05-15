package com.kitri.util;

import java.sql.*;

public class DBConnection {

	//static 블록초기화
		static {
			try {
				Class.forName(SiteConstance.DB_DRIVER);
				System.out.println("드라이버 연결 성공!");
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			}
		}
	
		public static Connection makeConnection() throws SQLException {
			return DriverManager.getConnection(SiteConstance.DB_URL, SiteConstance.DB_USERNAME, SiteConstance.DB_PASSWORD);
		}
	
}
