<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!	
	int total = 0;
	int sum = 0;
%>

<%
	int value = Integer.parseInt(request.getParameter("star"));
	sum += value;
	total++;
%>

참여자수 : <%=total%> <!-- 클릭을 한 횟수() -->
총 별점 : <%=sum%><!-- 별점의 총합 --> 