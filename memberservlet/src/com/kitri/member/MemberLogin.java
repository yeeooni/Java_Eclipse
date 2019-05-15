package com.kitri.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class MemberLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 로딩 성공!");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String name = null;
		try {
			
			String id = request.getParameter("id");
			String pass = request.getParameter("pass");
			con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.14.52:orcl", "kitri", "kitri");
			StringBuffer sql = new StringBuffer();
			
			sql.append("select name \n");
			sql.append("from member \n");
			sql.append("where id = ? and pass = ? \n");
			
			pstmt = con.prepareStatement(sql.toString());
			
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			
			rs = pstmt.executeQuery();
			
				if(rs.next()) {
					name = rs.getString("name");
				}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
				try {
					if(rs != null)
					rs.close();
					
					if(pstmt != null)
						pstmt.close();
					
					if(con != null)
						con.close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
		
		}
		
		response.setContentType("text/html;charset =utf-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("	<body>");
			if(name != null) {
				out.println(name + "님 안녕하세요! <br/>");
			} else {
				out.println("<font size =\"5\"; color = \"red\">");
				out.println("아이디와 비밀번호를 비교하여 일치하지 않으면 아이디 또는 비밀번호를 다시 확인하세요.");
				out.println("등록되지 않은 아이디 거나 아이디 또는 비밀번호를 잘못 입력하셨습니다. <br/>");
				out.println("<a href = \"/memberservlet/user/login.html\">로그인</a> <br/>");
			}
		out.println("	</body>");
		out.println("</html>");
		
//		1. 데이터를 받는다. (아이디, 비밀번호)
		
//		2. 로직처리 : 1의 data를 select 
		
//		3. 응답 요청  아이디와 비밀번호를 비교하여 일치하지 않으면 아이디 또는 비밀번호를 다시 확인하세요. 등록되지 않은 아이디 거나 아이디 또는 비밀번호를 잘못 입력하셨습니다.
//			3-1. name != null : 홍길동님 안녕하세요.
//			3-2. name == null : 아이디와 비밀번호 둘중 하나가 틀렸다.
	}

}
