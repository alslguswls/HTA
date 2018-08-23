<%@page import="lib.lib"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	//카테고리 리스트 가져오기
	lib lb = new lib();
	String[] cate=lb.category();
%>
<c:set var="cate" value="<%=cate %>"/>
<form id="newBoard" method="post" action="insertBoard.do" enctype="multipart/form-data">
	<table>
		<tr>
			<th >작성자</th><td>${session.id }</td>
		</tr>
		<tr>
			<th >제목</th><td><input type="text" name="title" id="title"></td>
		</tr>
		<tr>
			<th >카테고리</th>
			<td>
				<select id="cate">
					<c:forEach var="n" items="${cate }" varStatus="cate">
						<option value="${cate.index}">${n }</option>
					</c:forEach>
				</select>
			 </td>
		</tr>
		<tr>
			<th>시작가격</th><td><input type="text" name="price" id="price"></td>
		</tr>
		<tr>
			<th >시작날짜</th><td><input type="text" id="sdate" name="sdate"></td>
		</tr>
		<tr>
			<th>시작시간선택</th>
			<td>
				<select id="hh">
					<option>시</option>
					<%for(int i=1;i<25;i++){ 
						String n=""+i;
						if(i==24){n="00";}
					%>
						<option value="<%=n%>"><%=n%></option>
					<%} %>
				</select>
				<select id="mm">
					<option>분</option>
					<%for(int i=0;i<61;i++){ %>
						<option value="<%=i%>"><%=i%></option>
					<%} %>
				</select>
			</td>
		</tr>
		<tr>
			<th>이미지</th><td><input type="file" name="orgfile" id="orgfile"></td>
		</tr>
		<tr>
			<th >내용</th><td><textarea cols="5" rows="10" name="content" id="content"></textarea> </td>
		</tr>
		<tr>
			<td colspan="2" ><input type="button" value="게시글 등록하기" onclick="check()"> </td>
		</tr>
	</table>
</form>