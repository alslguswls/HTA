<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
	<h3>카테고리</h3>
<ul>
	<%
		Object ocate = request.getAttribute("cate");
		int cate = 1;
		if(ocate != null) cate = (int)ocate;
	%>
	<c:set var="cate" value="${cate }"></c:set>
	<c:forEach var="n" items="${list1 }">
		<li>
			<c:choose>
				<c:when test="${cate == n.cate }">
					<a href="layout.jsp?page=/boardList.do?mod=list&cate=${n.cate}" style="color: #ff5;">${n.name }</a>
				</c:when>
				<c:otherwise>
					<a href="layout.jsp?page=/boardList.do?mod=list&cate=${n.cate}">${n.name }</a>
				</c:otherwise>
			</c:choose>
		</li>
	</c:forEach>
</ul>	
</div>