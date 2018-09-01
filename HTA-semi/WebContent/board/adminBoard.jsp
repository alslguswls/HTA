<%@page import="lib.lib"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
		String context = application.getContextPath();
		String bstatus[] = lib.bStatus(); 
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="bstatus" value="<%=bstatus %>" />
<h3>게시글관리페이지</h3>
<!-- <div><a href="boardList.do?mod=getCate">글작성</a></div> -->
<table class="table table-hover">
	<tr>
		<th colspan="12">
		<div>
		<select id="AdminCategorySel" onchange="selCate(this.value)">
				<c:forEach var="Acate" items="${list1 }">
					<option value="${Acate.cate }" <c:if test="${cate eq Acate.cate}">selected</c:if>>${Acate.name }</option>
				</c:forEach>
		</select>
		<a href="<%=context %>/layout.jsp?page=boardList.do?mod=list&cul=1&admin=1&cate=${cate}">조회순</a>
		<a href="<%=context %>/layout.jsp?page=boardList.do?mod=list&cul=0&admin=1&cate=${cate}">최근등록일</a>
		<a href="<%=context %>/layout.jsp?page=boardList.do?mod=list&cul=2&admin=1&cate=${cate}">경매임박순</a>
		<a href="<%=context %>/layout.jsp?page=boardList.do?mod=list&cul=3&admin=1&cate=${cate}">예약자순</a>
		</div>
		</th>
		
	</tr>
	<tr>
		<th>글번호</th><th>아이디</th><th>제목</th><th>내용</th>
		<th>경매시작시간</th>
		<th>시작가</th>
		<th>조회수</th><th>예약자수</th><th>진행상태</th><th colspan="3">등록일</th>
	</tr>
		<c:forEach var="n" items="${list}">
			<tr>
				<td>${n.bnum }</td>
				<td>${n.id }</td>
				<td>	<a href="detail.do?cmd=detail&bnum=${n.bnum}&admin=1">${n.title }</a></td>
				<td>${n.content }</td>
				<td>${n.starttime }</td>
				<td>${n.startprice }</td>
				<td>${n.hit }</td>
				<td>${n.regv}</td>	
				<td>${bstatus[n.status]}</td>
				<td>${n.regdate }</td>
				<td><input type="button" value="수정" onclick="javascript:AboardModify(${n.bnum})" class="btn btn-default btn-xs"></td>
				<td><input type="button" value="삭제" onclick="javascript:AboardDelete(${n.bnum})" class="btn btn-default btn-xs"></td>
			</tr>
		</c:forEach>
	<tr>
		<td colspan="12">
		<!-- 페이징 -->
		<div>
			<!-- 이전 -->
			<c:choose>
				<c:when test="${startPage>10 }">
					<a href="boardList.do?mod=list&pageNum=${startPage-1 }&admin=1">[이전]</a>
				</c:when>
				<c:otherwise>
					[이전]
				</c:otherwise>
			</c:choose>
			
			<c:forEach var="i" begin="${startPage }" end="${endPage }">
			<c:choose>
				<c:when test="${pageNum==i }"><%-- 현재페이지인경우--%>
					<a href="boardList.do?mod=list&pageNum=${i }&admin=1"><span style="color:red" >[${i }]</span></a>
				</c:when>
				<c:otherwise>
					<a href="boardList.do?mod=list&pageNum=${i }&admin=1"><span style="color:#555" >[${i }]</span></a>
				</c:otherwise>
			</c:choose>	
			</c:forEach>
			<!-- 다음 -->
			<c:choose>
				<c:when test="${endPage<pageCount }">
					<a href="boardList.do?mod=list&pageNum=${endPage+1 }&admin=1">[다음]</a>
				</c:when>
				<c:otherwise>
					[다음]
				</c:otherwise>
			</c:choose>
			
		</div>
		</td>
	</tr>
	<tr>
		<td colspan="12">
			<form method="post" action="<%=context %>/boardList.do?mod=list&admin=1">
			<select name="searchSel">
				<option value="0">제목</option>
				<option value="1">아이디</option>
			</select>
			<input type="text" name="search" value="${search1}">
			<input type="submit" value="조회">
			<input type="hidden" name="cate" value="${cate }">
			</form>
		</td>
	</tr>
</table>