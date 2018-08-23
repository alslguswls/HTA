<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript">
	function check() {
		var fm = document.newBoard;
		var title = document.getElementById("title").value;
		var cate = document.getElementById("cate").value;
		var price = document.getElementById("price").value;
		var sdate = document.getElementById("sdate").value;
		var hh = document.getElementById("hh").value;
		var mm = document.getElementById("mm").value;
		var comtent = document.getElementById("content").value;
		var thumbext = document.getElementById('orgfile').value; //파일을 추가한 input 박스의 값
		thumbext = thumbext.slice(thumbext.indexOf(".") + 1).toLowerCase(); //파일 확장자를 잘라내고, 비교를 위해 소문자로 만듭니다.
		var regNumber=/^[0-9]*$/;
		var money=price.replace(/[^0-9]/g,"");
		if (!title) {
			alert("제목을 입력해주세요");
			return false;
		} else if (!cate) {
			return false;
		} else if (!money) {
			alert("가격을 입력해 주세요");
			document.getElementById("price").value=money;
			return false;
		}else if(!regNumber.test(price)){
			alert("가격은 숫자만 입력 가능합니다.");
			document.getElementById("price").value=money;
			return false;
		}else if (!sdate) {
			alert("날짜를 선택해주세요");
			return false;
		} else if (!(hh) && !(mm)) {
			alert("시간을 선택해 주세요");
			return false;
		}else if (!content) {
			alert("내용이 없습니다.");
			return false;
		}else if (thumbext != "jpg" && thumbext != "png" &&  thumbext != "gif" &&  thumbext != "bmp") {
			alert("jpg,png,gif,bmp파일만 업로드 가능합니다.");
			document.getElementById('orgfile').value="";
			return false;
		}
		//fm.submit();
	}
</script>