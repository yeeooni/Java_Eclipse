<%@page import="java.io.FileInputStream"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page buffer = "100kb" %>
<%@page errorPage = "err.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>pagedirective</title>
</head>
<body>

	<% // 1~ 10000까지 출력
		for(int i = 1; i <=10000; i++){
	%><%= i%>
	<%} %>
	<%//a.txt 파일을 byte단위로 읽기 위한 스트림 객체 생성
		FileInputStream fis = new FileInputStream("a.txt");
	%>

</body>
</html>