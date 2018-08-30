<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<%
		String context = application.getContextPath();
	%>
	<style type="text/css">
		#out {width: 1000px;height: 500px;background-color: yellow;text-align: center;}
		#in {display: inline-block;}
		img{width: 250px;height: 250px;margin: 20px;}
	</style>
	<link rel="stylesheet" type="text/css" href="<%=context %>/css/bootstrap-theme.min.css">
</head>
<body>
	<div id="out">
		<div id="in">
			<img src="images/reg.png">
			<img src="images/login.png">
			<img src="images/list.png">
		</div>
	</div>
</body>
</html>