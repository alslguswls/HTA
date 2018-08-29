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
	<h3>경매 참여 기록 확인</h3>
<br>
<table>
	<tr>
		<th>채팅번호</th><th>경매창 번호</th><th>내용</th>
	</tr>
	<c:forEach var="chatvo" items="${listChat }">
		<tr>
			<td>${chatvo.chat_no }</td>
			<td>${chatvo.bnum }</td>
			<td>${chatvo.str }</td>
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