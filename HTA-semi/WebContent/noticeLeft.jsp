<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function writeNext() {
	document.location.href="writeaddr.do";
}

</script>
</head>
<body>
<%
	String admin1 = (String)session.getAttribute("isAdmin"); 
	//String n = request.getParameter("n");
%>
<br><br>
<%
	if(session.getAttribute("id")!=null){
%>
<a href="layout.jsp">경매품 보러가기</a>
<br>
 <%
 	
 	if(admin1.equals("1")){
 		%>
 		<button type="button" value="글 작성" onclick="writeNext()" class="btn btn-default"></button>    
 		
 		<%
 	} else{
 		
 	}
	} else{
		
		%>
		<a href="layout.jsp">경매품 보러가기</a>
		<%
	}
 %>

</body>
</html>