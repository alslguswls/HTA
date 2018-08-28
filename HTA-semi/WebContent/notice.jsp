<%@page import="vo.notice.NoticeVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	//공지사항 메인보드
	function writeNext() {
		document.location.href="layout.jsp?page=write.jsp";
	}
</script>
</head>
<body>
<%	//로그인으로 넘겨받은 id
	String id2 = null;
	ArrayList<NoticeVO> list = (ArrayList<NoticeVO>)request.getAttribute("list22");
	//System.out.print(id2);
%>
<h2>공지사항</h2>
<%	if(session.getAttribute("id")!=null){
	id2 = (String)session.getAttribute("id");

	 if(id2.equals("admin2")){//운영자일때
		%>
		<table border="1" width="500">
		<tr>
			<th>번호</th><th>제목</th><th>내용</th><th>작성일</th><th>내용수정</th><th>삭제</th>
		</tr>
		<c:set var="list1" value="<%=list %>"/>
		<c:forEach var="vo2" items="<%=list %>" >
		<tr>
			<td>${vo2.noti_no }</td>
			<td>${vo2.title }</td>
			<td>${vo2.content }</td>
			<td>${vo2.regdate }</td>
			<td><a href="notiupdate.do?noti_no=${vo2.noti_no}">수정</a></td>
			<td><a href="deleteWarning.jsp?noti_no=${vo2.noti_no}">삭제</a></td>
		</tr>
		
		</c:forEach>

	</table>
	<div>
		<c:choose>
			<c:when test="${startPage>10 }">
				<a href="noticeList.do?pageNum=${startPage-1 }">[이전]</a>
			</c:when>
			<c:otherwise>
				[이전]
			</c:otherwise>
		</c:choose>
		<c:forEach var="i" begin="${startPage }" end="${endPage }">
		<c:choose>
			<c:when test="${pageNum==i }">
				<a href="noticeList.do?pageNum=${i }"><span style="color: red;">[${i }]</span></a>
		
		</c:when>
		<c:otherwise>
			<a href="noticeList.do?pageNum=${i }"><span style="color: #555;">[${i }]</span></a>
		</c:otherwise>
		
		</c:choose>
		
		</c:forEach>
		<c:choose>
			<c:when test="${endPage<pageCount }">
				<a href="noticeList.do?pageNum=${endPage+1 }">[다음]</a>
			
			</c:when>
			
		</c:choose>
		
		
	</div>
	<br>
	<form action="notisearch.do" method="post" >
		<select name="search">
			<option value="title">제목</option>
			<option value="content">내용</option>
			
		</select>
		<input type="text" name="keyword">
		<input type="submit" value="검색">
	</form>
	<br>
	<input type="button" value="글 작성" onclick="writeNext()">
	<%
	} else if(!id2.equals("admin2")){//관리자가 아닌 일반회원일 경우
			%>
			<table border="1" width="500">
			<tr>
				<th>번호</th><th>제목</th><th>내용</th><th>작성일</th>
			</tr>
			<c:forEach var="voNo" items="${requestScope.list22 }" >
			<tr>
				<td>${voNo.noti_no }</td>
				<td>${voNo.title }</td>
				<td>${voNo.content }</td>
				<td>${voNo.regdate }</td>
				
			</tr>
			
			</c:forEach>

		</table>
		<div>
			<c:choose>
				<c:when test="${startPage>10 }">
					<a href="noticeList.do?pageNum=${startPage-1 }">[이전]</a>
				</c:when>
				<c:otherwise>
					[이전]
				</c:otherwise>
			</c:choose>
			<c:forEach var="i" begin="${startPage }" end="${endPage }">
			<c:choose>
				<c:when test="${pageNum==i }">
					<a href="noticeList.do?pageNum=${i }"><span style="color: red;">[${i }]</span></a>
			
			</c:when>
			<c:otherwise>
				<a href="noticeList.do?pageNum=${i }"><span style="color: #555;">[${i }]</span></a>
			</c:otherwise>
			
			</c:choose>
			
			</c:forEach>
			<c:choose>
				<c:when test="${endPage<pageCount }">
					<a href="noticeList.do?pageNum=${endPage+1 }">[다음]</a>
				
				</c:when>
				
			</c:choose>
		
		</div>
		<br>
		<form action="notisearch.do" method="post" >
			<select name="search">
				<option value="title">제목</option>
				<option value="content">내용</option>
				
			</select>
			<input type="text" name="keyword">
			<input type="submit" value="검색">
		</form>
			
			<%
		} 
		
	} else if(session.getAttribute("id")==null){//아이디 없이 공지사항 들어갈 때
		%>
		<table border="1" width="500">
		<tr>
			<th>번호</th><th>제목</th><th>내용</th><th>작성일</th>
		</tr>
		<c:forEach var="vo4" items="${requestScope.list22 }" >
		<tr>
			<td>${vo4.noti_no }</td>
			<td>${vo4.title }</td>
			<td>${vo4.content }</td>
			<td>${vo4.regdate }</td>
			
		</tr>
		
		</c:forEach>

	</table>
	<div>
		<c:choose>
			<c:when test="${startPage>10 }">
				<a href="noticeList.do?pageNum=${startPage-1 }">[이전]</a>
			</c:when>
			<c:otherwise>
				[이전]
			</c:otherwise>
		</c:choose>
		<c:forEach var="i" begin="${startPage }" end="${endPage }">
		<c:choose>
			<c:when test="${pageNum==i }">
				<a href="noticeList.do?pageNum=${i }"><span style="color: red;">[${i }]</span></a>
		
		</c:when>
		<c:otherwise>
			<a href="noticeList.do?pageNum=${i }"><span style="color: #555;">[${i }]</span></a>
		</c:otherwise>
		
		</c:choose>
		
		</c:forEach>
		<c:choose>
			<c:when test="${endPage<pageCount }">
				<a href="noticeList.do?pageNum=${endPage+1 }">[다음]</a>
			
			</c:when>
			
		</c:choose>
	
	</div>
	<br>
	<form action="notisearch.do" method="post" >
		<select name="search">
			<option value="title">제목</option>
			<option value="content">내용</option>
			
		</select>
		<input type="text" name="keyword">
		<input type="submit" value="검색">
	</form>
		
	<%
	} 
%>

<br><!-- 여기 작성 필요함 -->
<a href="layout.jsp">메인페이지로</a>

</body>
</html>