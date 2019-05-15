package com.kitri.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/counter")
public class Counter extends HttpServlet {
	private static final long serialVersionUID = 1L;

	int cnt;
//	int totalLen;

		public void init() {
			cnt = 0;
//			totalLen = 8;
		}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		cnt++;

//		String cntStr = cnt + ""; //cnt를 문자열로 변환
//		int cntLen = cntStr.length(); // cnt의 문자길이를 구해옴
//		int zeroLen = totalLen - cntLen; // 
		
		response.setContentType("text/html; charset = utf-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("	<body>");
		out.println("당신은");
		
		String str = Integer.toString(cnt);
		int len = str.length();	
		
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
