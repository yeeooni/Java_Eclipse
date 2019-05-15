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
	
	<%// experssion : _jspservice() 내부에 작성될 구문 
		// out.print()
	%>
	<%=i %>
	
	<%// declaration : _jspservice() 외부에 작성될 구문 %>
	<%! int i; %>
	<hr/>
	
	i = <%=i %>, this.i=<%=this.i %>
</body>
</html>