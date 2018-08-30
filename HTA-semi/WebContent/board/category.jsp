<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
	<div> 카테고리</div>
<ul>
	<c:forEach var="n" items="${list }">
		<li>
			<a href="layout.jsp?page=/boardList.do?mod=list&cate=${n.cate}">${n.name }</a>
		</li>
	</c:forEach>
</ul>	
</div>