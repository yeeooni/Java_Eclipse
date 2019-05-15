<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "com.kitri.member.model.MemberDto, com.kitri.util.MoveUrl"%>
    
<%@ include file ="/templet/header.jsp" %>

<%
	MemberDto memberDto = (MemberDto) session.getAttribute("userInfo");
		if(memberDto != null) {
%>
	
	<script type ="text/javascript">
	function deleteMember(){
		if(confirm("정말 탈퇴하시겠습니까?")){
			document.location.href ="<%=root%>/user?act=deletemember";
		}
	}
	</script>
		
		<%=memberDto.getName() %>님 안녕하세요! 	<%=memberDto.getId()%>  <br/>
			<a href ="<%=root%>/user?act=logout">로그아웃</a>
			<a href ="<%=root%>/user?act=mvmodify">정보수정</a>
			<a href ="#" onclick = "javascript:deleteMember();">회원탈퇴</a>
		<%
			if("kimi".equals(memberDto.getId())){
		%>	
		<a href ="<%=root%>/admin?act=memberlist">관리자</a>
		<%} 
				
		} else { 
			MoveUrl.redirect(request, response, "/user?act=mvlogin");	
		}
		%>
		
			

<%@ include file ="/templet/footer.jsp" %>