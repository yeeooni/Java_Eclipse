package com.kitri.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.kitri.dto.Product;
import com.kitri.dto.ProductCategory;

public class ProductDao {

	public List<Product> selectAll() {

		List<Product> list = new ArrayList<Product>();

		// 2) DB CONNECTION
		Connection conn = null;
		// 3) SQL 송신
		PreparedStatement pstmt = null;
		// 4) 결과 수신
		ResultSet rs = null;

		try {
			// 1) JDBC DRIVER LOADING

			Class.forName("oracle.jdbc.driver.OracleDriver");

			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "kitri", "kitri");

			StringBuffer sql = new StringBuffer();

			sql.append("SELECT cate_no, cate_name, prod_no, prod_name, prod_price, prod_detail \n");
			sql.append("FROM product p JOIN product_category pc \n");
			sql.append("ON p.prod_cate = pc.cate_no \n");
			sql.append("ORDER BY cate_no, prod_name");

			pstmt = conn.prepareStatement(sql.toString());

			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				String prod_no = rs.getString("prod_no");
				String prod_name = rs.getString("prod_name");
				int prod_price = rs.getInt("prod_price");
				String prod_detail = rs.getString("prod_detail");

				String cate_no = rs.getString("cate_no");
				String cate_name = rs.getString("cate_name");

				ProductCategory pc = new ProductCategory(cate_no, cate_name);
				Product p = new Product(prod_no, prod_name, prod_price, prod_detail, pc);

				list.add(p);
			}

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			// 5) 연결 닫기

			try {
				if (rs != null)
					rs.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}

			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}

			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}

		}

		return list;

	}

	public Product selectByNo() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Product p = null;
		try {
			// 드라이버 연결
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 디비 연결
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "kitri", "kitri");
			
			StringBuffer sql = new StringBuffer();
			
			sql.append("SELECT cate_no, cate_name, prod_no, prod_name, prod_price, prod_detail \n");
			sql.append("FROM product p JOIN product_category pc \n"); 
			sql.append("ON p.prod_cate = pc.cate_no \n"); 
			sql.append("WERER prod_no = ? \n");
		    sql.append("ORDER BY prod_name \n");

		    pstmt = conn.prepareStatement(sql.toString());
		    
		    rs = pstmt.executeQuery();
		    
		    if(rs.next()) {
		    	String prod_no = rs.getString("prod_no");
				String prod_name = rs.getString("prod_name");
				int prod_price = rs.getInt("prod_price");
				String prod_detail = rs.getString("prod_detail");

				String cate_no = rs.getString("cate_no");
				String cate_name = rs.getString("cate_name");

				ProductCategory pc = new ProductCategory(cate_no, cate_name);
				p = new Product(prod_no, prod_name, prod_price, prod_detail, pc);
				
		    }
	
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			if(rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			if(pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			if(conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			
		}
		
		return p;

	}
	
	
	
	
	
	
}
