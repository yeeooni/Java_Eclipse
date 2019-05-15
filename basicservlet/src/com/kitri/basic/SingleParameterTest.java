package com.kitri.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/singleparam")
public class SingleParameterTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		1. 데이터 받아내라
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		int age = Integer.parseInt(request.getParameter("age"));
		
//		2. 로직처리
		String color = age == 10 ? "red" :"lightblue";
		
		
//		3. 리스폰스 페이지 
			response.setContentType("text/html; charset = utf-8"); 
			PrintWriter out = response.getWriter();
			
			out.println("<html>");
			out.println("<body>");
			out.println(name + "(<font color = \""+ color + "\">"+ id + "</font>)" + "님 안녕하세요.");
			out.println("</body>");
			out.println("</html>");
			
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		1. 데이터 받아내라 (내부적으로 소켓을 통해서 넘어온다.)
		request.setCharacterEncoding("utf-8"); //get에서는 안되고 post에서 가능하다.
		
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		int age = Integer.parseInt(request.getParameter("age"));
		
//		2. 로직처리
		String color = age == 10 ? "red" :"lightblue";
		
		
//		3. 리스폰스 페이지 
			response.setContentType("text/html; charset = utf-8"); 
			PrintWriter out = response.getWriter();
			
			out.println("<html>");
			out.println("<body>");
			out.println(name + "(<font color = \""+ color + "\">"+ id + "</font>)" + "님 안녕하세요.");
			out.println("</body>");
			out.println("</html>");
			
	}

}
