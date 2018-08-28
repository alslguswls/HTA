<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
//검색된 뒤의 공지사항들
//공지사항 메인보드
function write() {
	document.location.href="/admin/write.jsp";
}

</script>
</head>
<body>
<%	//로그인으로 넘겨받은 id
	String id = (String)session.getAttribute("id");
%>
<h2>검색된 공지사항</h2>
	<%
	if(id.equals("admin")){//운영자일때
		%>
<table border="1" width="500">
		<tr>
			<th>번호</th><th>제목</th><th>내용</th><th>작성일</th><th>내용수정</th><th>삭제</th>
		</tr>
		<c:forEach var="vo" items="${requestScope.list2 }" >
		<tr>
		<th>${vo.noti_no }</th>
			<th>${vo.title }</th>
			<th>${vo.content }</th>
			<th>${vo.regdate }</th>
			<th><a href="notiupdate.do?noti_no=${vo.noti_no}">수정</a></th>
			<th><a href="deleteWarning.html?noti_no=${vo.noti_no}">삭제</a></th>
		</tr>
		
		</c:forEach>
			
	</table>
	<div>
		<c:choose>
			<c:when test="${startPage>10 }">
				<a href="notisearch.do?pageNum=${startPage-1 }">[이전]</a>
			</c:when>
			<c:otherwise>
				[이전]
			</c:otherwise>
		</c:choose>	
		<c:forEach var="i" begin="${startPage }" end="${endPage }">
		<c:choose>
			<c:when test="${pageNum==i }">
				<a href="notisearch.do?pageNum=${i }"><span style="color: red;">[${i }]</span></a>	
		</c:when>
		<c:otherwise>
			<a href="notisearch.do?pageNum=${i }"><span style="color: #555;">[${i }]</span></a>
		</c:otherwise>
		
		</c:choose>
		
		</c:forEach>
		<c:choose>		
			<c:when test="${endPage<pageCount }">
				<a href="notisearch.do?pageNum=${endPage+1 }">[다음]</a>
			
			</c:when>
			
		</c:choose>
		
		
	</div>
	<br>
	<input type="button" value="글 작성" onclick="write()">
	<%
	}else {//운영자가 아닐때
		%>
		<table border="1" width="500">
		<tr>
			<th>번호</th><th>제목</th><th>내용</th><th>작성일</th>
		</tr>
		<c:forEach var="vo" items="${requestScope.list2 }" >
		<tr>
			<th>${vo.noti_no }</th>
			<th>${vo.title }</th>
			<th>${vo.content }</th>
			<th>${vo.regdate }</th>
	</tr>
		
		</c:forEach>

	</table>
	<div>
		<c:choose>
			<c:when test="${startPage>10 }">
				<a href="notisearch.do?pageNum=${startPage-1 }">[이전]</a>
			</c:when>
			<c:otherwise>
				[이전]
			</c:otherwise>
		</c:choose>
		<c:forEach var="i" begin="${startPage }" end="${endPage }">
		<c:choose>
			<c:when test="${pageNum==i }">
				<a href="notisearch.do?pageNum=${i }"><span style="color: red;">[${i }]</span></a>
		
		</c:when>
		<c:otherwise>
			<a href="notisearch.do?pageNum=${i }"><span style="color: #555;">[${i }]</span></a>
		</c:otherwise>
		
		</c:choose>
		
		</c:forEach>
		<c:choose>
			<c:when test="${endPage<pageCount }">
				<a href="notisearch.do?pageNum=${endPage+1 }">[다음]</a>
			
			</c:when>
			
		</c:choose>
	
	</div>
		<%
	}
%>

<br><!-- 여기 작성 필요함 -->
<a href="notice.jsp">검색 전 페이지로</a>
<br>
<a href="~~~">메인페이지로</a>
</body>
</html>