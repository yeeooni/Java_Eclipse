package com.kitri.basic;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/DBcounter")
public class DBcounter extends HttpServlet {
	private static final long serialVersionUID = 1L;

	int cnt;
	
    public void init() {
    	cnt = 0;
    	try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		System.out.println("DB드라이버 연결 성공!");
    }
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			Connection con = null;
			Statement stmt = null;
			ResultSet rs = null;
			
			try {
				con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.14.52:1521:orcl", "kitri", "kitri");
				String sql = "";
				sql += "select no \n";
				sql += "from counter";
				stmt = con.createStatement();
				rs = stmt.executeQuery(sql);
				rs.next();
				cnt = rs.getInt(1);
				
				sql += "udpate counter \n";
				sql += "set no = no + 1 \n";
				stmt.executeUpdate(sql);
				
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
			response.setContentType("text/html; charset = utf-8");
			PrintWriter out = response.getWriter();
			out.println("<html>");
			out.println("	<body>");
			out.println("당신은");
			
			String str = Integer.toString(cnt);
			int len = str.length();
			
//			if(int i = 0; i < zeroLen; i ++) {
//				out.println("<img width = 150 src = \"/basicservlet/img/0.png\">");
//			}
			
//			if(int i = 0; i < strLen; i++) {
//				out.println("<img width = 150 src = \"/basicservlet/img/" + str.charAt(i) + ".png\">");
//			}
			
			for(int i = 0; i < len; i ++) {
				char ch = str.charAt(i);
				int number = (ch - 48 );
				out.println("<img width = 150 src = \"/basicservlet/img/" + number + ".png\">");
				
			}

			out.println("번째 방문자 입니다.");
			out.println("</body>");
			out.println("</html>");
	}
}

