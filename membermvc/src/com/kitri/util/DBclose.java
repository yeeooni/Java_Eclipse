package com.kitri.util;

import java.sql.*;

public class DBclose {

	public static void close(Connection con, Statement stmt) {
		try {

			if (stmt != null)
				stmt.close();

			if (con != null)
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void close(Connection con, Statement stmt, ResultSet rs) {
		try {
			if (rs != null)
				rs.close();

			if (stmt != null)
				stmt.close();

			if (con != null)
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void close(Connection con, PreparedStatement pstmt) {
		try {

			if (pstmt != null)
				pstmt.close();

			if (con != null)
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void close(Connection con, PreparedStatement pstmt, ResultSet rs) {
		try {
			if (rs != null)
				rs.close();

			if (pstmt != null)
				pstmt.close();

			if (con != null)
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}