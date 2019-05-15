package com.kitri.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.kitri.dto.Customer;
import com.kitri.exception.NotFoundException;

public class CustomerDao {
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public Customer selectById(String id) throws NotFoundException, SQLException {

		//(1) jdbc 로드
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		//(2) db 연결
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "kitri", "kitri");
			
			StringBuffer sql = new StringBuffer();
			
			sql.append("select * from customer where id = ? \n");
			
			pstmt = con.prepareStatement(sql.toString());
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
				if(rs.next()) {
					Customer c = new Customer();
					c.setId(id);
					c.setName(rs.getString("name"));
					c.setPass(rs.getString("pass"));
					return c;
				} else {
					throw new NotFoundException("아이디에 해당하는 고객이 없습니다.");
				}
			
		} catch (ClassNotFoundException e) {
				throw new NotFoundException("e.getMessage()");
		} finally {
			if(rs != null)
			rs.close();
			if(pstmt != null)
			pstmt.close();
			if(con != null)
			con.close();
		}
	}
	
	public List<Customer> selectByName(String name) throws SQLException{
		
		List<Customer> list = new ArrayList<Customer>();
		
		try {
			// 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 디비 연결 
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl");
			
			StringBuffer sql = new StringBuffer();
			
			sql.append("select * from customer where name = ? \n");
			
			pstmt = con.prepareStatement(sql.toString());
			
			pstmt.setString(1, name);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				Customer customer = new Customer();
				customer.setName(name);
				customer.setId(rs.getString("id"));
				customer.setPass(rs.getString("pass"));
				
				list.add(customer);
			}
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		System.out.println(list);
		return list;
	}
	
	public List<Customer> selectAll(){
		return null;
	}
	
	public void insert(Customer c ) {
		
	}
}
