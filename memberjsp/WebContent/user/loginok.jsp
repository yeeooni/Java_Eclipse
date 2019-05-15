<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file ="/templet/header.jsp" %>

<%
	String name = request.getParameter("name");
%>

		<%=name %>님 안녕하세요!  <br/>
			로그인 후 모든 서비스를 이용할 수 있습니다.

<%@ include file ="/templet/footer.jsp" %>