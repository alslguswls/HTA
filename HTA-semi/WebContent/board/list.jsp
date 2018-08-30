<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
		String context = application.getContextPath();
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h2>게시글목록</h2>     
<div><a href="boardList.do?mod=getCate">글작성</a></div>
<table>
	<tr>
		<th colspan="5">
		<div>
		<a href="<%=context %>/layout.jsp?page=boardList.do?mod=list&cul=1">조회순</a>
		<a href="<%=context %>/layout.jsp?page=boardList.do?mod=list&cul=0">최근등록일</a>
		<a href="<%=context %>/layout.jsp?page=boardList.do?mod=list&cul=2">경매임박순</a>
		</div>
		</th>
		
	</tr>
	<tr>
		<th>글번호</th><th>제목</th><th>작성자</th><th>조회수</th><th>작성일</th>
	</tr>
		<c:forEach var="n" items="${list}">
			<tr>
				<td>${n.bnum }<td>
				<td><a href="<%=context %>/layout.jsp?page=detail.do?cmd=detail&bnum=${n.bnum}">${n.title }</a><td>
				<td>${n.id }<td>
				<td>${n.hit }<td>
				<td>${n.regdate }<td>
			</tr>
		</c:forEach>
		
		
	<tr>
		<td colspan="5">
		<!-- 페이징 -->
		<div>
			<!-- 이전 -->
			<c:choose>
				<c:when test="${startPage>10 }">
					<a href="boardList.do?mod=list&pageNum=${startPage-1 }">[이전]</a>
				</c:when>
				<c:otherwise>
					[이전]
				</c:otherwise>
			</c:choose>
			
			<c:forEach var="i" begin="${startPage }" end="${endPage }">
			<c:choose>
				<c:when test="${pageNum==i }"><%-- 현재페이지인경우--%>
					<a href="boardList.do?mod=list&pageNum=${i }"><span style="color:red" >[${i }]</span></a>
				</c:when>
				<c:otherwise>
					<a href="boardList.do?mod=list&pageNum=${i }"><span style="color:#555" >[${i }]</span></a>
				</c:otherwise>
			</c:choose>	
			</c:forEach>
			
			
			<!-- 다음 -->
			<c:choose>
				<c:when test="${endPage<pageCount }">
					<a href="boardList.do?mod=list&pageNum=${endPage+1 }">[다음]</a>
				</c:when>
				<c:otherwise>
					[다음]
				</c:otherwise>
			</c:choose>
			
		</div>
		</td>
	</tr>
	<tr>
		<td colspan="5">
			<form method="post" action="<%=context %>/boardList.do?mod=list">
			<select name="searchSel">
				<option value="0">제목</option>
				<option value="1">아이디</option>
			</select>
			<input type="text" name="search" value="${search}">
			<input type="submit" value="조회">
			<input type="hidden" name="cate" value="${cate }">
			</form>
		</td>
	</tr>
</table>