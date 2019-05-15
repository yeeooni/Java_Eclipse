<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file ="/templet/header.jsp" %>
<script type="text/javascript">
$(document).ready(function() {

});

	function login(){
		
		if(document.getElementById("id").value == ""){
			alert("아이디를 입력해주세요.");
		}else if(document.getElementById("pass").value == ""){
			alert("패스워드를 입력해주세요.");
		}else{
			document.getElementById("loginform").action = "<%=root%>/user/loginprocess.jsp";
			document.getElementById("loginform").submit();
		}
	
	}
	
	function mvjoin(){
		document.location.href ="/<%=root%>/user/member.jsp";
	}


</script>

<div class="container" align="center">
	<div class="col-lg-6" align="center">
		<h2>로그인</h2>
		<form id="loginform" method="post" action="">
			<div class="form-group" align="left">
				<label for="">아이디</label>
				<input type="text" class="form-control" id="id" name="id" placeholder="">
			</div>
			<div class="form-group" align="left">
				<label for="">비밀번호</label>
				<input type="password" class="form-control" id="pass" name="pass" placeholder="">
			</div>
			<div class="form-group" align="center">
				<button type="button" class="btn btn-warning" id="loginBtn" onclick = "javascript : login();">로그인</button>
				<button type="button" class="btn btn-primary" id="moveRegisterBtn" onclick = "javascript : mvjoin();" > 회원가입</button>
			</div>
		</form>
	</div>
</div>

<%@ include file ="/templet/footer.jsp" %>