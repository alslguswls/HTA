<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<%
	int num = Integer.parseInt(request.getParameter("num"));
%>
<body>
	<form action="delete.do?" method="post">
	글번호<input type="text" name="num" value=<%=num %>>
	비밀번호<input type="password" name="pwd"><br>
	<input type="submit" value="삭제">
	</form>
</body>
</html>