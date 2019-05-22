package com.kitri.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.kitri.dto.*;
import com.kitri.exception.AddException;

public class OrderDao {

	public void insert(OrderInfo info) throws AddException{// orderInfo Has a Lines
		// 드라이버 로딩
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// DB 연결
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "kitri", "kitri");
			conn.setAutoCommit(false); // 자동 커밋 해제

			insertInfo(conn, info); // 주문 기본 추가
			List<OrderLine> lines = info.getLines();
			insertLine(conn, lines);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			throw new AddException("주문추가 오류" + e.getMessage());
		} finally {
			if (conn != null)
				try {
					// DB 닫기
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

	}

	public void insertInfo(Connection conn, OrderInfo info) throws SQLException {
		PreparedStatement pstmt = null;

		String sql = "";
		sql += "insert into order_info(order_no, order_id)";
		sql += "	values(order_seq.nextval, ?)";

		try {
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, info.getCustomer().getId());
			pstmt.executeUpdate();
			// } catch (SQLException e) {
			// e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void insertLine(Connection conn, List<OrderLine> lines) throws SQLException {

		PreparedStatement pstmt = null;

		String sql = "insert into order_line(order_no, order_prod_no, order_quantity) \n";
		sql += "values(order_seq.currval, ?, ?) \n";

		try {
			pstmt = conn.prepareStatement(sql.toString());
			for (OrderLine line : lines) {
				String prod_no = line.getProduct().getProd_no();
				pstmt.setString(1, prod_no);

				int quantity = line.getOrder_quantity();
				pstmt.setInt(2, quantity);
				//stmt.executeUpdate();
				pstmt.addBatch();// 일괄처리 추가
			}
			pstmt.executeBatch();// 일괄처리
			// } catch (SQLException e) {
			// e.printStackTrace();

		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public List<OrderInfo>selectById(String id){
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<OrderInfo> list = new ArrayList<OrderInfo>();
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "kitri", "kitri");
			
			String sql = "SELECT info.order_no, order_dt, prod_no, prod_name, prod_price, order_quantity \n";
			sql += "FROM order_info info JOIN order_line line ON info.order_no = line.order_no \n";
			sql += "JOIN product p ON p.prod_no = line.ORDER_PROD_NO \n";
			sql += "WHERE order_id = ? \n";
			sql += "ORDER BY order_no DESC, prod_no \n";
			
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			OrderInfo info = null;
			OrderLine line = null;
			List<OrderLine> lines = null;
			int old_order_no = -1; // 이전 주문 번호 
			
			while (rs.next()) {
				int order_no = rs.getInt("order_no");
				
				if(old_order_no != order_no) { // 주문번호가 다를 경우 OrderInfo 객체 생성 리스트에 추가 
					info = new OrderInfo();
					list.add(info);
					info.setOrder_no(order_no);
					info.setOrder_dt(rs.getDate("order_dt"));
					lines = new ArrayList<OrderLine>();
					info.setLines(lines);
					old_order_no = order_no;
				}
				
				line = new OrderLine();
				String prod_no = rs.getString("prod_no");
				String prod_name = rs.getString("prod_name");
				int prod_price = rs.getInt("prod_price");
				
				Product p = new Product();
				p.setProd_no(prod_no);
				p.setProd_name(prod_name);
				p.setProd_price(prod_price);
				
				
				line.setProduct(p);
				line.setOrder_quantity(rs.getInt("order_quantity"));
				lines.add(line);
			}
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}

}
