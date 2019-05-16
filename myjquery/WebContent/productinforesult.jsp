<%@page import="com.kitri.dto.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%--     <%Product p = (Product) request.getAttribute("prod_No");%> --%>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>

<meta charset="UTF-8">
<title>product detail</title>

<script type="text/javascript">
	$(function(){
		var $obj = $("a");
		$obj.click(function(){
		$.ajax({
			method : 'get',
			url : '/myjquery/productInfoServlet',
			data : $obj.attr("href"),
			success : function(result){
				$("section").html(result);
			}
		});
	});
});
</script>
</head>
<body>

<section>
	상세내역
</section>
	<div>
		상품목록
	</div>
</body>
</html>