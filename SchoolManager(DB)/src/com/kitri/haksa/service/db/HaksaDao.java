package com.kitri.haksa.service.db;

import java.sql.*;
import java.util.ArrayList;

import com.kitri.haksa.data.db.HaksaDto;

public class HaksaDao {

	Connection conn = null; // DB연결된 상태(세션)을 담은 객체
	PreparedStatement pstm = null; // SQL 문을 나타내는 객체
	ResultSet rs = null; // 쿼리문을 날린것에 대한 반환값을 담을 객체

	public HaksaDao() {

	}

	public void register(HaksaDto haksa) {

		try {
			
			String select = "Insert into School values(?, ?, ?, ?)";
			
			conn = pstm.getConnection();
			pstm = conn.prepareStatement(select);
			
			pstm.setString(1, haksa.getName());
			pstm.setInt(2, haksa.getAge());
			pstm.setInt(3, haksa.getKey());
			pstm.setString(4, haksa.getValue());
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		
	}

	public HaksaDto findName(String name) {
		return null;
	}

	public int delete(String name) {
		return 0;
	}

	public ArrayList<HaksaDto> selectAll() {

		ArrayList<HaksaDto> list = new ArrayList<HaksaDto>();

		try {
			String select = "SELECT * FROM School";

			conn = pstm.getConnection(); // DB에 연결되어있는 클래스 호출
			pstm = conn.prepareStatement(select); // DB테이블 select
			rs = pstm.executeQuery();

			System.out.println("============================================");
//			System.out.println("name", "age", "key", "keyName", "value");

			while (rs.next()) {
				HaksaDto haksadto = new HaksaDto();

				String name = rs.getString(1);
				int age = rs.getInt(2);
				int key = rs.getInt(3);
				String keyName = rs.getString(4);
				String value = rs.getString(5); // Date 타입 처리

				String result = name + "\t" + age + "\t" + key + "\t" + keyName + "\t" + value;
				System.out.println(result);
				
				haksadto.setName(name);
				haksadto.setAge(age);
				haksadto.setKey(key);
				haksadto.setKeyName(keyName);
				haksadto.setValue(value);

				list.add(haksadto);

			}

		} catch (SQLException sqle) {
			System.out.println("SELECT문에서 예외 발생");
			sqle.printStackTrace();

		} finally {
			// DB 연결을 종료한다.
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return list;
	}

	public class DBConnection {
//		public static Connection dbConn;

		public Connection getConnection() {

			String user = "kitri";
			String pw = "kitri";
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";

			try {

				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection(url, user, pw);

				System.out.print("Database에 연결성공.\n");

			} catch (ClassNotFoundException cnfe) {
				System.out.println("DB 드라이버 로딩 실패 :" + cnfe.toString());
			} catch (SQLException sqle) {
				System.out.println("DB 접속실패 : " + sqle.toString());
			} catch (Exception e) {
				System.out.println("Unkonwn error");
				e.printStackTrace();
			}
			return conn;
		};
	}

}
