<%@page import="com.kitri.dto.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c"	 uri = "http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
	
<%--
	Product p = (Product) request.getAttribute("product");
	String no = p.getProd_no();
--%>
<c:set var = "p" value = "${requestScope.product}"/>
<%-- 리퀘스트 값 : ${p } --%>
<c:set var ="no" value = "${p.prod_no}"/>
							
<style>
@media screen and (max-width: 300px) div.product_view_detail {
}

div.product_view {
	width: 100%;
}

div.product_view_pic {
	width: 50%;
	float: left;
	position: relative;
}

img {
	vertical-align: top;
	width: 50%;
	max-width: 100%;
}

div.product_view_detail {
	width: 40%;
	/* width: 610px; */
	float: left;
	position: relative;
}

div.product_view_detail h4 {
	font-size: 24px;
	font-weight: bold;
	color: #222;
	border-bottom: 2px solid #333;
	padding-bottom: 18px;
	margin-bottom: 20px;
}

div.product_view_detail p.t1 {
	font-size: 16px;
	color: #444;
	margin-bottom: 20px;
	line-height: 1.6;
	min-height: 40px;
	vertical-align: baseline;
	background: transparent;
}

div.product_info_content {
	width: 100%;
	margin: 20px 0;
	border-top: 1px solid #ddd;
}

div.product_info_content ul {
	margin: 0;
	padding: 0;
	width: 80%;
	/* width: 100%; */
	float: left;
	list-style: none;
}

div.product_info_content ul>li {
	width: 100%;
	margin: 0;
	padding: 0;
	border: 0;
	vertical-align: baseline;
	background: transparent;
}

div.product_info_content ul>li>button {
	width: 100%;
}

div.product_info_content dl {
	width: 100%;
}

div.product_info_content dl dt {
	float: left;
	width: 40%;
}

div.product_info_content li.submit dl dt {
	float: left;
	width: 100%;
}

div.product_info_content dl dd {
	float: right;
	width: 45%;
	text-align: right;
}

div.product_info_content dl:after {
	content: "";
	display: block;
	clear: both;
}

div.result {
	display: none;
}
</style>


<script>
// 장바구니 담기 창을 띄웁니다.
$(function(){
	var $bt = $(".submit dl > dt > button");
	$bt.click(function(){
		$.ajax({
		  url: 'addcart',
		  method: 'get',
		  data: 'no=${no}&quantity=' + $("input[name=quantity]").val(),
		  success : function(result) {
				console.log("success!");
				//$("div[class='product_list']").html(result.trim());
					$("div.addcartresult").remove();
					$("section").append(result.trim());		
				},
			error : function(){
				console.log("error!!!!!!");
			}
		});
	return false;
	});
});
</script>

<div class="product_view">
	<div class="product_view_pic">
		<img src="images/${no}.jpg">
	</div>
	<div class="product_view_detail">
		<h4>${p.prod_name}</h4>
		<p class="t1">
			<%-- <%=p.getProd_detail()==null?"":p.getProd_detail() %> --%>
			신선하게 브루드(Brewed)되어 원두의 다양함이 살아있는 커피
		</p>
		<div class="product_info_content">
			<input type ="hidden" name = "no" value ="${no}">
			<ul>
				<li class="no">
					<dl>
						<dt>상품번호</dt>
						<dd>${no}</dd>
					</dl>
				</li>
				<li class="name">
					<dl>
						<dt>가격</dt>
						<dd>
						
						<fmt:formatNumber value="${p.prod_price }"  type ="currency" pattern = "#,###"/></dd>
						
					</dl>
				</li>
				<li class="quantity">
					<dl>
						<dt>수량</dt>
						<dd>
							<input type="number" name="quantity" value ="1" min="1" max ="99">
						</dd>
					</dl>
				</li>
				<li class="submit">
					<dl>
						<dt>
							<button>장바구니넣기</button>
						</dt>
					</dl>
				</li>
			</ul>
		</div>
	</div>
	<div class="result">
		<button>계속쇼핑하기</button>
		<button>장바구니보기</button>
	</div>
</div>
