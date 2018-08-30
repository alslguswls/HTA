<%@page import="lib.lib"%>
<%@page import="board.vo.boardVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% 
String sid = (String)session.getAttribute("id");
	if(sid==null){ 
		pageContext.forward("/layout.jsp?page=login.jsp");
	}
%>
 <% 
 	String sessionLev = (String)session.getAttribute("isAdmin");
 	String[] bstatus= lib.bStatus();
%> 
<div>
<c:set var="id" value="${vo.id }" />
<c:set var="bstatus" value="<%=bstatus %>" />
<c:set var="sid" value="<%=sid %>" />
<c:set var="lev"	value="<%=sessionLev %>" />
	<table border="1">
		<tr>
			<th>글번호</th>
			<td>${vo.bnum }
			<!--  status 1번 경매 진행중에는 삭제 수정 불가 
							   2번 경매 종료 후 수정 불가능 
			 -->
			<c:if test="${id  eq sid or lev eq '1' or vo.status ne '1' }">
				<c:if test="${vo.status ne '2'}">
				<input type="button" value="수정" onclick="javascript:boardModify(${vo.bnum})">
				</c:if>
				<input type="button" value="삭제" onclick="javascript:boardDelete(${vo.bnum})">
			</c:if>
			</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td><input type="hidden" name="id">${vo.id }</td>
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
			<td>${bstatus[vo.status] }</td>
		</tr>
		<tr>
			<th>등록일</th>
			<td>${vo.regdate }</td>
		</tr>
	</table>
	<%
		int status = (int)request.getAttribute("status");
		if(status != 0){
	%>
			<input type="button" value="예약불가" id="resvBtn" disabled="disabled">
	<%
		}else{
			boolean resv = (boolean)request.getAttribute("resv");
			if(resv){
	%>
				<input type="button" value="예약완료" id="resvBtn" disabled="disabled">
	<%
			}else{
	%>
				<input type="button" value="예약" id="resvBtn" onclick="resv()" >
	<%
			}
		}
	%>
	<input type="button" value="경매참여" id="enterBtn" onclick="enter()" disabled="disabled">
	<br>
	<form action="javascript:return false;" onsubmit="comm1()" method="post">
		댓글 : <input type="text" id="comm" size="20">
		<input type="submit" value="입력">
	</form>
	<div id="list"></div>
</div>