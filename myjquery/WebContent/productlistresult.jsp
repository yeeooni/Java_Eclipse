<%@page import="com.kitri.dto.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<style>
div.product_list ul > li.menuDataSet{
    float: left;
    position: relative;
    margin: 5px;
}
div.product_list ul{
  list-style: none;
}
</style>
<script>
//제품 상세정보를 띄웁니다.
$(function(){
  var $aObj = $("div.product_list>ul>li.menuDataSet dl>dt>a");
  
  $aObj.click(function(){
	var url = 'productInfo';
	var $dt = $(this).parent();	
	var $span = $dt.siblings("dd.no").find("span");
	var $no = $span.html().trim();//상품번호
	
	$.ajax({
		url:url,
		method:'get',
		data:'no='+$no,
		success:function(result){
			$("div[class='product_list']").html(result.trim());
		}
	});
	return false; 
  });
});
</script>    
<div class="product_list">
<ul>
<% List<Product> list = (List)request.getAttribute("list");

   for(Product p: list){    
%>
 
    <li class="menuDataSet">
      <dl>
        <dt>
          <a href="#" >          
            <img src="images/<%=p.getProd_no()%>.jpg" alt="<%=p.getProd_name()%>이미지입니다">
          </a>
        </dt>
        <dd class="category">카테고리:<span><%=p.getProductCategory().getCate_name() %></span></dd>
        <dd class="no">상품번호:<span><%=p.getProd_no() %></span></dd>
        <dd class="name">상품명:<span><%=p.getProd_name() %></span></dd>
        <dd class="price">가격:<span><%=p.getProd_price() %></span></dd>
    
      </dl> 
    </li>

<%}//end for%>
  </ul>    

</div>
