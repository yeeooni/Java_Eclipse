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

@WebServlet("/gbwriter")
public class GuestBookWrite extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 로딩 성공!!");
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Connection con = null;
		PreparedStatement pstmt = null;

		int cnt = 0;
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.14.52:1521:orcl", "kitri", "kitri");
			System.out.println("DB연결 성공!!");
			StringBuffer sql = new StringBuffer();
//		1.data get(이름, 제목, 내용)
			request.setCharacterEncoding("utf-8");
			String name = request.getParameter("name");
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");

//		System.out.println("내용입력받음.");
//		2. logic
			sql.append("insert all \n");
			sql.append("into guestbook (seq, name, subject, content, logtime) \n");
			sql.append("values ( guestbook_seq.nextval, ?, ?, ?, sysdate) \n");
			sql.append("select * from dual \n");

			System.out.println(sql);

			int idx = 0;
			pstmt = con.prepareStatement(sql.toString());

			pstmt.setString(++idx, name);
			pstmt.setString(++idx, subject);
			pstmt.setString(++idx, content);

			cnt = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
//		3. response
		response.setContentType("text/html;charset =utf-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("	<body>");
		if (cnt != 0) {
			out.println("글작성 성공.<br/>");
			out.println("<a href = \"/guestbookservlet/gblist\">글목록</a> <br/>");
		} else {
			out.println("글작성 실패");
			out.println("<a href = \"/guestbookservlet/index.html/\">처음으로</a> <br/>");
		}
		out.println("	</body>");
		out.println("</html>");
	}

}
