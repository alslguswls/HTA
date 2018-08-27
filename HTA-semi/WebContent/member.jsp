<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>member.jsp</title>
</head>
<body align="center">
<h1>회원관리 페이지</h1>
	<table border="1" align="center" width="800">
			<tr>
				<td>id</td>
				<td>email</td>
				<td>phone</td>
				<td>수정</td>
				<td>삭제</td>
			</tr>
		<c:forEach var="vo" items="${list }">
			<tr>
				<td>${vo.id }</td>
				<td>${vo.email }</td>
				<td>${vo.phone }</td>
				<td><a href="memberUpdate.do?id=${vo.id }">수정</a></td>
				<td><a href="memberDelete.do?id=${vo.id }">삭제</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>