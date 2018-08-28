<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String context = (String) request.getContextPath();
%>
<script type="text/javascript">
	function check(mod) {
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
		var regNumber=/^[0-9]*$/;//숫자정규표현식
		var money=price.replace(/[^0-9]/g,"");//스페이스 공백제거
		var nowDate = new Date();
		//날짜비교 현재 시간 이후로 등록 가능
		var year = nowDate.getFullYear();
		var month = nowDate.getMonth();
		month =(++month < 10)? month = "0" +month : month;
		var day = nowDate.getDate();
		day =(day < 10)? day = "0" +day : day;//10미만 앞자리 0붙여주기 
		var hours = nowDate.getHours();
		var minutes=nowDate.getMinutes();
		var time = year+""+month+""+day+""+ hours+""+minutes;
		var rowDate = sdate+hh+mm;
		time*=1;//숫자로 변경
		rowDate*=1;
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
		}else if(time >=rowDate){
			alert("잘못된 시간 설정입니다.");
			return false;
		}else if (!content) {
			alert("내용이 없습니다.");
			return false;
		}else if(thumbext){
			if (thumbext != "jpg" && thumbext != "png" &&  thumbext != "gif" &&  thumbext != "bmp") {
			alert("jpg,png,gif,bmp파일만 업로드 가능합니다.");
			document.getElementById('orgfile').value="";
			return false;
			}
		}
		fm.action = "<%=context%>/insertBoard.do?mod="+mod;
		fm.submit();
	}
</script>