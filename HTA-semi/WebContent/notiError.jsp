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
		
		<h3>${requestScope.errMsg}</h3>
		<br>
		<br>
		<a href="layout.jsp?page=write.jsp">뒤로가기</a>
		
		<%
	} else if(request.getAttribute("errMsg2")!=null){
		//공지사항 수정 오류일 경우
		%>
		
		<h3>${requestScope.errMsg2}</h3>
		<br>
		<br>
		<a href="layout.jsp?page=update.jsp">뒤로가기</a>
		
		<%
	} else if(request.getAttribute("errMsg3")!=null){
		%>
		
		<h3>${requestScope.errMsg3}</h3>
		<br>
		<br>
		<a href="layout.jsp?page=notice.jsp">뒤로가기</a>
		
		<%
	}

%>


</body>
</html>