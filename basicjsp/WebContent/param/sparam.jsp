<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
   <%
   	request.setCharacterEncoding("utf-8");
   	String name = request.getParameter("name");
   	String id = request.getParameter("id");
   	int age = Integer.parseInt(request.getParameter("age"));
   	String color = age == 10 ? "red" :"lightblue";
   %>
   
   
   
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>Single Jsp</title>
	</head>
		<body>
			
			<%=name %><font color = "<%= color %>" > <%= id%> </font> 님 안녕하세요.

		</body>
</html>