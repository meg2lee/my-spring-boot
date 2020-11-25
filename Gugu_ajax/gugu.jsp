<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	div {border: 1px black solid; width: 200px; height: 300px}
</style>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script>

	function ajax_req(dan) { /* parameter로 받은 dan */
				
		$.ajax({ 
		url:'ajax_req', 
		type :'post',
		data: {'dan': dan},
		dataType:'html',
		success:function gugudan(res){
			$('div').html(res);
		}, 
		error:function(xhr, status, err){
			alert(status+', '+err);
		}
	
	    });
		
	}
</script>
</head>
<body>
	<form action = "ajax_req" method="post">
		<a href = "javascript:ajax_req(2)" >2</a>
		<a href = "javascript:ajax_req(3)" >3</a>
		<a href = "javascript:ajax_req(4)" >4</a>
		<a href = "javascript:ajax_req(5)" >5</a>
		<a href = "javascript:ajax_req(6)" >6</a>
		<a href = "javascript:ajax_req(7)" >7</a>
		<a href = "javascript:ajax_req(8)" >8</a>
		<a href = "javascript:ajax_req(9)" >9</a>		
	</form>
	<div>${data}</div>
</body>
</html>