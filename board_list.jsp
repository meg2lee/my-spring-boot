<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.detail {border: solid grey 1px; width:300px;height: 200px}
</style>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>
	<h1>Welcome to meg's world</h1>
	<table>
		<tr><th>no</th><th>title</th><th>author</th><th>date</th></tr>
		<c:forEach var="b" items="${boardlist}">
			<tr>
				<td><a href="javascript:show_content(${b.num})">${b.num}</a></td>
				<td>${b.title}</td>
				<td>${b.author}</td>
				<td>${b.wdate}</td>
			</tr>
		</c:forEach>
	</table>
		<a href = "upload">leave your footprint</a>
	</div>
</body>
</html>