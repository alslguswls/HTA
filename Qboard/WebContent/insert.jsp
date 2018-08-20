<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>게시글 작성</h1>
	<form action="insert.do" method="post">
	작성자 <br> <input type="text" name="writer"><br>
	제목  <br> <input type="text" name="title"><br>
	내용 <br>
	<textarea rows="5" cols="50" name="content"></textarea><br>
	비밀번호<input type="password" name="pwd"><br>
	<input type="submit" value="등록">
	</form>
</body>
</html>