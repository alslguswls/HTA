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
		if(errMsg==null){
				errMsg="";
	}
%>


<h1>로그인</h1>

<form name="f" method="post" action="loginOk.jsp" onsubmit="return check()" align="center">
	<table align="center" border="1" width="250" height="100"  >
		<div style="color:red;font-size:12px"><%=errMsg %></div>
		<tr>
			<td>아이디</td>
			<td><input type="text" name="id"></td>
		</tr>
		<tr>
			<td>패스워드</td>
			<td><input type="password" name="pwd"></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="submit" value="로그인">
				<input type="reset" value="취소">
			</td>
		</tr>
	
	</table>

</form>

</body>
</html>