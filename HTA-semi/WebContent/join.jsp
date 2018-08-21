<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>insert.jsp</title>
</head>
<body align="center">
<h1> 회원 가입</h1>
<table border="1" width="350" align="center">
<form method="post" action="insert.do">  <!-- 가입버튼을 누르면 insert.do 서블릿으로 이동 -->
	
	<!--  가입시 lev 0, coin 0을 입력 -->
	<input type="hidden" name="lev" value="0">  
	<input type="hidden" name="coin" value="0">  
	
	<tr>
		<td>아이디</td>
		<td><input type="text" name="id"></td>
	</tr>
	<tr>
		<td>패스워드</td>
		<td><input type="password" name="pwd"></td>
	</tr>
	<tr>
		<td>패스워드 재입력</td>
		<td><input type="password" name="pwd2"></td>
	</tr>
	<tr>
		<td>이메일</td>
		<td><input type="text" name="email"></td>
	</tr>
	<tr>
		<td>전화번호</td>
		<td><input type="text" name="phone"></td>
	</tr>
	<tr>
		<td>주소</td>
		<td><input type="text" name="addr"></td>
	</tr>
	<td colspan="2" align="center">
			<input type="submit" value="가입">
			<input type="reset" value="취소">
	</td>
</form>
</table>
메인으로 이동...<a href="main.jsp">메인</a>

</body>
</html>