<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("utf-8");%>

<% 
	String id = request.getParameter("id"); 
	String name = request.getParameter("name");
%>

	<%
	/*10초*/
		//Thread.sleep(10 * 1000);
	/*1초*/
		Thread.sleep(1 * 1000);
	%>
	<%=id %>(<%=name %>)님 반갑습니다.
