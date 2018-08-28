<%@page import="vo.notice.NoticeVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
//삭제전 1번더 물어보는 팝업창
<%		
		int noti = Integer.parseInt(request.getParameter("noti_no"));
		
		
%>
function turn() {
	var ans= confirm("정말로 삭제하겠습니까?");
	//window.open("deleteWarning.jsp","_blank","width=200,height=200");
	if (ans) {
		
		document.location.href="notidelete.do?noti_no=<%=noti %>";
		
	} else{
		document.location.href="noticeList.do";
	}
}

</script>
</head>
<body onload="turn()">

</body>
</html>