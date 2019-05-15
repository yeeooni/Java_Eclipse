<%@page import="com.kitri.member.model.MemberDetailDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    
<%@ include file ="/templet/header.jsp" %>

<%
	MemberDetailDto memberDetailDto = (MemberDetailDto) request.getAttribute("userInfo");
%>

		<%=memberDetailDto.getName() %> 님 회원가입을 환영합니다. <br/>
		가입하신 정보는 아래와 같습니다. <br/>
		아이디 : <%=memberDetailDto.getId() %> <br/>
		이메일 : <%=memberDetailDto.getEmailid() %> <br/>
		전화번호 : <%=memberDetailDto.getTel1() %> - <%=memberDetailDto.getTel2() %> - <%=memberDetailDto.getTel3() %> <br/>
		주소 : <%=memberDetailDto.getAddress() %> <br/>
		로그인 후 모든 서비스를 이용할 수 있습니다. <br/>
		
		<a href = "<%=root%>/user/login.jsp/">로그인</a> <br/>

<%@ include file ="/templet/footer.jsp" %>