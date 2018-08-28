<%@page import="lib.lib"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
	<div> 카테고리</div>
<%
	//카테고리 리스트 가져오기
	lib lb = new lib();
	String[] cate=lb.category();
%>
	<c:set var="cate" value="<%=cate %>"/>
	<c:forEach var="n" items="${cate }" varStatus="cate">
	<a href="layout.jsp?page=/boardList.do?mod=list&cate=${cate.index}">${n }</a><br>
	</c:forEach>
</div>