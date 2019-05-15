package com.kitri.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ggd")
public class Gugudan extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8;");
		PrintWriter out = response.getWriter();
		String white = "white";
		String color;

        out.println("<html>"); 
        
        out.println("	<body>"); 
//        out.println("	<head>"); 
//        out.println("	<style = \"text/css\">");
//        out.println("	td { ");
//        out.println("	 	font-weight : \"bold;\" ");
//        out.println("	 	font-size : \"100px;\" ");
//        out.println("	 }");
//        out.println("	</style>"); 
//        out.println("	</head>"); 
         
	        out.println("<h3> ***구구단*** </h3>"); 
   
        // 구구단 
	        out.println("<table border = 1 width = 800 height = 700>"); 
	        
	        
	        for(int i=2; i<=9; i++){ 
	        	
	        	color = i % 2 == 0 ? "red" : "lightblue";
	        	
	        	out.println("<tr id = \"td\" style = \"background-color : " + color + "\">"); 
	        
	        	for(int j = 1; j < 10; j++) {
		        	out.println("<td align=center style = 'font-weight : bold; font-size : 20px; color : " + white + ";'>"); 
		        	out.println(i + " * " + j + " = " + (i * j)); 
		            out.println("</td>"); 
	        	}
        	out.println("</tr>"); 
        }     		
        out.println("</table>");
        out.println("</body>");
        out.println("</html>"); 
	}
}
