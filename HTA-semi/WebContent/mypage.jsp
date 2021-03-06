<%@page import="lib.lib"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 
2018-08-28	윤우현	마이페이지 작성
2018-09-02	윤우현	회원정보 수정 페이지 추가
 -->
	<%
		Object ocate = request.getAttribute("cate");
		int cate = 1;
		if(ocate != null) cate = (int)ocate;
	%>
	<div align="center">
		<div><strong> 마이페이지 </strong></div> 
		<c:choose>
			<c:when test="${cate == 5 }">
				<ul><a href="layout.jsp?page=memberGetinfo.do&left=mypage.jsp" style="color: #ff5;"> 회원정보수정</a></ul>
			</c:when>
			<c:otherwise>
				<ul><a href="layout.jsp?page=memberGetinfo.do&left=mypage.jsp"> 회원정보수정</a></ul>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${cate == 1 }">
				<ul><a href="layout.jsp?page=coin.do&left=mypage.jsp" style="color: #ff5;"> 코인 충전</a></ul>
			</c:when>
			<c:otherwise>
				<ul><a href="layout.jsp?page=coin.do&left=mypage.jsp"> 코인 충전</a></ul>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${cate == 2 }">
				<ul><a href="layout.jsp?page=perReser.do" style="color: #ff5;"> 경매 예약 확인 </a></ul>
			</c:when>
			<c:otherwise>
				<ul><a href="layout.jsp?page=perReser.do"> 경매 예약 확인 </a></ul>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${cate == 3 }">
				<ul><a href="layout.jsp?page=joinDeal.do" style="color: #ff5;"> 경매 낙찰이력 확인 </a></ul>
			</c:when>
			<c:otherwise>
				<ul><a href="layout.jsp?page=joinDeal.do"> 경매 낙찰이력 확인 </a></ul>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${cate == 4 }">
				<ul><a href="layout.jsp?page=joinchat.do" style="color: #ff5;"> 경매 출품내역 확인 </a></ul>
			</c:when>
			<c:otherwise>
				<ul><a href="layout.jsp?page=joinchat.do"> 경매 출품내역 확인 </a></ul>
			</c:otherwise>
		</c:choose>
	</div>


	<!--   ' &left=mypage.jsp ' 링크걸때 이거 파라메터 값 넣어줘야 함
		예제: <ul><a href="layout.jsp?page=memberList.do&left=admin.jsp">회원관리</a></ul>
	 -->
