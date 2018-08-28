<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h1>경매사이트</h1>
<div>
	<ul>
		<li><a href="layout.jsp?page=main.jsp">홈으로</a></li>
		<li><a href="layout.jsp?page=join.jsp">회원가입</a></li>
		<li><a href="layout.jsp?page=notice.jsp">공지사항</a></li>
		<li><a href="layout.jsp?page=list.jsp">경매품보기</a></li>
		<%
			String id = (String)session.getAttribute("id");
			if(id == null){
		%>
			<li><a href="layout.jsp?page=login.jsp">로그인</a></li>
		<%
			}else{
		%>
			<li><a href="logout.jsp">로그아웃</a></li>
		<%
			}
		%>
	</ul>
</div>