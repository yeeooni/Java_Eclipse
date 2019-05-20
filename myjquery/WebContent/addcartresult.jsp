<%@page import="java.util.Set"%>
<%@page import="com.kitri.dto.Product"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <script>
    	$(function(){
    		var arr = $("div.addcartresult > button");
    		$(arr[0]).click(function(){
    			console.log("success");
				alert("상품목록으로 가기를 클릭했습니다.");
				 //메뉴 중 상품목록 메뉴 찾기 
					$("nav > ul > li > a[href=productlist]").trigger("click");
				return false;
    			});
    	
    		$(arr[1]).click(function(){
    			alert("장바구니 보기를 클릭했습니다.");
    			alert($("nav > ul > li > a[href=viewcart]"));
  				$("nav > ul > li > a[href=viewcart]").trigger("click");
  			
    			return false;
        	});
    	});
    </script>
    
    
    
    <div class = "addcartresult" style = "position : absolute; top : 200px; left : 100px; width : 250px; border : 1px solid;">
		 장바구니 넣기 성공
		<button>상품 목록으로 가기</button>
		<button>장바구니 보기 </button>
	</div>