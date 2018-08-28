<%@page import="vo.notice.SearchVO"%>
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
//검색된 뒤의 공지사항들
//공지사항 메인보드
function writeSearch() {
	document.location.href="layout.jsp?page=write.jsp";
}

</script>
</head>
<body>
<%	//로그인으로 넘겨받은 id
	String idSearch = null;
	ArrayList<NoticeVO> list33 = (ArrayList<NoticeVO>)request.getAttribute("list2");
	SearchVO vose2 = (SearchVO) request.getAttribute("vose");
	//System.out.println(list33);
%>
<h2>검색된 공지사항</h2>
	<%
	
	if(session.getAttribute("id")!=null){
		idSearch = (String)session.getAttribute("id");
	
	if(idSearch.equals("admin2")){//운영자일때
		
		 if(list33!=null){//검색된 공지사항 있을때
		%>
<table border="1" width="500">
		<tr>
			<th>번호</th><th>제목</th><th>내용</th><th>작성일</th><th>내용수정</th><th>삭제</th>
		</tr>
		<c:forEach var="voSe" items="<%=list33 %>" >
		<tr>
		<td>${voSe.noti_no }</td>
			<td>${voSe.title }</td>
			<td>${voSe.content }</td>
			<td>${voSe.regdate }</td>
			<td><a href="notiupdate.do?noti_no=${voSe.noti_no}">수정</a></td>
			<td><a href="deleteWarning.jsp?noti_no=${voSe.noti_no}">삭제</a></td>
		</tr>
		
		</c:forEach>
			
	</table>
	<div>
		<c:choose>
			<c:when test="${startPage>10 }">
				<a href="notisearch.do?pageNum2=${startPage-1 }&search=<%=vose2.getSearch() %>&keyword=<%=vose2.getKeyword()%>">[이전]</a>
			</c:when>
			<c:otherwise>
				[이전]
			</c:otherwise>
		</c:choose>	
		<c:forEach var="i" begin="${startPage }" end="${endPage }">
		<c:choose>
			<c:when test="${pageNum==i }">
				<a href="notisearch.do?pageNum2=${i }&search=<%=vose2.getSearch() %>&keyword=<%=vose2.getKeyword()%>"><span style="color: red;">[${i }]</span></a>	
		</c:when>
		<c:otherwise>
			<a href="notisearch.do?pageNum2=${i }&search=<%=vose2.getSearch() %>&keyword=<%=vose2.getKeyword()%>"><span style="color: #555;">[${i }]</span></a>
		</c:otherwise>
		
		</c:choose>
		
		</c:forEach>
		<c:choose>		
			<c:when test="${endPage<pageCount }">
				<a href="notisearch.do?pageNum2=${endPage+1 }&search=<%=vose2.getSearch() %>&keyword=<%=vose2.getKeyword()%>">[다음]</a>
			
			</c:when>
			
		</c:choose>
		
		
	</div>
	<br>
	<input type="button" value="글 작성" onclick="writeSearch()">
	<%
	}else if(list33==null){
		%>
		<h3>검색된 공지사항이 없습니다</h3>
		<%
	}
	}else if(!idSearch.equals("admin2")){ //운영자가 아닌 일반 회원일 경우
			if(list33!=null){//공지사항 있을떄
		
		%>
		<table border="1" width="500">
		<tr>
			<th>번호</th><th>제목</th><th>내용</th><th>작성일</th>
		</tr>
		<c:forEach var="voSe2" items="<%=list33 %>" >
		<tr>
			<td>${voSe2.noti_no }</td>
			<td>${voSe2.title }</td>
			<td>${voSe2.content }</td>
			<td>${voSe2.regdate }</td>
	</tr>
		
		</c:forEach>

	</table>
	<div>
		<c:choose>
			<c:when test="${startPage>10 }">
				<a href="notisearch.do?pageNum2=${startPage-1 }&search=<%=vose2.getSearch() %>&keyword=<%=vose2.getKeyword()%>">[이전]</a>
			</c:when>
			<c:otherwise>
				[이전]
			</c:otherwise>
		</c:choose>
		<c:forEach var="i" begin="${startPage }" end="${endPage }">
		<c:choose>
			<c:when test="${pageNum==i }">
				<a href="notisearch.do?pageNum2=${i }&search=<%=vose2.getSearch() %>&keyword=<%=vose2.getKeyword()%>"><span style="color: red;">[${i }]</span></a>
		
		</c:when>
		<c:otherwise>
			<a href="notisearch.do?pageNum2=${i }&search=<%=vose2.getSearch() %>&keyword=<%=vose2.getKeyword()%>"><span style="color: #555;">[${i }]</span></a>
		</c:otherwise>
		
		</c:choose>
		
		</c:forEach>
		<c:choose>
			<c:when test="${endPage<pageCount }">
				<a href="notisearch.do?pageNum2=${endPage+1 }&search=<%=vose2.getSearch() %>&keyword=<%=vose2.getKeyword()%>">[다음]</a>
			
			</c:when>
			
		</c:choose>
	
	</div>
		<%
		} else{
			%>
			<h3>검색된 공지사항이 없습니다</h3>
			<%
		}
		}
		
	} else if(session.getAttribute("id")==null){//아이디 없이 검색할 때
		if(list33!=null){//공지사항 있을때
		%>
		
		<table border="1" width="500">
		<tr>
			<th>번호</th><th>제목</th><th>내용</th><th>작성일</th>
		</tr>
		<c:forEach var="voSe3" items="<%=list33 %>" >
		<tr>
			<td>${voSe3.noti_no }</td>
			<td>${voSe3.title }</td>
			<td>${voSe3.content }</td>
			<td>${voSe3.regdate }</td>
	</tr>
		
		</c:forEach>

	</table>
	<div>
		<c:choose>
			<c:when test="${startPage>10 }">
				<a href="notisearch.do?pageNum2=${startPage-1 }&search=<%=vose2.getSearch() %>&keyword=<%=vose2.getKeyword()%>">[이전]</a>
			</c:when>
			<c:otherwise>
				[이전]
			</c:otherwise>
		</c:choose>
		<c:forEach var="i" begin="${startPage }" end="${endPage }">
		<c:choose>
			<c:when test="${pageNum==i }">
				<a href="notisearch.do?pageNum2=${i }&search=<%=vose2.getSearch() %>&keyword=<%=vose2.getKeyword()%>"><span style="color: red;">[${i }]</span></a>
		
		</c:when>
		<c:otherwise>
			<a href="notisearch.do?pageNum2=${i }&search=<%=vose2.getSearch() %>&keyword=<%=vose2.getKeyword()%>"><span style="color: #555;">[${i }]</span></a>
		</c:otherwise>
		
		</c:choose>
		
		</c:forEach>
		<c:choose>
			<c:when test="${endPage<pageCount }">
				<a href="notisearch.do?pageNum2=${endPage+1 }&search=<%=vose2.getSearch() %>&keyword=<%=vose2.getKeyword()%>">[다음]</a>
			
			</c:when>
			
		</c:choose>
	
	</div>
	<%
		} else{
			%>
			<h3>검색된 공지사항이 없습니다</h3>
			<%
		}
	}
%>

<br><!-- 여기 작성 필요함 -->
<a href="noticeList.do">검색 전 페이지로</a>
<br>
<a href="layout.jsp">메인페이지로</a>
</body>
</html>