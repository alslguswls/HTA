<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%	//공지사항 작성 오류일 경우
	if(request.getAttribute("errMsg")!=null){
		%>
		${requestScope.errMsg}
		<a href="admin/write.jsp">뒤로가기</a>
		<%
	} else if(request.getAttribute("errMsg2")!=null){
		//공지사항 수정 오류일 경우
		%>
		${requestScope.errMsg2}
		<a href="admin/update.jsp">뒤로가기</a>
		<%
	} else if(request.getAttribute("errMsg3")!=null){
		%>
		${requestScope.errMsg3}
		<a href="notice.jsp">뒤로가기</a>
		<%
	}

%>


</body>
</html>