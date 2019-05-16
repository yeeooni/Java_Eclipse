<%@page import="com.sun.org.apache.bcel.internal.generic.NEW"%>
<%@page import="java.util.List"%>
<%@page import="com.kitri.dto.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
		Product product = (Product) request.getAttribute("prod_No");
		List<Product> list = (List<Product>) request.getAttribute("list");
	for (Product p : list) {
%>
<%	if(product.equals(p.getProd_no())) { %>
		<a href = "/productinforesult.jsp" >
			<img src ="images/<%=p.getProd_no()%>.jpg">
		</a>
		상세설명 : <%=p.getProd_detail()%>
<%} %>
		카테고리 :<%=p.getProductCategory().getCate_name()%> <br/>
		상품번호 :<%=p.getProd_no()%> <br/>
		상품명 : <%=p.getProd_name()%> <br/>
		가격 : <%=p.getProd_price()%> <br/>
		
		
<%
	}
%>


