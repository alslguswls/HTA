<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"   %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">

</script>
</head>
<body >
	<h3>경매 출품내역 확인</h3>
<br>
<table border="1" width="500">
	<tr>
		<th>카테고리</th><th>제목</th><th>내용</th><th>시작가</th><th>등록일</th>
	</tr>
	<c:forEach var="chatvo" items="${listChat }">
		<tr>
			<td>${chatvo.cate }</td>
			<td>${chatvo.title }</td>
			<td><a href="detail.do?cmd=detail&bnum=${chatvo.bnum}">${chatvo.content }</a></td>
			<td>${chatvo.startprice }</td>
			<td>${chatvo.regdate }</td>
		</tr>
	</c:forEach>
</table>
<div>
	<c:choose>
		<c:when test="${startPage>10 }">
			<a href="joinchat.do?pageNum=${startPage-1 }">[이전]</a>
		</c:when>
		<c:otherwise>
			[이전]
		</c:otherwise>
	</c:choose>
	<c:forEach var="i" begin="${startPage }" end="${endPage }">
		<c:choose>
		<c:when test="${pageNum==i }">
			<a href="joinchat.do?pageNum=${i }"><span style="color: red;">[${i}]</span></a>
		</c:when>
		<c:otherwise>
			<a href="joinchat.do?pageNum=${i }"><span style="color: #555;">[${i}]</span></a>
		</c:otherwise>
		</c:choose>
		
	</c:forEach>
	<c:choose>
		<c:when test="${endPage<pageCount }">
			<a href="joinchat.do?pageNum=${endPage+1 }">[다음]</a>
		</c:when>
		<c:otherwise>
		
		</c:otherwise>
	</c:choose>
	
</div>
	
</body>
</html>