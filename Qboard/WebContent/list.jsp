<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>list.jsp</title>
</head>
<body>
<h2>게시판</h2>
<table border="1" width="500">
	<tr>
		<th>글번호</th>
		<th>작성자</th>
		<th>글제목</th>
		<th>삭제</th>
	</tr>
	<c:forEach var="vo" items="${list }">
	<tr>
		<td>${vo.num }</td>
		<td>${vo.writer }</td>
		<td>
		<a href="detail.do?num=${vo.num }">${vo.title }</a>
		</td>
		<td>
		<a href="delete.jsp?num=${vo.num }">삭제</a>
		</td>
	</tr>
	</c:forEach>
</table>
<form action="list.do" method="post">
	<select name="search">
		<option value="writer">작성자</option>
		<option value="title">제목</option>
		<option value="content">내용</option>
	</select>
	<input type="text" name="keyword">
	<input type="submit" value="검색">
</form>
<div>
	<c:choose>
		<c:when test="${pageNum>4}">
			<a href="list.do?pageNum=${startPage-1 }">[이전]</a>
		</c:when>
		<c:otherwise>
			[이전]
		</c:otherwise>
	</c:choose>
	<c:forEach var="i" begin="${startPage }" end="${endPage }">
	<c:choose>
		<c:when test="${pageNum==i }">
			<a href="list.do?pageNum=${i }"><span style="color:red" >[${i }]</span></a>
		</c:when>
		<c:otherwise>
			<a href="list.do?pageNum=${i }"><span style="color:#555" >[${i }]</span></a>
		</c:otherwise>
	</c:choose>
	</c:forEach>
	<c:choose>
		<c:when test="${endPage<pageCount}">
			<a href="list.do?pageNum=${endPage+1 }">[다음]</a>
		</c:when>
		<c:otherwise>
			[다음]
		</c:otherwise>
	</c:choose>
</div>
</body>
</html>