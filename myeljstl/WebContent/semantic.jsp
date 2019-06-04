<%@page contentType="text/html; charset=utf-8" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
	<script type="text/javascript">
		$(function(){
			// Dom 트리에서 nav > ul > li > a 객체들 찾기
		
			var arr = $("nav > ul > li > a");
				$(arr).click(function(){
					alert("success!");
					var vUrl = $(this).attr("href");
						if(vUrl == 'logout'){
							$.ajax({
								url : vUrl,
								method : 'get',
								success : function(result){
									alert(result.trim());
									location.href = "semantic.jsp";
								}
							});
						} else{
							alert("success = " + vUrl);
							$.ajax({
								url : vUrl,
								method : 'get',
								success : function(result){
									$("section").html(result);	
								}
							});
							return false;				
						}
					});
				});
	
	</script>
<title>semantic</title>
	<style>
		header{
			background-image: url("images/logo.jpg");
		}
		nav > ul {
			list-style: none;
			padding-left: 0;

		}
		
		nav > ul > li{
			display: inline-block;
		}
		
		footer{
			position: fixed;
			bottom: 0;
			width : 100%;
			background-color: magenta;
		}
	</style>

</head>
<body>
	<header> <h1>header</h1></header>
	<nav>메뉴
		<jsp:include page="menu.jsp"></jsp:include>
	</nav>
	<section>본문</section>
	<footer>서울시 구로구 디지털로 34길 코오롱 싸이언스밸리1차 4층 TEL 02-869-6052, Copyright 2013 KITRI, ALL RIGHTS RESERVED</footer>
</body>
</html>