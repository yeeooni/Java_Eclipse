<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
    <%
    	String name = request.getParameter("name");
    	String id = request.getParameter("id");
    	int age = Integer.parseInt(request.getParameter("age"));
    	String fruit[] = request.getParameterValues("fruit");
    	String color = age == 10 ? "red" :"lightblue";
    %>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

		
	<%=name %> (<font color = "<%=color %>"> <%=id %> </font>)님 안녕하세요. <br/>
			
			<%
				int size = fruit.length;
			%>
			
		<% 
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
				}
				out.println("입니다.");
			}
		%>
		
</body>
</html>