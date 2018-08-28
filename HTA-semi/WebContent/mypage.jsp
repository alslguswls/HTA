<%@page import="lib.lib"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 
2018-08-28	윤우현	마이페이지 작성
 -->
<div>
	<div> 마이페이지</div>
<%
	//카테고리 리스트 가져오기
	lib lb = new lib();
	String[] cate=lb.category();
%>
	<c:set var="cate" value="<%=cate %>"/>
	<c:forEach var="n" items="${cate }" varStatus="cate">
	<a href="layout.jsp?page=/boardList.do?cate=${cate.index}">${n }</a><br>
	</c:forEach>
</div>