package com.kitri.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/multiparam")
public class MultiParameterTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		int age = Integer.parseInt(request.getParameter("age"));
		String fruit[] = request.getParameterValues("fruit");
//		톰캣의 버전이 낮을경우  한글 깨짐 오류 잡는 방법
//		1. euc-kr을 바이트로 쪼갠다.
//		2. 바이트로 쪼갠 것을 다시 utf-8로 합친다.
//		byte b[] = name.getBytes("iso-8859-1");
//		name = new String(b, "euc-kr");
		
		String color = age == 10 ? "red" :"lightblue";
		
		
//		3. 리스폰스 페이지 
			response.setContentType("text/html; charset = utf-8"); //첫번째 한글깨짐을 잡는방법. 
			PrintWriter out = response.getWriter();
			
			out.println("<html>");
			out.println("<body>");
			out.println(name + "(<font color = \""+ color + "\">"+ id + "</font>)" + "님 안녕하세요. <br/>");
			
			int size = fruit.length;
			
			if(size == 0) {
				out.println("그런 과일 없습니다.");
			} else {
				out.println("당신이 좋아하는 과일은");
				for(int i = 0; i < size; i++) {
					if(i == size - 1) {
						out.print(fruit[i]);
					}else {
						out.println(fruit[i] + ",");
					}
//					String result = fruit[i].toString();
//					System.out.println(result);
//					out.println()
				}
				out.println("입니다.");
			}
	
			out.println("</body>");
			out.println("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); //post일 때는 한글깨짐을 이렇게 잡으면 됨.
		doGet(request, response);
	}

}
