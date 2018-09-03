<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String context = request.getContextPath();
%>
<script type="text/javascript">
	var cateXhr;
	function name_ck() {
		var cate_n = document.getElementById("cate_n").value;
		var cate = cate_n.replace(" ", "");
		if (cate == "") {
			return; // 메소드 끝내기
		}
		cateXhr = new XMLHttpRequest();
		cateXhr.onreadystatechange = name_rs;
		cateXhr.open('get', 'Category.do?mod=name_ck&cate_n=' + cate, true);
		cateXhr.send(); 
	}
 	function name_rs() { // 콜백 메소드
		if (cateXhr.readyState == 4 && cateXhr.status == 200) {
			var fm =document.cateForm;
			var xml = cateXhr.responseXML;
			var result = xml.getElementsByTagName("check")[0];
			var row = result.firstChild.nodeValue;
			if (eval(row) == true) {
				fm.submit();
			} else {
				alert("이미사용중인 카테고리입니다.");
			}
		}
	}
	
	//카테고리 수정 작업 xml ajax 처리 후 저장 성공시 창새로고침
	function cateInfo(no,str){
		var text = document.getElementById("cateText");
		text.innerHTML="카테고리 수정하기";
		var btn=document.getElementById("chButton").style;
		btn.display="none";
		var btn1=document.getElementById("modify").style;
		btn1.display="inline";
		var c_name= document.getElementById("cate_n");
		c_name.value=str;
		var btn = document.getElementById("cancle").style; 
		btn.display="inline";
		var cate= document.getElementById("cate");
		cate.value=no;
	}
	
	function cateCancle(){
		var text = document.getElementById("cateText");
		text.innerHTML="카테고리 추가하기";
		var btn=document.getElementById("chButton").style;
		btn.display="inline";
		var btn1=document.getElementById("modify").style;
		btn1.display="none";
		var c_name= document.getElementById("cate_n");
		var btn = document.getElementById("cancle").style;
		btn.display="none";
		c_name.value="";
		var cate= document.getElementById("cate");
		cate.value="";
	}
	var cateXhr1;
	function cateModify(){
		var fm=document.cateForm;
		fm.action="Category.do?mod=modify";
		var cate_n = document.getElementById("cate_n").value;
		var no = document.getElementById("cate").value;
		var cate = cate_n.replace(' ', '');
		if (cate == "") {
			return; // 메소드 끝내기
		}
		cateXhr1 = new XMLHttpRequest();
		cateXhr1.onreadystatechange = cateMCall;
		cateXhr1.open('get', 'Category.do?mod=update&cate_n=' +cate +'&cate='+no, true);
		cateXhr1.send(); 
	}
	
	function cateMCall(){
		if (cateXhr1.readyState == 4 && cateXhr1.status == 200) {
			var fm =document.cateForm;
			var xml = cateXhr1.responseXML;
			var result = xml.getElementsByTagName("check")[0];
			var row = result.firstChild.nodeValue;
			if (eval(row) == true) {
				alert("수정되었습니다.");
				location.href="Category.do?mod=list";
			} else {
				alert("이미사용중인 카테고리명입니다.");
			}
		}
	}
	
	function cateDelete(no){
		var ck = confirm(" 삭제 하시겠습니까? \n 삭제시 카테고리 게시글도 전부 삭제 됩니다.");
		if(ck==true){
			alert("삭제되었습니다.");
			location.href="Category.do?mod=delete&cate="+no;
		}
	}
	
	function selCate(val){
		location.href="<%=context%>/boardList.do?mod=list&admin=1&cate="+val;
	}
	
</script>