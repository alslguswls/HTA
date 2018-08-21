<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2>게시글목록</h2>

<!-- 페이징 -->
<div>
	<!-- 이전 -->
	<c:choose>
		<c:when test="${startPage>10 }">
			<a href="list.do?pageNum=${startPage-1 }">[이전]</a>
		</c:when>
		<c:otherwise>
			[이전]
		</c:otherwise>
	</c:choose>
	
	<c:forEach var="i" begin="${startPage }" end="${endPage }">
	<c:choose>
		<c:when test="${pageNum==i }"><%-- 현재페이지인경우--%>
			<a href="list.do?pageNum=${i }"><span style="color:red" >[${i }]</span></a>
		</c:when>
		<c:otherwise>
			<a href="list.do?pageNum=${i }"><span style="color:#555" >[${i }]</span></a>
		</c:otherwise>
	</c:choose>	
	</c:forEach>
	
	
	<!-- 다음 -->
	<c:choose>
		<c:when test="${endPage<pageCount }">
			<a href="list.do?pageNum=${endPage+1 }">[다음]</a>
		</c:when>
		<c:otherwise>
			[다음]
		</c:otherwise>
	</c:choose>
	
</div>





















