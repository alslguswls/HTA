<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h2>카테고리목록</h2>
<div "cate_in">	
	<form method="post" action="/Category.do?mod=insert" id="cateForm" name="cateForm">
	<span>카테고리 추가하기</span>
	<input type="text" name="cate_n" id="cate_n"> 
	<input type="button" value="추가" onclick="name_ck()">
	</form>
</div>



		<!-- 페이징 -->
		<div>
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
		</div>
