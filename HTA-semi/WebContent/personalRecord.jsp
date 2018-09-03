<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"   %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	//개인정보창
	
	
</script>
</head>
<body >
<h1>개인 기록창</h1><br>

<h3>참여 예약목록</h3>
<br>
<table class="table table-striped">
	<tr>
		<th class="text-center col-sm-1">게시글 번호</th><th class="text-center col-sm-1">제목</th>	
	</tr>
	<c:forEach var="reservo" items="${listReser }">
		<tr>
			<td>${reservo.bnum }</td>
			<td><a href="detail.do?cmd=detail&bnum=${reservo.bnum}">${reservo.title }</a></td>
		</tr>
	</c:forEach>
</table>
<div>
		<c:choose>
			<c:when test="${startPage>10 }">
				<a href="perReser.do?pageNum=${startPage-1 }&id=${sessionScope.id}">[이전]</a>
			</c:when>
			<c:otherwise>
				[이전]
			</c:otherwise>
		</c:choose>
		<c:forEach var="i" begin="${startPage }" end="${endPage }">
		<c:choose>
			<c:when test="${pageNum==i }">
				<a href="perReser.do?pageNum=${i }&id=${sessionScope.id}"><span style="color: red;">[${i }]</span></a>
		
		</c:when>
		<c:otherwise>
			<a href="perReser.do?pageNum=${i }&id=${sessionScope.id}"><span style="color: #555;">[${i }]</span></a>
		</c:otherwise>
		
		</c:choose>
		
		</c:forEach>
		<c:choose>
			<c:when test="${endPage<pageCount }">
				<a href="perReser.do?pageNum=${endPage+1 }&id=${sessionScope.id}">[다음]</a>
			
			</c:when>
			
		</c:choose>
		
		
	</div>

<br>


</body>
</html>