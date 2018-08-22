<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<jsp:include page="/script/common.jsp"></jsp:include>
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