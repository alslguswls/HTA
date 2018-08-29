<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- 
2018-08-28	cate -> left 로 수정(윤우현)
 -->
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<jsp:include page="/script/common.jsp"></jsp:include>
	<jsp:include page="/script/cate_ck.jsp"></jsp:include>
	<%
		String context = application.getContextPath();
	%>
	<link rel="stylesheet" type="text/css" href="<%=context %>/css/common.css">
</head>
	<%
		String page1 = request.getParameter("page");
		if(page1 == null) page1 = "/boardList.do?mod=list";
		String left1 = request.getParameter("left");
		if(left1 == null) left1 = "/Category.do?mod=leftList";
		//달력 스크립트 글게시시 사용
		if(page1.equals("/board/newBoard.jsp")){
	%>
		<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />  
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>  
		<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script> 
		<script src="<%=context %>/script/datepicker.js" ></script>
		<jsp:include page="/script/newBoard.jsp"></jsp:include>
	<% 
		}else if(page1.equals("detail.jsp")){
	%>
			<jsp:include page="/script/detailScript.jsp"></jsp:include>
			<body onload="commList();checkStart()">
			
	<%
		}else if(page1.equals("enter.jsp")){
	%>
			<body onload="timer()">
			
	<%
		}else{
	%>
			<body>
	<%
		}
	%>
	<div id="wrap">
		<div id="header">
			<jsp:include page="header.jsp"></jsp:include>
		</div>
		<div id="category">
			<jsp:include page="<%=left1 %>"></jsp:include>
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