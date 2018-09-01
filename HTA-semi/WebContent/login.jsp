<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>login.jsp</title>
<style>table {margin:auto;text-align:center}</style>
<script type="text/javascript">
	function check(){
		//alert("나오냐");
		var id=document.f.id.value;
		//alert(id);
		var pwd=document.f.pwd.value;
		//alert(pwd);
		if(id==""){
			alert("아이디를 입력해주세요");
			return false;
			id.focus();
		}else if(pwd==""){
			alert("패스워드를 입력해주세요");
			return false;
			pwd.focus();
		}
		return true;
	}

</script>
</head>
<body align="center">
<%
	String errMsg = (String)request.getAttribute("errMsg");
 	if(errMsg==null){	// 필요없을것 처럼 보이지만 이부분 처리를 안해주면 'null' 이라는 문자가 표시됨
				errMsg=""; 
 	}
%>

<br>
<h1>로그인</h1>

<form name="f" class="form-inline" method="post" action="loginOk.jsp" onsubmit="return check()" align="center">
	<div style="color:red;font-size:12px"><%=errMsg %></div>
	<table align="center" class="table" border="3" style="width: 250px;height: 100px;">
		<tr>
			<td><label for="id">아이디</label></td>
			<td><input type="text" class="form-control" name="id" id="id"></td>
		</tr>
		<tr>
			<td><label for="pwd">패스워드</label></td>
			<td><input type="password" class="form-control" name="pwd" id="pwd"></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="submit" class="btn btn-warning btn-sm" value="로그인">
				<input type="reset" class="btn btn-warning btn-sm" value="취소">
			</td>
		</tr>
	
	</table>

</form>

</body>
</html>