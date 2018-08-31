<%@page import="lib.lib"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 
2018-08-28	윤우현	관리자 페이지 작성
 -->
 	<%
		Object ocate = request.getAttribute("cate");
		int cate = 1;
		if(ocate != null) cate = (int)ocate;
	%>
<div align="center">
	<div align="center"><th> 관리자 페이지 </th></div>
	<c:set var="cate" value="<%=cate %>"></c:set>
	<c:choose>
		<c:when test="${cate == 1 }">
			<ul><a href="layout.jsp?page=memberList.do&left=admin.jsp" style="color: #ff5;">회원관리</a></ul>
		</c:when>
		<c:otherwise>
			<ul><a href="layout.jsp?page=memberList.do&left=admin.jsp">회원관리</a></ul>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${cate == 2 }">
			<ul><a href="layout.jsp?page=/Category.do?mod=list" style="color: #ff5;">카테고리관리</a></ul>
		</c:when>
		<c:otherwise>
			<ul><a href="layout.jsp?page=/Category.do?mod=list">카테고리관리</a></ul>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${cate == 3 }">
			<ul><a href="layout.jsp?page=/boardList.do?mod=list&admin=1" style="color: #ff5;">게시글관리</a></ul>
		</c:when>
		<c:otherwise>
			<ul><a href="layout.jsp?page=/boardList.do?mod=list&admin=1">게시글관리</a></ul>
		</c:otherwise>
	</c:choose>
</div>