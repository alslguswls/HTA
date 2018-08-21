<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<script type="text/javascript">
		var resvXhr = null;
		function resv() {
			var id = "<%=session.getAttribute("id") %>";
			resvXhr = new XMLHttpRequest();
			resvXhr.onreadystatechange = resvCallback;
			resvXhr.open('get','resv.do?bnum=${vo.bnum }&id='+id,true);
			resvXhr.send();
		}
		function resvCallback() {
			if(resvXhr.readyState==4 && resvXhr.status==200){
				var resvBtn = document.getElementById("resvBtn");
				var txt = resvXhr.responseText;
				var json = JSON.parse(txt);
				if(json.result){
					resvBtn.disabled = "disabled";
					resvBtn.value = "예약완료"
				}else{
					alert("오류로 인해 예약이 실패하였습니다.");
				}
			}
		}
	</script>
	<%
		String context = application.getContextPath();
	%>
	<link rel="stylesheet" type="text/css" href="<%=context %>/css/common.css">
</head>
<body>
	<%
		session.setAttribute("id", "테스터1");
		String page1 = request.getParameter("page");
		if(page1 == null) page1 = "main.jsp";
	%>
	<div id="wrap">
		<div id="header">
			<jsp:include page="header.jsp"></jsp:include>
		</div>
		<div id="category">
			<jsp:include page="/board/category.jsp"></jsp:include>
		</div>
		<div id="content">
			<jsp:include page="<%=page1 %>"></jsp:include>
		</div>
		<div id="footer">
			<jsp:include page="footer.jsp"></jsp:include>
		</div>
	</div>
</body>
</html>