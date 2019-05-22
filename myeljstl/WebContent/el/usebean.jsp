<%@page import="com.kitri.dto.Customer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>UseBean</title>
</head>
<body>
<%--
	// request의 속성 (이름 : "c", 타입 : com.kitri.dto.Customer 얻기)
	Customer c = (Customer) request.getAttribute("c");

	// 2.1 속성이 null인 경우 Customer 객체 생성하여 c 참조변수 대입 
	// c 참조변수를 requset의 속성 (이름 : c)로 추가 
	if(c == null){
		c = new Customer();
		request.setAttribute("c", c);			
	}
--%>
<jsp:useBean id= "c" class = "com.kitri.dto.Customer" scope = "request" />

<%-- 
	c.setId("id1");
--%>
<jsp:setProperty  name="c" property="id" value = "id1"/>

<%-- 
	c.setName(request.getParameter("n"));
--%>
<jsp:setProperty name="c" property="name" value ="n"/>

<%--=c.getId()--%>
<jsp:getProperty name="c" property="id"/>



</body>
</html>