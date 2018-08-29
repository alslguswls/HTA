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
	window.onload = function () {
		document.location.href="perReser.do";
		bid();
		deal();
	}
	function bid(){
		document.location.href="joinDeal.do";
	}
	function deal(){
		document.location.href="";
	}
</script>
</head>
<body>
<h1>개인 기록창</h1><br>

<h3>참여 예약목록</h3>
<br>
<table border="1" width="500">
	<tr>
		<th>예약자 번호</th><th>게시글 번호</th>	
	</tr>
	<c:forEach var="reservo" items="${listReser }">
		<tr>
			<td>${reservo.vnum }</td>
			<td>${reservo.bnum }</td>
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
<h3>낙찰물품 목록</h3>
<br>
<table border="1" width="500">
	<tr>
		<th>경매 번호</th><th>경매게시글 번호</th><th>최고가</th>	
	</tr>
	<c:forEach var="dealvo" items="${listDeal }">
		<tr>
			<td>${dealvo.mp_no }</td>
			<td>${dealvo.bnum }</td>
			<td>${dealvo.maxprice }</td>
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
<br>
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
			<a href="joinChat.do?pageNum=${startPage-1 }">[이전]</a>
		</c:when>
		<c:otherwise>
			[이전]
		</c:otherwise>
	</c:choose>
	<c:forEach var="i " begin="${startPage }" end="${endPage }">
		<c:choose>
		<c:when test="${pageNum==i }">
			<a href="joinChat.do?pageNum=${i }"><span>[${i}]</span> </a>
		</c:when>
		</c:choose>
		
	</c:forEach>
</div>

</body>
</html>