<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h2>카테고리목록</h2>
<div id="cate_in" name="">	
	<form method="post" action="Category.do?mod=insert" id="cateForm" name="cateForm">
	<span id="cateText">카테고리 추가하기</span>
	<input type="text" name="cate_n" id="cate_n">
	<input type="hidden" name="cate" id="cate" value="">
	<input type="button" value="추가" id="chButton" onclick="name_ck()">
	<input type="button" style="display:none;" value="저장" id="modify" onclick="cateModify()">
	<input type="button"  style="display:none;" name="cancle" id="cancle" value="취소" onclick="cateCancle()">
	</form>
</div>
<div id="cate_list">
	<table>
		<tr>
			<th>카테고리번호</th>
			<th  colspan="3">카테고리 이름</th>
		</tr>
	<c:forEach var="n" items="${list }">
		<tr>
			<td>${n.cate } </td>
			<td>${n.name }</td>
			<td><input type="button" value="수정" onclick="cateInfo(${n.cate },'${n.name }')" /> </td>
			<td><input type="button" value="삭제" onclick="cateDelete(${n.cate })" /></td>	
		</tr>
	</c:forEach>
		<tr>
			<td colspan="4">
				<!-- 페이징 -->
			<!-- 이전 -->
			<c:choose>
				<c:when test="${startPage>10 }">
					<a href="boardList.do?mod=list&pageNum=${startPage-1 }">[이전]</a>
				</c:when>
				<c:otherwise>
					[이전]
				</c:otherwise>
			</c:choose>
			
			<c:forEach var="i" begin="${startPage }" end="${endPage }">
			<c:choose>
				<c:when test="${pageNum==i }"><%-- 현재페이지인경우--%>
					<a href="boardList.do?mod=list&pageNum=${i }"><span style="color:red" >[${i }]</span></a>
				</c:when>
				<c:otherwise>
					<a href="boardList.do?mod=list&pageNum=${i }"><span style="color:#555" >[${i }]</span></a>
				</c:otherwise>
			</c:choose>	
			</c:forEach>
			
			<!-- 다음 -->
			<c:choose>
				<c:when test="${endPage<pageCount }">
					<a href="boardList.do?mod=list&pageNum=${endPage+1 }">[다음]</a>
				</c:when>
				<c:otherwise>
					[다음]
				</c:otherwise>
			</c:choose>
			</td>
		</tr>
	</table> 
</div>
		
		
