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
<div style="text-align:center">
	<h2>로그아웃 되었습니다.</h2>	
	<a href="layout.jsp">메인으로..</a>
</div>
</body>
</html>