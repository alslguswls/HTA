<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- 
2018-08-30	검색기능 추가(윤우현)
 -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>member.jsp</title>
<style>table {margin:auto;text-align:center }</style>
</head>
<body align="center">
<br>
<h1>회원관리 페이지</h1>
	<table border="1" align="center" width="800">
		<tr>
			<th>id</th>
			<th>email</th>
			<th>phone</th>
			<th>수정</th>
			<th>삭제</th>
		</tr>
		<c:forEach var="vo" items="${list }">
			<tr>
				<td>${vo.id }</td>
				<td>${vo.email }</td>
				<td>${vo.phone }</td>
				<td><a href="layout.jsp?page=memberGetinfo.do?id=${vo.id }">수정</a></td>
				<td><a href="memberDelete.do?id=${vo.id }">삭제</a></td>
			</tr>
		</c:forEach>
	</table>
	<div>
		<!-- 이전 -->
		<c:choose>
			<c:when test="${startPage>10 }">
				<a href="memberList.do?pageNum=${startPage-1 }">[이전]</a>
			</c:when>
			<c:otherwise>
				[이전]
			</c:otherwise>
		</c:choose>
		<c:forEach var="i" begin="${startPage }" end="${endPage }">
			<c:choose>
				<c:when test="${pageNum==i }">
					<%--현재페이지인경우 --%>
					<a href="memberList.do?pageNum=${i }"><span style="color: red">[${i }]</span></a>
				</c:when>
				<c:otherwise>
					<a href="memberList.do?pageNum=${i }"><span style="color: #555">[${i }]</span></a>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<!-- 다음 -->
		<c:choose>
			<c:when test="${endPage<pageCount }">
				<a href="memberList.do?pageNum=${endPage+1 }">[다음]</a>
			</c:when>
			<c:otherwise>
				[다음]
			</c:otherwise>
		</c:choose>
	</div>
	
	<!-- 회원 검색 기능 -->
	<div>
		<form method="post" action="memberSearch.do" >
			<tr>
				<td>
					<select name="searchSel">
						<option value="id"> Id </option>
						<option value="email"> Email </option>
					</select>
					<input type="text" name="searchText" >
					<input type="submit" value="조회">
				</td>
			</tr>
			
		</form>
	</div>
</body>
</html>