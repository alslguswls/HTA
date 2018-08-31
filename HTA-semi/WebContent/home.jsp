<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String context = request.getContextPath();
%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<style type="text/css">
		.bg-1 {background-color: #444;}
		#wrap{padding-top: 100px;}
		#icon div{width: 250px;height: 250px;margin: 10px;
			background-color: #ffff99;display: inline-block;}
		#icon label{font-size: 30px;color: #ddd}
		#icon img{width: 250px;height: 250px;
			padding: 50px;background-color: #ffff55;}
		#logo{width: 100px;height: 100px;margin-top: -60px;}
		#name{font-size: 100px;color: #ddd;display: inline;}
		#sname{font-size: 30px;color: #ddd;display: inline;}
	</style>
	<link rel="stylesheet" href="<%=context %>/css/bootstrap.min.css">
	<script src="<%=context %>/js/jquery.min.js"></script>
  	<script src="<%=context %>/js/bootstrap.min.js"></script>
</head>
<body class="container-fluid bg-1 text-center">
	<div id="wrap">
		<div>
			<img id="logo" src="images/logo.png">
			<label id="name">억!</label>
			<label id="sname">소리나는 옥</label>
			<label id="name">션</label>
		</div>
		<br>
		<div id="icon">
			<div onmouseover="over()" onmouseout="out()" class="img-rounded">
				<a href="layout.jsp?page=join.jsp&left=noticeLeft.jsp">
					<img src="images/reg.png" class="img-rounded">
				</a>
				<label>회원가입</label>
			</div>
			<div onmouseover="over()" onmouseout="out()" class="img-rounded">
				<a href="layout.jsp?page=login.jsp&left=noticeLeft.jsp">
					<img src="images/login.png" class="img-rounded">
				</a>
				<label>로그인</label>
			</div>
			<div onmouseover="over()" onmouseout="out()" class="img-rounded">
				<a href="layout.jsp?page=/boardList.do?mod=list">
					<img src="images/list.png" class="img-rounded">
				</a>
				<label>경매품 보기</label>
			</div>
		</div>
	</div>
</body>
</html>