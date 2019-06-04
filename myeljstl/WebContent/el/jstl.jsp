<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import = "java.util.Map" %>
<%@page import = "java.util.HashMap" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jstl</title>
</head>
<body>
	<%--int num = 99; --%><%-- int num = Integer.parseInt(request.getParameta("num")); --%>
	<c:set var = "num" value = "${param.num }"></c:set>
	요청 전달 데이터 값 : ${num}
	
	<%-- if(){} --%>
	<c:if test ="${num % 2 == 0}">짝수입니다.<br/></c:if>
<hr/>
	<c:choose>
		<c:when test="${num % 2 == 0}">짝수입니다.</c:when>
		<c:otherwise>홀수입니다.</c:otherwise>
	</c:choose>
	
	<%-- for(int i = 1; i <=10; i++) --%>
	<c:forEach begin ="1" end ="10" step ="1" var = "i">${i}</c:forEach>
	
	<c:set var = "total" value ="0"/>
		<c:forEach begin = "1" end ="10" step ="1" var = "i">
			<c:set var = "total" value = "${total + i }"/>
		</c:forEach>
			1 ~ 10의 반복 합 : ${total}
<hr/>	
	<%
		List<String> list = new ArrayList<>();
		list.add("one");
		list.add("two");
		list.add("three");
		request.setAttribute("list", list);
	%>
	
	<%--for(String e : request.getAttribute("list"))--%>
	<c:forEach var = "e" items="${requestScope.list }">
		${e } <br/>
	</c:forEach>
<hr/>
	<c:forEach var ="e" varStatus = "obj" items="${requestScope.list }">
		${obj.index} - ${e } : ${obj.count}회 <br/>
	</c:forEach>
	
<%
	Map<String, Integer> map = new HashMap<>();
	map.put("one", 1);
	map.put("two", 2);
	map.put("three", 3);
	request.setAttribute("map", map);

%>
	<c:forEach var = "e1" items = "${requestScope.map}">
		${e1.key} : ${e1.value}
	</c:forEach>
	
</body>
</html>