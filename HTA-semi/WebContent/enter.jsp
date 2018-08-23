<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function chat() {
		alert("ASD");
	}
	
	var timeXhr = null;
	function timer() {
		timeXhr = new XMLHttpRequest();
		timeXhr.onreadystatechange = timerCallback;
		timeXhr.open('get','enter.do?cmd=timer',true);
		timeXhr.send();
	}
	function timerCallback() {
		if(timeXhr.readyState==4 && timeXhr.status==200){
			var txt = timeXhr.responseText;
			var json = JSON.parse(txt);
			var time = document.getElementById("time")
			time.innerHTML = json.time;
		}
	}
	setInterval(timer, 1000);
	
	function priceCall() {
		
	}
</script>
</head>
<body>



<table border="1">
	<tr>
		<th>최고호가</th>
		<td id="maxPrice">0</td>
		<th>아이디</th>
		<td id="id">id</td>
		<th>남은시간</th>
		<td id="time">0:00</td>
	</tr>
</table>
<div style="background-color: yellow;width: 800px;height: 500px;">
</div>
<form action="javascript:return false;"onsubmit="chat()">
<input type="text" size="50" id="msg">
<input type="submit" value="입력">
</form>
<input type="button" value="호가하기" onclick="priceCall()">





</body>
</html>