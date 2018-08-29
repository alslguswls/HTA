<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
			var xml = cateXhr.responseXML;
			var result = xml.getElementsByTagName("check")[0];
			var row = result.firstChild.nodeValue;
			if (eval(row) == true) {
				document.cateForm.submit();
			} else {
				alert("이미사용중인 카테고리입니다.");
			}
		}
	}
</script>