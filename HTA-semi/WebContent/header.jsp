<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--
2018. 8. 28	오전 9:14:29	- 로그인 여부로 '회원 가입' 매뉴 출력 방법을 변경함.(로그인 전 : 회원가입 출력, 로그인 후 : 회원관리(관리자)/마이페이지(이용자)
-->
<h1><a href="#">억!션</a></h1>
<div>
	<ul class="list-inline">
		<li><a href="layout.jsp?page=/boardList.do?mod=list&cate=0">홈으로</a></li>
		
		<!-- session에 isAdmin 값에 따라 '회원가입' 매뉴가 달라짐-->
		<%
			String isAdmin = (String)session.getAttribute("isAdmin");
			if (isAdmin == null) {
		%>
			<li><a href="layout.jsp?page=join.jsp">회원가입</a></li>
		<%
			} else if (isAdmin.equals("1") ) {
		%>
			<li><a href="layout.jsp?left=admin.jsp">관리자</a></li>
		<%
			} else {
		%>
			<li><a href="layout.jsp?left=mypage.jsp">마이페이지</a></li>
		<%
			}
		%>
		
		<li><a href="noticeList.do?page=notice.jsp">공지사항</a></li>
		<li><a href="layout.jsp?page=/boardList.do?mod=list&cate=0">경매품보기</a></li>
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