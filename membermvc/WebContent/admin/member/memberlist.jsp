<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/templet/header.jsp"%>

<script type="text/javascript">
	$(document).ready(function() {
		memberList('','');
		//console.log('온로드');
		$("#searchBtn").click(function() {
			//console.log('버튼눌려진다');
			var key = $("#key").val();
			var word = $("#word").val();
			$("#word").val(''); 
			memberList(key, word);			
		});
		
	});
	
	function memberList(key, word){
		$("#mlist").empty();
	
		$.ajax({
			url:"<%=root%>/admin",
			type : "get",
			dataType : "xml",
			data : "act=getmemberlist&key=" + key + "&word=" + word,
			timeout : 30000,
			cache : false,
			success : function(xml) { // 성공  
				console.log(xml);
				var member = $(xml).find("member");

				for (var i = 0; i < member.length; i++) {

					var id = $(member[i]).find("id").text();
					var name = $(member[i]).find("name").text();
					var email = $(member[i]).find("email").text();
					var tel = $(member[i]).find("tel").text();
					var address = $(member[i]).find("address").text();
					var joindate = $(member[i]).find("joindate").text();

					var tr = $("<tr>").attr("class", "table-active");
					var td1 = $("<td>").html(id);
					var td2 = $("<td>").html(name);
					var td3 = $("<td>").html(email);
					var td4 = $("<td>").html(tel);
					var td5 = $("<td>").html(address);
					var td6 = $("<td>").html(joindate);

					tr.append(td1).append(td2).append(td3).append(td4).append(
							td5).append(td6);
					$("#mlist").append(tr);
				}
			},
			error : function(xhr, textStatus, errorThrown) { // 실패
				console.log("실패");
				$("#result").html(
						"xhr : " + xhr + "textStatus :" + textStatus
								+ "errorThrown : " + errorThrown);
			}
		});
	}
</script>

<div class="table-responsive-lg">
	<h2>회원목록</h2>
	<table class="table">
		<tr>
			<td>
				<form class="form-inline" action="">
					<select class="form-control" id="key" name="key">
						<option value="id">아이디</option>
						<option value="tel3">전화번호뒷자리</option>
						<option value="address">주소</option>
					</select> <input type="text" class="form-control" id="word" name="word">
					<button type="button" class="btn btn-primary" id="searchBtn">검색</button>
				</form>
			</td>
		</tr>
	</table>
	<table class="table">
		<thead>
			<tr>
				<th>아이디</th>
				<th>이름</th>
				<th>이메일</th>
				<th>전화번호</th>
				<th>주소</th>
				<th>가입일</th>
			</tr>
		</thead>
		<tbody id="mlist"></tbody>
	</table>
</div>

<%@ include file="/templet/footer.jsp"%>

