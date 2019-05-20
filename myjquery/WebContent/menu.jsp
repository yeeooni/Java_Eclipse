<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
			<ul>
<%String id = (String) session.getAttribute("loginInfo");
	if(id == null){
%>
				<li><a href = "user/login.html"> 로그인 </a></li>
				<li><a href = "user/member.html"> 가입 </a></li>
<%} else{ %>
				<!-- 로그인이 성공된 사람이 보일 화면 -->  
				<li><a href = "logout"> 로그아웃 </a></li>
<% } %>
				<li><a href ="productlist">상품 목록</a></li>
				<li><a href ="viewcart">장바구니 보기</a></li>
				<li><a href ="vieworder">주문내역 보기</a></li>
			</ul>