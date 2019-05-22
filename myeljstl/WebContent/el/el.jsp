<%@page import="com.kitri.dto.Customer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>el</title>
</head>
<body>
	작업구분
	<hr/>
	<%=request.getParameter("opt") %> 작업을 선택했습니다. <br/>
	el 표기법 : ${param.opt} 작업을 선택했습니다. <br/>
		
	<%=Integer.parseInt(request.getParameter("a")) + 10 %> <br/>
	el 표기법 : ${param.a+10}  
	
	<%
		Customer c = new Customer("id1", "p1", "n1"); 
		request.setAttribute("c", c);	
	%>
	
고객이름 :<%=((Customer)request.getAttribute("c")).getName()%>
고객이름 : ${requestScope.c.name }
</body>
</html>