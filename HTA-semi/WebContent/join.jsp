<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>insert.jsp</title>
<script type="text/javascript">
	var xhr=null;
	function idcheck(){
		var id=document.getElementById("id").value;
		if(id==""){
			document.getElementById("idcheck").innerHTML="";
			return; // 메소드 끝내기
		}
		xhr=new XMLHttpRequest();
		xhr.onreadystatechange=callback;
		xhr.open('get','idcheck.jsp?id='+id,true);
		xhr.send();
	}
	
	function callback(){ // 콜백 메소드
		if(xhr.readyState==4 && xhr.status==200){
			var xml=xhr.responseXML;
			var using=xml.getElementsByTagName("using")[0];
			var u=using.firstChild.nodeValue;
			var span=document.getElementById("idcheck");
			if(eval(u)==true){
				span.innerHTML="사용할 수 없는 아이디 입니다.";
			}else{
				span.innerHTML="사용 가능한 아이디 입니다.";
			}
		}
	}
	
	// 입력칸 입력여부 체크
	function check(){
		var id=document.getElementById("id");
		var pwd=document.getElementById("pwd");
		var email=document.getElementById("email");
		var phone=document.getElementById("phone");
		var addr=document.getElementById("addr");
		if(id.value==""){
			alert("아이디를 입력하세요");
			return false;
		}else if(pwd.value==""){
			alert("패스워드를 입력하세요");
			return false;
		}else if(email.value==""){
			alert("이메일을 입력하세요");
			return false;
		}else if(phone.value==""){
			alert("전화번호를 입력하세요");
			return false;
		}else if(addr.value==""){
			alert("주소를 입력하세요");
			return false;
		}
	}
	

</script>
</head>
<body align="center">
<h1> 회원 가입</h1>

<table border="1" width="300" align="center">
<form method="post" action="insert.do" onsubmit="return check()">  <!-- 가입버튼을 누르면 insert.do 서블릿으로 이동 -->
	<!--  가입시 lev 0, coin 0을 입력 -->
	<input type="hidden" name="lev" value="0">  
	<input type="hidden" name="coin" value="0">  
	
	<tr>
		<td>아이디</td>
		<td>
			<input type="text" name="id" id="id" onkeyup="idcheck()">
			<!-- 아이디 중복검사 기능 -->
			<br>
			<span id="idcheck" style="color:red;font-size:12px"></span>
		</td>
	</tr>
	<tr>
		<td>패스워드</td>
		<td><input type="password" id="pwd" name="pwd"></td>
	</tr>
	<tr>
		<td>패스워드 확인</td>
		<td>
			<input type="password" name="pwd2">
			<br>
			<span id="pwdcheck" style="color:red;font-size:12px"></span>
		</td>
	</tr>
	<tr>
		<td>이메일</td>
		<td><input type="text" id="email" name="email"></td>
	</tr>
	<tr>
		<td>전화번호</td>
		<td><input type="text" id="phone" name="phone"></td>
	</tr>
	<tr>
		<td>주소</td>
		<td><input type="text" id="addr" name="addr"></td>
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