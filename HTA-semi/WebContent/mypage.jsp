<%@page import="lib.lib"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 
2018-08-28	윤우현	마이페이지 작성
 -->

	<div align="center"> 마이페이지</div>
	<ul><a href="layout.jsp?page=coin.do&left=mypage.jsp"> 코인 충전</a></ul>
	<ul><a href="layout.jsp?page=perReser.do"> 경매 예약 확인 </a></ul>
	<ul><a href="layout.jsp?page=joinDeal.do"> 경매 낙찰이력 확인 </a></ul>
	<ul><a href="layout.jsp?page=joinchat.do"> 경매 참여이력 확인 </a></ul>


	<!--   ' &left=mypage.jsp ' 링크걸때 이거 파라메터 값 넣어줘야 함
		예제: <ul><a href="layout.jsp?page=memberList.do&left=admin.jsp">회원관리</a></ul>
	 -->
