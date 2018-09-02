<%@page import="lib.lib"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
		String context = application.getContextPath();
		String bstatus[] = lib.bStatus(); 
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="bstatus" value="<%=bstatus %>" />   
<table class="table table-hover" style="width: 800px; margin: 50px auto 0px auto;"  >
	<thead>
	<tr>
		<th colspan="6">
		<div style="width: 100%">
		<a href="<%=context %>/layout.jsp?page=boardList.do?mod=list&cul=1&cate=${cate}">조회순</a>
		<a href="<%=context %>/layout.jsp?page=boardList.do?mod=list&cul=0&cate=${cate}">최근등록일</a>
		<a href="<%=context %>/layout.jsp?page=boardList.do?mod=list&cul=2&cate=${cate}">경매임박순</a>
		<a href="<%=context %>/layout.jsp?page=boardList.do?mod=list&cul=3&cate=${cate}">예약자순</a>
		<a href="boardList.do?mod=getCate" style="float: right;">글작성</a>
		</div>
		</th>
		
	</tr>
	
	<tr>
		<th class="text-center col-sm-1">글번호</th><th class="text-center col-sm-4">제목</th><th class="text-center col-sm-2">작성자</th><th class="text-center col-sm-2">진행상태</th><th class="text-center col-sm-1">조회수</th><th class="text-center col-sm-2">작성일</th>
	</tr>
	</thead>
		<c:forEach var="n" items="${list}">
			<tr>
				<td>${n.bnum }</td>
				<td><a href="detail.do?cmd=detail&bnum=${n.bnum}" >${n.title }</a></td>
				<td>${n.id }</td>
				<td>${bstatus[n.status]}</td>
				<td>${n.hit }</td>
				<td>${n.regdate }</td>
			</tr>
		</c:forEach>
	<tr>
		<td colspan="6" style="padding: 0px;width: 30px;">
		<!-- 페이징 -->
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
		</td>
	</tr>
	<tr>
		<td colspan="6" class="form-group">
			<form method="post" action="<%=context %>/boardList.do?mod=list">
			<span class="col-sm-2">
			<select name="searchSel" class="form-control form-control-sm">
				<option value="0">제목</option>
				<option value="1">아이디</option>
			</select>
			</span>
			<span class="input-group">
				<input type="text" class="form-control" name="search" value="${search1}" style="width: 300px; float: left;">
				<span class="input-group-btn">
				<button type="submit" class="btn btn-default">
					 <i class="glyphicon glyphicon-search" style="height:20px;"></i>
				</button>
			 	</span>
  			</span>
			<input type="hidden" name="cate" value="${cate }">
			</form>
		</td>
	</tr>
</table>