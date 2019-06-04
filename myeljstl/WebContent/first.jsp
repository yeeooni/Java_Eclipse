<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>first jsp</title>
</head>
<body>
	첫번째 jsp 입니다.
	<%int i; // scriptlet : _jspservice() 내부에 작성될 구문 
		i = 99;
	%>
	
	<%
		// experssion : _jspservice() 내부에 작성될 구문 
		// out.print()
	%>
	<%=i %>
	
	<%// declaration : _jspservice() 외부에 작성될 구문 %>
	<%! int i; %>
	<hr/>
	i = <%=i %>, this.i=<%=this.i %>
	<hr/>
	<h3>지시자(directive)</h3>
	<ul>
		<li> page directive : 속성 -contentType, import, errorPage, isErrorPage, buffer
		
			<%
				Date date = new Date();
				String patten = "yyyy-MM-dd";
				SimpleDateFormat sdf = new SimpleDateFormat(patten);
			%> 
		, 현재날짜	:<%=sdf.format(date)%>
		</li>
		
		<li> inclued directive  : 정적포함(.java 파일에 포함), 속성 -file </li>
		<li> taglib directive </li>
	</ul>
	<br/>
	<h3>ACTION TAG</h3>
	<ul>
		<li>STANDARD Action Tag
			<ol>
				<li>jsp:include : 동적포함(실행결과에 포함), 속성 -page</li>
				<li>jsp:forward</li>
				<li>jsp:param</li>
				<li>jsp:useBean</li>
				<li>jsp:setProperty</li>
				<li>jsp:getProperty</li>
			</ol>
		</li>	
			
		<li>CUSTOM Action Tag</li>
	</ul>
</body>
</html>