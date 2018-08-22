<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>logout.jsp</title>
</head>
<body aling="center">
<%
	session.invalidate(); // 세션 비우기
%>
<h1>로그아웃 되었습니다.</h1>	
<a href="main.jsp">메인으로..</a>
</body>
</html>