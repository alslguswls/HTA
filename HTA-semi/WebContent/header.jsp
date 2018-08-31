<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--
2018. 8. 28	오전 9:14:29	- 로그인 여부로 '회원 가입' 매뉴 출력 방법을 변경함.(로그인 전 : 회원가입 출력, 로그인 후 : 회원관리(관리자)/마이페이지(이용자)
-->
<img id="logo" src="images/logo.png">
<h1><a href="home.jsp">억<span>!</span>션</a></h1>
<div>
	<ul class="list-inline">
		<li><a href="home.jsp">홈으로</a></li>
		<%
			String page1 = request.getParameter("page");
			if(page1 == null) page1 = "/boardList.do?mod=list";
			String left1 = request.getParameter("left");
			if(left1 == null) left1 = "/board/category.jsp";
			System.out.println(page1);
			System.out.println(left1);
		%>
		<c:set var="page" value="<%=page1 %>"></c:set>
		<c:set var="left" value="<%=left1 %>"></c:set>
		
		<!-- session에 isAdmin 값에 따라 '회원가입' 매뉴가 달라짐-->
		<%
			String isAdmin = (String)session.getAttribute("isAdmin");
			if (isAdmin == null) {
		%>
			<c:choose>
				<c:when test="${page == 'join.jsp' }">
					<li><a href="layout.jsp?page=join.jsp&left=noticeLeft.jsp" style="color: #ff5;">회원가입</a></li>
					<!-- 
					<li><a href="Category.do?mod=getCate&to=join.jsp" style="color: #ff5;">회원가입</a></li>
					 -->
				</c:when>
				<c:otherwise>
					<li><a href="layout.jsp?page=join.jsp&left=noticeLeft.jsp">회원가입</a></li>
					<!-- 
					<li><a href="Category.do?mod=getCate&to=join.jsp">회원가입</a></li>
					 -->
				</c:otherwise>
			</c:choose>
		<%
			} else if (isAdmin.equals("1") ) {
		%>
			<li><a href="layout.jsp?page=memberList.do&left=admin.jsp">관리자</a></li>
		<%
			} else {
		%>
			<c:choose>
				<c:when test="${left == 'mypage.jsp' }">
					<li><a href="layout.jsp?page=coin.do&left=mypage.jsp" style="color: #ff5;">마이페이지</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="layout.jsp?page=coin.do&left=mypage.jsp">마이페이지</a></li>
				</c:otherwise>
			</c:choose>
		<%
			}
		%>
		<c:choose>
			<c:when test="${page == 'notice.jsp' }">
				<li><a href="noticeList.do?page=notice.jsp" style="color: #ff5;">공지사항</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="noticeList.do?page=notice.jsp">공지사항</a></li>
			</c:otherwise>
		</c:choose>
		
		<c:choose>
			<c:when test="${page == '/board/list.jsp' }">
				<li><a href="layout.jsp?page=/boardList.do?mod=list" style="color: #ff5;">경매품보기</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="layout.jsp?page=/boardList.do?mod=list">경매품보기</a></li>
			</c:otherwise>
		</c:choose>
		<%
			String id = (String)session.getAttribute("id");
			if(id == null){
		%>
			<c:choose>
				<c:when test="${page == 'login.jsp' }">
					<li><a href="layout.jsp?page=login.jsp&left=noticeLeft.jsp" style="color: #ff5;">로그인</a></li>
					<!-- 
					<li><a href="Category.do?mod=getCate&to=login.jsp" style="color: #ff5;">로그인</a></li>
					 -->
				</c:when>
				<c:otherwise>
					<li><a href="layout.jsp?page=login.jsp&left=noticeLeft.jsp">로그인</a></li>
					<!-- 
					<li><a href="Category.do?mod=getCate&to=login.jsp">로그인</a></li>
					 -->
				</c:otherwise>
			</c:choose>
		<%
			}else{
				
		%>
			<li><a href="loginOk.jsp?cmd=logout">로그아웃</a></li>
		<%
			}
		%>
	</ul>
</div>