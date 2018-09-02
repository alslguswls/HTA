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
<c:set var="id1" value="${vo.id }" />
<c:set var="bstatus" value="<%=bstatus %>"/>
<c:set var="sid" value="<%=sid %>" />
<c:set var="lev"	value="<%=sessionLev %>"/>
<c:set var="orgfile"	value="${vo.orgfilename }"/>
	<h3>경매품 조회</h3>
	<table id="detailtable" class="table table-striped" border="2">
		<tr>
			<th>글번호</th>
			<td>${vo.bnum }
			<!--  status 1번 경매 진행중에는 삭제 수정 불가 
							   2번 경매 종료 후 수정 불가능 
			 -->
			<c:if test="${id1  eq sid or lev eq '1'}">
				<c:if test="${vo.status ne '1' }">
					<c:if test="${vo.status ne '2' }">
					<input type="button" value="수정" onclick="javascript:boardModify(${vo.bnum})" class="btn btn-default btn-xs">
					</c:if>
				<input type="button" value="삭제" onclick="javascript:boardDelete(${vo.bnum})" class="btn btn-default btn-xs">
				</c:if>
			</c:if>
			</td>
			<th>작성자</th>
			<td><input type="hidden" name="id">${vo.id }</td>
			<th>카테고리</th>
			<td>${vo.cate }</td>
		</tr>
		<tr>
		</tr>
		<tr>
			<th>제목</th>
			<td colspan="5">${vo.title }</td>
		</tr>
		<tr>
			<th>설명</th>
			<td colspan="5">${vo.content }</td>
		</tr>
		<tr>
			<th>사진</th>
			<td colspan="5">
				<c:if test="${orgfile ne null}">
				<img src="upload/${vo.savefilename }">
				</c:if>
			</td>
		</tr>
		<tr>
			<th>경매시작시간</th>
			<td>${vo.starttime }</td>
			<th>경매시작가</th>
			<td colspan="3">${vo.startprice }</td>
		</tr>
		<tr>
			<th>조회수</th>
			<td>${vo.hit }</td>
			<th>경매예약자수</th>
			<td colspan="3">${vo.regv }</td>
		</tr>
		<tr>
			<th>경매품상태</th>
			<td>${bstatus[vo.status] }</td>
			<th>등록일</th>
			<td colspan="3">${vo.regdate }</td>
		</tr>
	</table>
	<%
		int status = (int)request.getAttribute("status");
		if(status != 0){
	%>
			<input type="button" value="예약불가" id="resvBtn" disabled="disabled" class="btn btn-warning btn-sm">
	<%
		}else{
			boolean resv = (boolean)request.getAttribute("resv");
			if(resv){
	%>
				<input type="button" value="예약완료" id="resvBtn" disabled="disabled" class="btn btn-warning btn-sm">
	<%
			}else{
	%>
				<input type="button" value="예약" id="resvBtn" onclick="resv()" class="btn btn-warning btn-sm">
	<%
			}
		}
	%>
	<input type="button" value="경매참여" id="enterBtn" onclick="enter()" disabled="disabled" class="btn btn-warning btn-sm">
	<br>
	<form id="detailform" action="javascript:return false;" onsubmit="comm1()" method="post" class="form-inline">
		댓글 : <input type="text" id="comm" size="60" class="form-control">
		<input type="submit" value="입력" class="btn btn btn-xs">
	</form>
	<div id="detaillist"></div>
</div>