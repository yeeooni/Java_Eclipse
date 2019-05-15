<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    	<%
    		String root = request.getContextPath();
    	%>
<!DOCTYPE html>
<html>
<head>
<style>
	
	div{
	
		border : 10px groove magenta;
		padding : 5px;
		margin : 8px;
	}
	
	#aside_right{
		width : 25%;
		margin : 30px 0px; 
		border : 5px dotted black;
		float : right;
	
	}

</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body style = "margin : 0;">

	<div align = "center">
	
		<h3> jsp을 이용한 회원가입 &amp; 로그인 </h3>
	
		<div id = "aside_right">
			<a href = "<%=root %>/user/member.jsp"> 회원가입 </a><br/>
			<a href = "<%=root %>/user/login.jsp"> 로그인 </a>
		</div>
	
	</div>


</body>
</html>