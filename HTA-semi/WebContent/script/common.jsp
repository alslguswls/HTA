<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
	var resvXhr = null;
	function resv() {
		var id = "<%=session.getAttribute("id") %>";
		resvXhr = new XMLHttpRequest();
		resvXhr.onreadystatechange = resvCallback;
		resvXhr.open('get','resv.do?cmd=resv&bnum=${vo.bnum }&id='+id,true);
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
				alert("예약에 실패하였습니다.");
			}
		}
	}
	
	var commXhr = null;
	function comm1() {
		commXhr = new XMLHttpRequest();
		commXhr.onreadystatechange = commCallback;
		commXhr.open('post','comm.do?cmd=insert',true);
		commXhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		var bnum = ${vo.bnum };
		var id = "<%=session.getAttribute("id") %>";
		var comm = document.getElementById("comm");
		var param = "bnum=" + bnum + "&id=" + id + "&comm=" + comm.value;
		commXhr.send(param);
		comm.value = "";
	}
	function commCallback() {
		if(commXhr.readyState==4 && commXhr.status==200){
			var txt = commXhr.responseText;
			var json = JSON.parse(txt);
			if(json.result){
				commList();
			}else{
				alert("오류로 인해 댓글등록에 실패하였습니다.");
			}
		}
	}
	
	var ccommXhr = null;
	function ccomm(i) {
		ccommXhr = new XMLHttpRequest();
		ccommXhr.onreadystatechange = ccommCallback;
		ccommXhr.open('post','comm.do?cmd=insert',true);
		ccommXhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		var cnum = document.getElementsByName("form"+i)[0].cnum.value;
		var bnum = document.getElementsByName("form"+i)[0].bnum.value;
		var id = document.getElementsByName("form"+i)[0].id.value;
		var comm = document.getElementsByName("form"+i)[0].comm.value;
		var ref = document.getElementsByName("form"+i)[0].ref.value;
		var lev = document.getElementsByName("form"+i)[0].lev.value;
		var step = document.getElementsByName("form"+i)[0].step.value;
		var param = "cnum=" + cnum + "&bnum=" + bnum + "&id=" + id + 
			"&comm=" + comm + "&ref=" + ref + "&lev=" + lev + "&step=" + step;
		ccommXhr.send(param);
	}
	function ccommCallback() {
		if(ccommXhr.readyState==4 && ccommXhr.status==200){
			var txt = ccommXhr.responseText;
			var json = JSON.parse(txt);
			if(json.result){
				commList();
			}else{
				alert("오류로 인해 답글등록에 실패하였습니다.");
			}
		}
	}
	
	var listXhr = null;
	function commList(pageNum) {
		listXhr = new XMLHttpRequest();
		listXhr.onreadystatechange = listCallback;
		if(pageNum == undefined){
			listXhr.open('get','comm.do?cmd=list&bnum=${vo.bnum}',true);
		}else{
			listXhr.open('get','comm.do?cmd=list&bnum=${vo.bnum}&pageNum='+pageNum,true);
		}
		listXhr.send();
	}
	function listCallback() {
		if(listXhr.readyState==4 && listXhr.status==200){
			var list = document.getElementById("list");
			var txt = listXhr.responseText;
			var json = JSON.parse(txt);
			list.innerHTML = "";
			for(var i = 0;i < json.list.length;i++){
				for(var j = 0;j < json.list[i].lev;j++){
					list.innerHTML += "&nbsp;&nbsp;&nbsp;&nbsp;";
				}
				list.innerHTML += "<label>"+json.list[i].id+" : "+json.list[i].comments+"</label>&nbsp;"+
					"<input type=\"button\" value=\"답글\" onclick=\"addInput(" + i + ")\" id=\"btn" + i + "\"><br>"+
					"<form action=\"javascript:return false;\" method=\"post\" style=\"display: none;\" name=\"form"+i+"\" onsubmit=\"ccomm("+i+")\" >"+
						"<input type=\"hidden\" name=\"cnum\" value=\""+json.list[i].cnum+"\">"+
						"<input type=\"hidden\" name=\"bnum\" value=\""+json.list[i].bnum+"\">"+
						"<input type=\"hidden\" name=\"id\" value=\"<%=session.getAttribute("id") %>\">"+
						"<input type=\"hidden\" name=\"ref\" value=\""+json.list[i].ref+"\">"+
						"<input type=\"hidden\" name=\"lev\" value=\""+json.list[i].lev+"\">"+
						"<input type=\"hidden\" name=\"step\" value=\""+json.list[i].step+"\">"+
						"<input type=\"text\" name=\"comm\" size=\"20\">"+
						"<input type=\"submit\" value=\"입력\" >";
					"</form>";
			}
			
			if(json.startPage>10){
				list.innerHTML += "<a href=\"javascript:commList("+(json.startPage-1)+");\">[이전]</a>";
			}else{
				list.innerHTML += "[이전]";
			}
			for(var i = json.startPage;i <= json.endPage;i++){
				if(json.pageNum == i){
					list.innerHTML += "<a href=\"javascript:commList("+i+");\" style=\"color: red\">["+i+"]</a>";
				}else{
					list.innerHTML += "<a href=\"javascript:commList("+i+");\" style=\"color: gray\">["+i+"]</a>";
				}
			}
			if(json.endPage<json.pageCount){
				list.innerHTML += "<a href=\"javascript:commList("+(json.endPage+1)+");\">[다음]</a>";
			}else{
				list.innerHTML += "[다음]";
			}
		}
	}
	
	function addInput(i) {
		var form = document.getElementsByName("form"+i)[0];
		var btn = document.getElementById("btn"+i);
		btn.style.display = "none";
		form.style.display = "block";
	}
	
	var enterXhr = null;
	function enter() {
		var bnum = ${vo.bnum };
		var id = "<%=session.getAttribute("id") %>";
		enterXhr = new XMLHttpRequest();
		enterXhr.onreadystatechange = enterCallback;
		enterXhr.open('get','enter.do?cmd=resv&bnum='+bnum+'&id='+id,true);
		enterXhr.send();
	}
	function enterCallback() {
		if(enterXhr.readyState==4 && enterXhr.status==200){
			var txt = enterXhr.responseText;
			var json = JSON.parse(txt);
			if(json.resv){
				location.href = "layout.jsp?page=enter.jsp?bnum="+${vo.bnum };
			}else{
				alert("경매참여가 불가능합니다.");
			}
		}
	}
	
	var csXhr = null;
	function checkStart() {
		var bnum = ${vo.bnum };
		var id = "<%=session.getAttribute("id") %>";
		csXhr = new XMLHttpRequest();
		csXhr.onreadystatechange = checkStartCallback;
		csXhr.open('get','detail.do?cmd=check&bnum='+bnum+'&id='+id,true);
		csXhr.send();
	}
	function checkStartCallback() {
		if(csXhr.readyState==4 && csXhr.status==200){
			var enterBtn = document.getElementById("enterBtn");
			var txt = csXhr.responseText;
			var json = JSON.parse(txt);
			if(json.open){
				enterBtn.disabled = null;
			}
		}
	}
</script>

<a ></a>
















