package com.kitri.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/basic")
public class BasicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	String name;
	int age;

	
	//생성자 역할을 한다. 필드 값을 초기화 시킨다.
	@Override
	public void init() throws ServletException {
		name = "김의연";
		age = 10;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String color;
//		if(age > 18) {
//			color = "steelblue";
//		} else {
//			color = "red";
//		}
//		
		String color = age <= 18 ? "red" : "blue";
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("	<body>");
		out.println("<font color = " + color + " >" + name + "</font>(" + age + ") + 님 안녕하세요</font>");
		out.println("	</body>");
		out.println("</html>");
	}

}
