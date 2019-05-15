package com.kitri.guestbook;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/gblist")
public class GuestBookList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		GuestBookList gb = new GuestBookList();
//		gb.guestSearch(seq);
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		
	}

//	public GuestbookDto guestSearch(int seq) {
//		GuestbookDto guestbookDto = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//			con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.14.52:1521:orcl", "kitri", "kitri");
//			StringBuffer sql = new StringBuffer();
//			
//			sql.append("select * \n");
//			sql.append("from guestbook \n");
//			
//			pstmt = con.prepareStatement(sql.toString());
//
//			rs = pstmt.executeQuery();
//			
//				if(rs.next()) {
//					guestbookDto = new GuestbookDto();
//					guestbookDto.setName(rs.getString("name"));
//					guestbookDto.setLogtime(rs.getInt(("logtime")));
//					guestbookDto.setSeq(rs.getInt("seq"));
//					guestbookDto.setSubject(rs.getString("subject"));
//					guestbookDto.setContent(rs.getString("content"));
//					
//				}
//			
//		} catch (SQLException e) {
//			
//			e.printStackTrace();
//		} finally {
//				try {
//					if(rs != null)
//					rs.close();
//					
//					if(pstmt != null)
//						pstmt.close();
//					
//					if(con != null)
//						con.close();
//				} catch (SQLException e) {
//					
//					e.printStackTrace();
//				}
//		
//		}
//		return guestbookDto;
//	}
//     
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		1.data get

		System.out.println("들어옴");
		
		response.setContentType("text/html; charset = utf-8"); 
		PrintWriter out = response.getWriter();
		
		out.println("<!DOCTYPE html>");
		out.println("<html lang=\"ko\"> \n");
		out.println("<head>");
		out.println("<title>글목록</title>");
		out.println("<meta charset=\"utf-8\"> \n");
		out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
		out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css\">");
		out.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"> </script> ");
		out.println("<script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js\"></script>");
		out.println("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js\"></script>");
		out.println("<script type=\"text/javascript\">");
			

		out.println("</script>");
		out.println("</head>");
		out.println("<body> \n");
		out.println("<div class=\"container\" align=\"center\"> \n");
		out.println(" <div class=\"col-lg-8\" align=\"center\"> \n");
		out.println("<h2>글목록</h2> \n");
		out.println("<table class=\"table table-borderless\"> \n");
		out.println("<tr> \n");
		out.println("<td align=\"right\"><button type=\"button\" class=\"btn btn-link\">글쓰기</button></td> \n");
		out.println("</tr> \n");
		out.println("</table> \n");
//		2.logic
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.14.52:1521:orcl", "kitri", "kitri");
			StringBuffer sql = new StringBuffer();
			
			sql.append("select seq, name, subject, content, logtime \n");
			sql.append("from guestbook \n");
			sql.append("order by seq desc \n");
			
			pstmt = con.prepareStatement(sql.toString());

			rs = pstmt.executeQuery();
			
				while(rs.next()) {
					
					out.println("<table class=\"table table-active\"> \n");
					out.println("<tbody> \n");
					out.println("<tr \n>");
					out.println("<td>작성자 :" + rs.getString("name") + "</td> \n");
					out.println("<td style=\"text-align: right;\">" + rs.getString(("logtime")) + "</td> \n");
					out.println("</tr> \n");
					out.println("<tr>");
					out.println("<td colspan=\"2\"><strong>" + rs.getString("seq") + "." + rs.getString("subject") + " </strong></td \n>");
					out.println(" </tr \n>");
					out.println("<tr \n>");
					out.println("<td colspan=\"2\">" + rs.getString("content").replace("", "<br/>") + "</td \n>");
					out.println("</tr \n>");
					out.println("</tbody \n>");
					out.println("</table \n>");	
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
		
//		3.응답
		out.println("</div \n>");
		out.println("</div \n>");
		out.println("</html \n>");
	
	}

}
