<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
//운영자가 아니라서 경고메시지 뜨게 함
	function closeWindow() {
		self.close();
		
	}
</script>
</head>
<body >
	  <h3>관리자가 아니면 못합니다.</h3>
	  <input type="button" value="닫기" onclick="closeWindow()">
	 <!-- <a href="javascript:closeWindow()">닫기</a>-->


</body>
</html>