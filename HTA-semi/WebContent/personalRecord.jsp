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
		document.location.href="";
		bid();
		deal();
	}
	function bid(){
		document.location.href="";
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
	<c:forEach var="reservo" items="${reserList }">
		<tr>
			<td>${reserList.vnum }</td>
			<td>${reserList.bnum }</td>
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
<h3>낙찰물품 목록</h3>
<div id = "personalBid">

</div>
<br>
<h3>경매 참여 기록 확인</h3>
<div id="personalDeal">

</div>
</body>
</html>