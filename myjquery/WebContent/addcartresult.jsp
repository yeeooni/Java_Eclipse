
<%@page import="com.kitri.dto.Product"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	Map<Product, Integer> map = (Map) request.getAttribute("rcart"); 
    %>

    <script>
    	$(function(){
    		var arr = $("div.addcart > button");
    		$(arr[0]).click(function(){
				alert("상품목록으로 가기를 클릭했습니다.");
				 //메뉴 중 상품목록 메뉴 찾기 
					location.href = "/myjquery/productList";
    			});
    	
    		$(arr[1]).click(function(){
    			alert("장바구니 보기를 클릭했습니다.");
			$.ajax({
				method : 'get',
    			success : function(result){
    				$("div").append(result);
    			}
				});
        	});
    	});
		
    
    </script>
    
    
    
    <div class = "addcart" style = "position : absolute; top : 200px; left : 100px; width : 250px; border : 1px solid;">
		 장바구니 넣기 성공
		<button>상품 목록으로 가기</button>
		<button>장바구니 보기 </button>
	</div>