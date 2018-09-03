<%@page import="vo.notice.NoticeVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
 
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >
<title>Insert title here</title>
<style type="text/css">
	.godcenter {display: block; width: 33%; height: auto; margin: 0 auto;}
	
	td {color:#80bfff;}
	th {margin-left: auto; margin-right: auto;  text-align: center;}
	
</style>
<script type="text/javascript">
	//공지사항 메인보드
	function writeNext() {
		document.location.href="writeaddr.do";
	}
</script>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>


 

</head>
<body class="godcenter">


<%	//로그인으로 넘겨받은 id
	String id2 = null;
	ArrayList<NoticeVO> list = (ArrayList<NoticeVO>)request.getAttribute("list22");
	//System.out.print(id2);table table-striped  class="godcenter"
	
%>
<h2>공지사항</h2>

<%	if(session.getAttribute("id")!=null){
	id2 = (String)session.getAttribute("id");
	String admin1 = (String)session.getAttribute("isAdmin"); 
	System.out.print(admin1);
	 if(admin1.equals("1")){//운영자일때
		%>
		
		<table  class="table table-striped">
		<tr class="titlenotice" align="center">
			<th class="text-center col-sm-1">번호</th><th class="text-center col-sm-1">제목</th><th class="text-center col-sm-1">내용</th><th class="text-center col-sm-1">작성일</th><th class="text-center col-sm-1">내용수정</th><th class="text-center col-sm-1">삭제</th>
		</tr>
		<c:set var="list1" value="<%=list %>"/>
		<c:forEach var="vo2" items="<%=list %>" >
		<tr class="text-primary">
			<td>${vo2.noti_no }</td>
			<td><a href="detailContent.do?noti_no=${vo2.noti_no}">${vo2.title }</a></td>
			<td>${vo2.content }</td>
			<td>${vo2.regdate }</td>
			<td><a href="notiupdate.do?noti_no=${vo2.noti_no}">수정</a></td>
			<td><a href="deleteWarning.jsp?noti_no=${vo2.noti_no}">삭제</a></td>
		</tr>
		
		</c:forEach>

	</table>
	
	<div>
		<ul class="pagination">
		<c:choose>
		
			<c:when test="${startPage>10 }">
				<li><a href="noticeList.do?pageNum=${startPage-1 }">[이전]</a></li>
			</c:when>
			<c:otherwise>
				<li>[이전]</li>
			</c:otherwise>
			
		</c:choose>
		
		<c:forEach var="i" begin="${startPage }" end="${endPage }">
	
		<c:choose>
			<c:when test="${pageNum==i }">
				<li class="active"><a href="noticeList.do?pageNum=${i }"><span style="color: red;">${i}</span></a></li>
		
		</c:when>
		<c:otherwise>
			<li><a href="noticeList.do?pageNum=${i }"><span style="color: #555;">${i}</span></a></li>
		</c:otherwise>
		
		</c:choose>
		
		</c:forEach>
		
		<c:choose>
			<c:when test="${endPage<pageCount }">
				<li><a href="noticeList.do?pageNum=${endPage+1 }">[다음]</a></li>
			
			</c:when>
			
		</c:choose>
		</ul>
		
	</div>
	<br>
	<form action="notisearch.do" method="post" class="form-group" >
	
		<select name="search">
			<option value="title">제목</option>
			<option value="content">내용</option>
			
		</select>
		
		
		<input type="text"  name="keyword">
		
		<input type="submit" class="btn " value="검색">
		
		
		
	</form>
	<br>
	
	
	<%
	} else if(admin1.equals("0")){//관리자가 아닌 일반회원일 경우
			%>
			<table class="table table-striped" align="center">
			<tr>
				<th class="text-center col-sm-1">번호</th><th class="text-center col-sm-1">제목</th><th class="text-center col-sm-1">내용</th><th class="text-center col-sm-1">작성일</th>
			</tr>
			<c:forEach var="voNo" items="${requestScope.list22 }" >
			<tr>
				<td>${voNo.noti_no }</td>
				<td><a href="detailContent.do?noti_no=${voNo.noti_no}">${voNo.title }</a></td>
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
		<table class="table table-striped" align="center">
		<tr>
			<th class="text-center col-sm-1">번호</th><th class="text-center col-sm-1">제목</th><th class="text-center col-sm-1">내용</th><th class="text-center col-sm-1">작성일</th>
		</tr>
		<c:forEach var="vo4" items="${requestScope.list22 }" >
		<tr>
			<td>${vo4.noti_no }</td>
			<td><a href="detailContent.do?noti_no=${vo4.noti_no}">${vo4.title }</a></td>
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


</body>
</html>