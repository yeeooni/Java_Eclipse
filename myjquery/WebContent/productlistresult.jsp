<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script>

</script>

<script type="text/javascript">

	$(function() {
		$.ajax({
			method : 'get',
			url : "/myjquery/productInfo",
			success : function(result) {
				$("div").html(result.trim());
			}
		});
	});
	
	
</script>

</head>
<body>
	<div>
	
	</div>
</body>
</html>