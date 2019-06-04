<%@page import="com.kitri.dto.Product"%>
<%@page import="com.kitri.dto.OrderLine"%>
<%@page import="java.util.Date"%>
<%@page import="com.kitri.dto.OrderInfo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix ="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix ="fn" uri = "http://java.sun.com/jsp/jstl/functions" %>

    <style>
    	div.vieworder > table, div.wieworder > table th {
    		bor
    	}
    	
	    h3 {
	    	text-align: center;
	    }
    	td { 
    		text-align : center;
    	}
    	
    
    </style>
<div class= "vieworder">
	<h3> 주문 내역 보기 </h3>
	<table style = "width : 80%; margin-left: auto; margin-right: auto;">
	<tr>
		<th>주문번호</th><th>주문일자</th>
		<th>상품번호</th><th>상품명</th><th>가격</th><th>수량</th>
	</tr>
		<c:forEach var ="info" items = "${requestScope.orderlist }">
			<tr>
				<c:set var = "order_no" value = "${info.order_no}"/>
				<c:set var = "order_dt" value = "${info.order_dt }"/>
				<c:set var = "lines" value = "${info.lines}"/>
				<c:set var = "lineSize" value = "${fn:length(lines)}"/>
					<td rowspan = "${lineSize }"> ${order_no }</td>
					<td rowspan = "${lineSize }"> ${order_dt }</td>
					
					<c:forEach var = "line" items = "${lines }" varStatus = "obj">
						<c:set var = "p" value = "${line.product }"/>
							<c:if test = "${obj.index > 0 }">
								</tr><tr>
							</c:if>
							<td>${p.prod_no }</td><td>${p.prod_name }</td><td>${p.prod_price }</td><td>${line.order_quantity }</td>
					</c:forEach>
			</tr>	
		</c:forEach>
	</table>
</div>