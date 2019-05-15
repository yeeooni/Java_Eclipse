<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%! 
    
	    int cnt;
	    int number;
	    char ch;
	    
	     public void init(){
	    	cnt = 0;
	    	
	    } 
	    
    %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>counter jsp</title>
</head>
<body>
	<div align = "center">
	당신은 <% String str = Integer.toString(cnt++);%>
		<% int len = str.length(); %>	
		
		<% 
			for(int i = 0; i < len; i ++) {
			ch = str.charAt(i);
			number = (ch - 48 ); 
		%>
	
		<img width = 150 src = "/basicjsp/img/<%= number %>.png">
			
		<% } %> 번째 방문자 입니다.
	</div>
</body>
</html>