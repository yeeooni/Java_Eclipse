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

			conn = getConnection();
			String insert = "INSERT INTO SCHOOL(NAME, AGE, KEY, VALUE) VALUES(?, ?, ?, ?)";
			pstm = conn.prepareStatement(insert);

			pstm.setString(1, haksa.getName());
			pstm.setInt(2, haksa.getAge());
			pstm.setInt(3, haksa.getKey());
			pstm.setString(4, haksa.getValue());

			int result = pstm.executeUpdate();

			if (result > 0) {
				System.out.println(haksa.getName() + "님의 DB 저장");
			} else {
				System.out.println("DB연결실패");
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			try {
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
				}
				conn.close();

			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

	}

	public HaksaDto findName(String name) {

		HaksaDto result = null;

		try {
			conn = getConnection(); // DB에 연결되어있는 클래스 호출
			String select = "SELECT s.name, s.age, s.key, j.key_name, s.value FROM SCHOOL s, JOB j WHERE s.KEY = j.KEY AND NAME=?";
			pstm = conn.prepareStatement(select); // DB테이블 select

			pstm.setString(1, name);
			rs = pstm.executeQuery();

			System.out.println("============================================");
//			System.out.println("name", "age", "key", "keyName", "value");

			while (rs.next()) {

				int rage = rs.getInt(1);
				String rname = rs.getString(2);
				int rkey = rs.getInt(3);
				String rkeyName = rs.getString(4);
				String rvalue = rs.getString(5); // Date 타입 처리

				result = new HaksaDto();

				result.setName(rname);
				result.setAge(rage);
				result.setKey(rkey);
				result.setKeyName(rkeyName);
				result.setValue(rvalue);

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
		return result;
	}

	public int delete(String name) {

		int result = 0;

		try {
			conn = getConnection();
			String delete = "DELETE FROM SCHOOL WHERE name = ?";
			pstm = conn.prepareStatement(delete);
		} catch (SQLException e2) {

			e2.printStackTrace();
		}
		try {
			pstm.setString(1, name);
		} catch (SQLException e1) {

			e1.printStackTrace();
		}

		try {
			result = pstm.executeUpdate();

			if (result > 0) {
				System.out.println(name + "님의 정보를 삭제하였습니다.");
			} else {
				System.out.println("삭제 실패");
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<HaksaDto> selectAllList() {

		ArrayList<HaksaDto> list = new ArrayList<HaksaDto>();

		try {
			String select = "SELECT s.name, s.age, s.key, j.key_name, s.value FROM SCHOOL s, JOB j WHERE s.KEY = j.KEY";

			conn = getConnection(); // DB에 연결되어있는 클래스 호출
			pstm = conn.prepareStatement(select); // DB테이블 select
			rs = pstm.executeQuery();

			System.out.println("============================================");
//			System.out.println("name", "age", "key", "keyName", "value");

			while (rs.next()) {

				String rname = rs.getString(1);
				int rage = rs.getInt(2);
				int rkey = rs.getInt(3);
				String rkeyName = rs.getString("KeyName");
				String rvalue = rs.getString(4); // Date 타입 처리

				HaksaDto haksadto = new HaksaDto();

				haksadto.setName(rname);
				haksadto.setAge(rage);
				haksadto.setKey(rkey);
				haksadto.setKeyName(rkeyName);
				haksadto.setValue(rvalue);

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

//DB연결

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
