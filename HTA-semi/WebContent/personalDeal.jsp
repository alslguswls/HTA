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
<body>
<h3>낙찰물품 목록</h3>
<br>
<table border="1" width="500">
	<tr>
		<th>경매시작 순번</th><th>경매게시글 번호</th><th>최고가</th><th>종료시간</th>	
	</tr>
	<c:forEach var="dealvo" items="${listDeal }">
		<tr>
			<td>${dealvo.rnum }</td>
			<td><a href="detail.do?cmd=detail&bnum=${dealvo.bnum}">${dealvo.bnum }</a></td>
			<td>${dealvo.price }</td>
			<td>${dealvo.endtime }</td>
		</tr>
	</c:forEach>
</table>
<div>
		<c:choose>
			<c:when test="${startPage>10 }">
				<a href="joinDeal.do?pageNum=${startPage-1 }&id=${sessionScope.id}">[이전]</a>
			</c:when>
			<c:otherwise>
				[이전]
			</c:otherwise>
		</c:choose>
		<c:forEach var="i" begin="${startPage }" end="${endPage }">
		<c:choose>
			<c:when test="${pageNum==i }">
				<a href="joinDeal.do?pageNum=${i }&id=${sessionScope.id}"><span style="color: red;">[${i }]</span></a>
		
		</c:when>
		<c:otherwise>
			<a href="joinDeal.do?pageNum=${i }&id=${sessionScope.id}"><span style="color: #555;">[${i }]</span></a>
		</c:otherwise>
		
		</c:choose>
		
		</c:forEach>
		<c:choose>
			<c:when test="${endPage<pageCount }">
				<a href="joinDeal.do?pageNum=${endPage+1 }&id=${sessionScope.id}">[다음]</a>
			
			</c:when>
			
		</c:choose>
		
		
	</div>


</body>
</html>