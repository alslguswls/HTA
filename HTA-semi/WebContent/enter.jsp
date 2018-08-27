<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	var chatXhr = null;
	function chat() {
		chatXhr = new XMLHttpRequest();
		chatXhr.onreadystatechange = chatCallback;
		chatXhr.open('post','enter.do?cmd=chat',true);
		chatXhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		var msg = document.getElementById("msg").value;
		var param = "bnum="+${param.bnum }+"&msg="+msg;
		chatXhr.send(param);
	}
	function chatCallback() {
		if(chatXhr.readyState==4 && chatXhr.status==200){
			var msg = document.getElementById("msg");
			var txt = chatXhr.responseText;
			var json = JSON.parse(txt);
			if(json.msg != "success"){
				alert(json.msg);
			}
			msg.value="";
		}
	}
	
	var timeXhr = null;
	function timer() {
		timeXhr = new XMLHttpRequest();
		timeXhr.onreadystatechange = timerCallback;
		timeXhr.open('get','enter.do?cmd=timer&bnum='+${param.bnum },true);
		timeXhr.send();
	}
	function timerCallback() {
		if(timeXhr.readyState==4 && timeXhr.status==200){
			var txt = timeXhr.responseText;
			var json = JSON.parse(txt);
			var time = document.getElementById("time")
			var id = document.getElementById("id")
			var maxPrice = document.getElementById("maxPrice")
			time.innerHTML = json.time;
			id.innerHTML = json.id;
			maxPrice.innerHTML = json.maxPrice;
		}
	}
	setInterval(timer, 1000);
	
	var roadXhr = null;
	function road() {
		roadXhr = new XMLHttpRequest();
		roadXhr.onreadystatechange = roadCallback;
		roadXhr.open('get','enter.do?cmd=road&bnum='+${param.bnum },true);
		roadXhr.send();
	}
	function roadCallback() {
		if(roadXhr.readyState==4 && roadXhr.status==200){
			var area = document.getElementById("area");
			var chatlog = document.getElementById("chatlog");
			var txt = roadXhr.responseText;
			var json = JSON.parse(txt);
			if(json.msg != undefined){
				alert(json.msg);
			}else{
				var html = "";
				for(var i = 0;i < json.list.length;i++){
					html += json.list[i].id+" / "+json.list[i].str+"\r\n"
				}
				chatlog.value = html;
				chatlog.scrollTop = chatlog.scrollHeight;
			}
		}
	}
	setInterval(road, 500);
	
	var callXhr = null;
	function priceCall() {
		alert(${delay });
		var price = document.getElementById("price").value;
		callXhr = new XMLHttpRequest();
		callXhr.onreadystatechange = callCallback;
		callXhr.open('get','enter.do?cmd=call&bnum='+${param.bnum }+'&price='+price,true);
		callXhr.send();
	}
	function callCallback() {
		if(callXhr.readyState==4 && callXhr.status==200){
			var txt = callXhr.responseText;
			var json = JSON.parse(txt);
			if(json.msg != "success"){
				alert(json.msg);
			}
		}
	}
</script>
</head>
<body>



<table border='1'>
	<tr>
		<th>최고호가</th>
		<td id="maxPrice">0</td>
		<th>아이디</th>
		<td id="id">id</td>
		<th>남은시간</th>
		<td id="time">0:00</td>
	</tr>
</table>
<div style="background-color: yellow;width: 800px;height: 500px;" id="area">
<textarea rows="34" cols="60" id="chatlog" ></textarea>
</div>
<form action="javascript:return false;"onsubmit="chat()">
<input type="text" size="50" id="msg">
<input type="submit" value="입력">
</form>
<form action="javascript:return false;"onsubmit="priceCall()">
<input type="text" size="20" id="price">
<input type="submit" value="호가하기">
</form>





</body>
</html>