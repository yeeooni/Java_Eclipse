package com.kitri.haksa.service.db;

import java.sql.*;
import java.util.ArrayList;

import com.kitri.haksa.data.db.HaksaDto;

public class HaksaDao {

	Connection conn = null; // DB����� ����(����)�� ���� ��ü
	PreparedStatement pstm = null; // SQL ���� ��Ÿ���� ��ü
	ResultSet rs = null; // �������� �����Ϳ� ���� ��ȯ���� ���� ��ü

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
				System.out.println(haksa.getName() + "���� DB ����");
			} else {
				System.out.println("DB�������");
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
			conn = getConnection(); // DB�� ����Ǿ��ִ� Ŭ���� ȣ��
			String select = "SELECT s.name, s.age, s.key, j.key_name, s.value FROM SCHOOL s, JOB j WHERE s.KEY = j.KEY AND NAME=?";
			pstm = conn.prepareStatement(select); // DB���̺� select

			pstm.setString(1, name);
			rs = pstm.executeQuery();

			System.out.println("============================================");
//			System.out.println("name", "age", "key", "keyName", "value");

			while (rs.next()) {

				int rage = rs.getInt(1);
				String rname = rs.getString(2);
				int rkey = rs.getInt(3);
				String rkeyName = rs.getString(4);
				String rvalue = rs.getString(5); // Date Ÿ�� ó��

				result = new HaksaDto();

				result.setName(rname);
				result.setAge(rage);
				result.setKey(rkey);
				result.setKeyName(rkeyName);
				result.setValue(rvalue);

			}

		} catch (SQLException sqle) {
			System.out.println("SELECT������ ���� �߻�");
			sqle.printStackTrace();

		} finally {
			// DB ������ �����Ѵ�.
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
				System.out.println(name + "���� ������ �����Ͽ����ϴ�.");
			} else {
				System.out.println("���� ����");
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

			conn = getConnection(); // DB�� ����Ǿ��ִ� Ŭ���� ȣ��
			pstm = conn.prepareStatement(select); // DB���̺� select
			rs = pstm.executeQuery();

			System.out.println("============================================");
//			System.out.println("name", "age", "key", "keyName", "value");

			while (rs.next()) {

				String rname = rs.getString(1);
				int rage = rs.getInt(2);
				int rkey = rs.getInt(3);
				String rkeyName = rs.getString("KeyName");
				String rvalue = rs.getString(4); // Date Ÿ�� ó��

				HaksaDto haksadto = new HaksaDto();

				haksadto.setName(rname);
				haksadto.setAge(rage);
				haksadto.setKey(rkey);
				haksadto.setKeyName(rkeyName);
				haksadto.setValue(rvalue);

				list.add(haksadto);

			}

		} catch (SQLException sqle) {
			System.out.println("SELECT������ ���� �߻�");
			sqle.printStackTrace();

		} finally {
			// DB ������ �����Ѵ�.
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

//DB����

	public Connection getConnection() {

		String user = "kitri";
		String pw = "kitri";
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, pw);

			System.out.print("Database�� ���Ἲ��.\n");

		} catch (ClassNotFoundException cnfe) {
			System.out.println("DB ����̹� �ε� ���� :" + cnfe.toString());
		} catch (SQLException sqle) {
			System.out.println("DB ���ӽ��� : " + sqle.toString());
		} catch (Exception e) {
			System.out.println("Unkonwn error");
			e.printStackTrace();
		}
		return conn;
	};

}
