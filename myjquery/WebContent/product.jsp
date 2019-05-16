<%@page import="com.sun.org.apache.bcel.internal.generic.NEW"%>
<%@page import="java.util.List"%>
<%@page import="com.kitri.dto.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
<head>
	<style>
		img{
		
		}
			#div{
		
			}
	</style>
</head>

<%
		Product product = (Product) request.getAttribute("prod_No");
		List<Product> list = (List<Product>) request.getAttribute("list");
	for (Product p : list) {
%>
<body>
	<div>
		<a herf = "#" >
			<img src ="images/<%=p.getProd_no()%>.jpg">
		</a>
		카테고리 :<%=p.getProductCategory().getCate_name()%> <br/>
		상품번호 :<%=p.getProd_no()%> <br/>
		상품명 : <%=p.getProd_name()%> <br/>
		가격 : <%=p.getProd_price()%> <br/>
	</div>
<%
	}
%>
	<section>
		이건 상세 내역 
	</section>


</body>



</html>
