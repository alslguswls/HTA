<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- 
작업자:윤우현
 -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>join.jsp</title>
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
	
	var xhr2=null;
	function pwdcheck(){
		var pwd=document.f.pwd.value;
		var pwd2=document.f.pwd2.value;
		if(pwd=="" || pwd2==""){
			document.getElementById("pwdcheck").innerHTML="";
			return; // 메소드 끝내기
		}
		xhr2=new XMLHttpRequest();
		xhr2.onreadystatechange=pwd_callback;
		xhr2.open('get','pwdcheck.jsp?pwd='+pwd + '&pwd2='+ pwd2,true);
		xhr2.send();
	}
	
	function pwd_callback(){ // 패스워드 체크 콜백 메소드
		if(xhr2.readyState==4 && xhr2.status==200){
			var xml=xhr2.responseXML;
			var same=xml.getElementsByTagName("same")[0];
			var s=same.firstChild.nodeValue;
			var span=document.getElementById("pwdcheck");
			if(eval(s)==true){ // 패스워드 일치
				span.innerHTML="패스워드가 일치 합니다.";
			}else{
				span.innerHTML="패스워드가 일치하지 않습니다";
			}
		}
	}
	
	// 입력칸 입력여부 체크
	function check(){
		var id=document.f.id.value;
		var pwd=document.f.pwd.value;
		var pwd2=document.f.pwd2.value;
		var email=document.f.email.value;
		var phone=document.f.phone.value;
		var addr=document.f.addr.value;
		if(id==""){
			alert("아이디를 입력하세요");
			return false;
		}else if(pwd==""){
			alert("패스워드를 입력하세요");
			return false;
		}else if(pwd != pwd2){
			alert("패스워드를 확인하세요");
			return false;
			pwd.focus();
		}else if(email==""){
			alert("이메일을 입력하세요");
			return false;
		}else if(phone==""){
			alert("전화번호를 입력하세요");
			return false;
		}else if(addr==""){
			alert("주소를 입력하세요");
			return false;
		}
		alert("가입을 축하합니다")
		
	}
	

</script>
</head>
<body align="center">
<br>
<h1> 회원 가입</h1>
<form name="f" class="form-inline" method="post" action="membeInsert.do" onsubmit="return check()" >  <!-- 가입버튼을 누르면 membeInsert.do 서블릿으로 이동 -->
<table border="3" class="table" style="margin:auto;text-align:center;width: 400px;">
	<!--  가입시 lev 0, coin 0을 입력하는 기능. 미사용
	<input type="hidden" name="lev" value="0">  
	<input type="hidden" name="coin" value="0">  
	-->
	
	<tr>
		<td><label for="id">아이디</label></td>
		<td>
			<input type="text" class="form-control" name="id" id="id" onkeyup="idcheck()">
			<!-- 아이디 중복검사 기능 -->
			<div id="idcheck" style="color:red;font-size:12px"></div>
		</td>
	</tr>
	<tr>
		<td><label for="pwd">패스워드</label></td>
		<td><input type="password" class="form-control" id="pwd" name="pwd"></td>
	</tr>
	<tr>
		<td><label for="pwd2">패스워드 확인</label></td>
		<td>
			<input type="password" class="form-control" name="pwd2" id="pwd2" onkeyup="pwdcheck()">
			<div id="pwdcheck" style="color:red;font-size:12px"></div>
		</td>
	</tr>
	<tr>
		<td><label for="email">이메일</label></td>
		<td><input type="text" class="form-control" id="email" name="email"></td>
	</tr>
	<tr>
		<td><label for="phone">전화번호</label></td>
		<td><input type="text" class="form-control" id="phone" name="phone"></td>
	</tr>
	<tr>
		<td><label for="addr">주소</label></td>
		<td><input type="text" class="form-control" id="addr" name="addr"></td>
	</tr>
	<tr>
		<td colspan="2" align="center">
				<input type="submit" class="btn btn-warning btn-sm" value="가입">
				<input type="reset" class="btn btn-warning btn-sm" value="취소">
		</td>
	</tr>
</table>
</form>
메인으로 이동...<a href="main.jsp">메인</a>
회원목록...<a href="memberList.do">이동</a>

</body>
</html>