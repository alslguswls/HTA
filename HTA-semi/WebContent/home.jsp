<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<style type="text/css">
		.bg-1 {background-color: #444444;}
		#wrap{padding-top: 100px;}
		#icon div{width: 250px;height: 250px;margin: 10px;
			background-color: #ffff99;display: inline-block;}
		#icon label{font-size: 30px;color: #dddddd}
		img{width: 250px;height: 250px;
			padding: 50px;background-color: #ffff99;}
		input{width: 250px;height: 250px;margin: 10px;
			padding: 50px;background-color: #ffff99;}
		#name{font-size: 100px;color: #dddddd;}
	</style>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body class="container-fluid bg-1 text-center">
	<div id="wrap">
		<div>
			<label id="name">경매사이트</label>
		</div>
		<br>
		<div id="icon">
			<div id="reg" onmouseover="over()" onmouseout="out()" class="img-rounded">
				<a href="#">
					<img id="regimg" src="images/reg.png" class="img-rounded">
				</a>
				<label>회원가입</label>
			</div>
			<div onmouseover="over()" onmouseout="out()" class="img-rounded">
				<a href="#">
					<img id="loginimg" src="images/login.png" class="img-rounded">
				</a>
				<label>로그인</label>
			</div>
			<div onmouseover="over()" onmouseout="out()" class="img-rounded">
				<a href="#">
					<img id="listimg" src="images/list.png" class="img-rounded">
				</a>
				<label>경매품 보기</label>
			</div>
		</div>
	</div>
</body>
</html>