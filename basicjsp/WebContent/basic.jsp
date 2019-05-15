<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
   <!-- 선언부  -->
   <%! 
	    String name;
	   	int age;
	   	
	   	public void init(){
	   		name = "김의연";
	   		age = 31;
	   	}
   %>
   
   <!-- 처리부  -->
   <%
   		String color = age >= 18 ? "red" : "blue";
   %>
	   
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>basic jsp</title>
	</head>

		<body>
			<div align = "center"> 
			<!-- 출력부  -->
				<% out.print(name); %> (<font color ="<% out.print(color); %>"> <% out.print(age); %> </font>) 안녕하세요. <br/>
				<%= name %> (<font color = "<%=color%>"> <%= age %> </font>) 안녕하세요.
			</div>
	 
		</body>
</html>