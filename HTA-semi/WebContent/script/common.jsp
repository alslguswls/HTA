<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
	var resvXhr = null;
	function resv() {
		var id = "<%=session.getAttribute("id") %>";
		resvXhr = new XMLHttpRequest();
		resvXhr.onreadystatechange = resvCallback;
		resvXhr.open('get','resv.do?bnum=${vo.bnum }&id='+id,true);
		resvXhr.send();
	}
	function resvCallback() {
		if(resvXhr.readyState==4 && resvXhr.status==200){
			var resvBtn = document.getElementById("resvBtn");
			var txt = resvXhr.responseText;
			var json = JSON.parse(txt);
			if(json.result){
				resvBtn.disabled = "disabled";
				resvBtn.value = "예약완료"
			}else{
				alert("오류로 인해 예약이 실패하였습니다.");
			}
		}
	}
	
	var commXhr = null;
	function comm() {
		commXhr = new XMLHttpRequest();
		commXhr.onreadystatechange = commCallback;
		commXhr.open('post','comm.do?cmd=insert',true);
		commXhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		var bnum = ${vo.bnum };
		var id = "<%=session.getAttribute("id") %>";
		var comm = document.getElementById("comm").value;
		var param = "bnum=" + bnum + "&id=" + id + "&comm=" + comm;
		xhr.send(param);
	}
	function commCallback() {
		if(resvXhr.readyState==4 && resvXhr.status==200){
			var list = document.getElementById("list");
			var txt = resvXhr.responseText;
			var json = JSON.parse(txt);
		}
	}
	
	var listXhr = null;
	function commList() {
		listXhr = new XMLHttpRequest();
		listXhr.onreadystatechange = listCallback;
		listXhr.open('get','comm.do?cmd=list',true);
		listXhr.send();
	}
	function listCallback() {
		if(resvXhr.readyState==4 && resvXhr.status==200){
			var list = document.getElementById("list");
			var txt = resvXhr.responseText;
			var json = JSON.parse(txt);
			for(var i = 0;i < json.list.length;i++){
				list.innerHTML += "<label>" + json.list[i].id +
							json.list[i].comments + "</label>";
			}
		}
	}
</script>