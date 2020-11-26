<%@page import="java.util.Date"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>Kindly leave your sweet messages to meg:)</h3>
<form action="upload" method="post" enctype="multipart/form-data">
	Written by: <input type="text" name="author" value="smith"><br>
	Text: <input type="text" name="contents" style="text-align:center; width:200px; height:50px; letter-spacing: -5px"><br>
	File: <input type="file" name="files" multiple="multiple">
	<button type="submit">업로드</button>
	
</form>
</body>
</html>
