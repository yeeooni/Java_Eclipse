<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file ="/templet/header.jsp" %>

<%
	String name = request.getParameter("name");
%>

		<%=name %> 님 회원가입을 환영합니다. <br/>
		<a href = "<%=root%>/user/login.jsp/">로그인</a> <br/>

<%@ include file ="/templet/footer.jsp" %>