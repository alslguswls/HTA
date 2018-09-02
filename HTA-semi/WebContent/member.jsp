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
	<table border="3" class="table table-striped" align="center" style="width: 800px;">
		<tr>
			<th>ID</th>
			<th>이메일</th>
			<th>전화번호</th>
			<th>가입일</th>
			<th>수정</th>
			<th>삭제</th>
		</tr>
		<c:forEach var="vo" items="${list }">
			<tr>
				<td>${vo.id }</td>
				<td>${vo.email }</td>
				<td>${vo.phone }</td>
				<td>${vo.regdate }</td>
				<td><a class="btn btn-primary btn-xs" href="layout.jsp?page=memberGetinfo.do?id=${vo.id }">수정</a></td>
				<td><a class="btn btn-danger btn-xs" href="memberDelete.do?id=${vo.id }">삭제</a></td>
			</tr>
		</c:forEach>
	</table>
	<div>
		<!-- 이전 -->
		<ul class="pagination">
			<c:choose>
				<c:when test="${startPage>10 }">
					<li><a href="memberList.do?pageNum=${startPage-1 }">[이전]</a> </li>
				</c:when>
				<c:otherwise>
					<li><span> 이전 </span></li>
				</c:otherwise>
			</c:choose>
		<!-- 페이지 숫자들 -->
		
			<c:forEach var="i" begin="${startPage }" end="${endPage }">
				<c:choose>
					<c:when test="${pageNum==i }">
						<%--현재페이지인경우 --%>
						<li class="active"><a href="memberList.do?pageNum=${i }">${i }</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="memberList.do?pageNum=${i }">${i }</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		<!-- 다음 -->
		<c:choose>
			<c:when test="${endPage<pageCount }">
				<li><a href="memberList.do?pageNum=${endPage+1 }">[다음]</a></li>
			</c:when>
			<c:otherwise>
				<li><span>다음</span></li>
			</c:otherwise>
		</c:choose>
		</ul>
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
					<input type="submit" class="btn btn-warning btn-xs" value="조회">
				</td>
			</tr>
			
		</form>
	</div>
</body>
</html>