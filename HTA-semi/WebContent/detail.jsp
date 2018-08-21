<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
	<table border="1">
		<tr>
			<th>글번호</th>
			<td>${vo.bnum }</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${vo.id }</td>
		</tr>
		<tr>
			<th>카테고리</th>
			<td>${vo.cate }</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${vo.title }</td>
		</tr>
		<tr>
			<th>설명</th>
			<td>${vo.content }</td>
		</tr>
		<tr>
			<th>사진</th>
			<td><img src="upload/${vo.savefilename }"></td>
		</tr>
		<tr>
			<th>경매시작시간</th>
			<td>${vo.starttime }</td>
		</tr>
		<tr>
			<th>경매시작가</th>
			<td>${vo.startprice }</td>
		</tr>
		<tr>
			<th>조회수</th>
			<td>${vo.hit }</td>
		</tr>
		<tr>
			<th>경매예약자수</th>
			<td>${vo.regv }</td>
		</tr>
		<tr>
			<th>경매품상태</th>
			<td>${vo.status }</td>
		</tr>
	</table>
	<%
		String id = (String)session.getAttribute("id");
		
	%>
	<input type="button" value="예약" id="resvBtn" onclick="resv()" >
	<input type="button" value="예약완료" id="resvBtn" disabled="disabled">
	
</div>