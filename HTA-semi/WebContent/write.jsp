<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>공지사항 작성</h3>
<form action="notiinsert.do" name="insert" method="post">
제목:<input type="text" name="title"><br>
내용:<textarea rows="5" cols="50" name="content">

</textarea><br>
<input type="submit" value="등록">
</form>
</body>
</html>