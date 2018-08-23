<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body align="center">
<h1>회원관리 페이지</h1>
<form name="f">
	<table border="1" align="center" width="800">
		<tr>
			<td>id</td>
			<td>email</td>
			<td>phone</td>
			<td>수정</td>
			<td>삭제</td>
		</tr>
		<c:forEach var="vo" items="${list }">
			
		</c:forEach>
		
	</table>
</form>
</body>
</html>