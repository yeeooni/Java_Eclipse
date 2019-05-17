package com.kitri.jdbctest;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SelectTest {

	
	public SelectTest(){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 연결 성공!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Connection makeConnection() throws SQLException {
		Connection con = null;
		con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.14.52:1521:orcl","kitri", "kitri");
		System.out.println("DB 연결 성공!");
		return con;
	}
	
//	public List<MemberDto> memberList(String searchName) {
//		List<MemberDto> list = new ArrayList<MemberDto>();
//		
//		Connection con = null;
//		Statement stmt = null;
//		ResultSet rs = null;
//		
//		try {
//			con = makeConnection();
//			String sql = "";
//			sql += "select no, name, id, joindate \n";
//			sql += "from jdbctest \n";
//			if(searchName != null)
//				sql += "where name = '" + searchName + "'";
//			
//			stmt = con.createStatement();
//			rs = stmt.executeQuery(sql);
////			MemberDto memberDto = new MemberDto();
//			
//			while(rs.next()) {
//				MemberDto memberDto = new MemberDto();
////				memberDto.setNo(rs.getInt(1));
//				memberDto.setNo(rs.getInt("no"));
//				memberDto.setName(rs.getString("name"));
//				memberDto.setId(rs.getString("id"));
//				memberDto.setJoindate(rs.getString("joindate"));
//				
//				list.add(memberDto);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}finally {
//				try {
//					if(rs != null)
//						rs.close();
//					if(stmt != null)
//						stmt.close();
//					if(con != null)
//						con.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//		}
//		
//		return list;
//	}
//	
	public MemberDto memberSearch(int no) {
		
		MemberDto memberDto = null;
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			con = makeConnection();
			
			String sql = "";
			sql += "select no, name, id, decode(joindate, sysdate, to_char(joindate, 'hh24:mi:ss'), to_char(joindate, 'yy.mm.dd')) joindate \n";
			sql += "from jdbctest \n";
			sql += "where no = '" + no + "'";
				
			stmt = con.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				memberDto = new MemberDto();
				memberDto.setNo(rs.getInt("no"));
				memberDto.setName(rs.getString("name"));
				memberDto.setId(rs.getString("id"));
				memberDto.setJoindate(rs.getString("joindate"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null)
					rs.close();
				if(stmt != null)
					stmt.close();
				if(con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
		return memberDto;
	}
	
	public static void main(String[] args) {
		SelectTest st = new SelectTest();
//		String searchName = null;
//		List<MemberDto> list = st.memberList(searchName);
//		//향상된 for문
//		for(MemberDto memberDto : list) {
//			System.out.println(memberDto.getNo() + "\t" + memberDto.getName() + "\t" + memberDto.getId() + "\t" + memberDto.getJoindate());
//		}
//		int no = 201;
		int no = 122;
//		System.out.println("회원 번호가 " + no + "인 회원 검색 !!");
	
//		int no = 10;
		MemberDto memberDto = st.memberSearch(no);
	
		if(memberDto != null) {
			System.out.println("이름 :" + memberDto.getName() + "\n" + "id : " + memberDto.getId() + "\n" + "가입일 : " + memberDto.getJoindate());
		} else {
			System.out.println(no + "번 회원은 존재하지 않습니다.");
		}
		
//		이름 : 홍길동
//		id : hong
//		가입일 : 10:27:24 (오늘)
//		가입일 : 19.04.25 (오늘x)
//		10번 회원은 존재하지 않습니다.
	}

	
}
