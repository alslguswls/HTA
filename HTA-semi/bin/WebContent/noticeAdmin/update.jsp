<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	input:focus, textarea:focus {
	outline:none;
}
</style>
</head>
<body>
<%	//수정을 위한 인터넷창
	request.getAttribute("vo");
%>
<h2>수정사항</h2>
<form action="notiupdateResult.do" method="post" name="updatearticle">
	<table style="border: 1px solid; border-collapse: collapse; ">
		<tr style="border: 1px solid;">
		<th style="border: 1px solid;">
			번호
		</th>
			<th>
				<input type="text" value="${requestScope.noti_no }" name="noti_no"
					size="20" style="width: 100%; border: 0;"  readonly="readonly">
			</th>
			
		</tr>
	
		<tr style="border: 1px solid;">
		<td style="border: 1px solid;">
			제목
		</td>
			<td>
				<input type="text" value="${requestScope.title }" name="title"
					size="20" style="width: 100%; border: 0;">
			</td>
			
		</tr>
		
		<tr style="border: 1px solid;">
		<td style="border: 1px solid;">
			내용
		</td>
			<td>
				<textarea rows="10"  name="content"
					style="width: 100%; border: 0; resize: none;">
				${requestScope.content }
				</textarea>
				
			</td>
			
		</tr>
		<tr style="border: 1px solid;">
		<th style="border: 1px solid;">
			가입일
		</th>
			<th>
				<input type="text" value="${requestScope.regdate }" name="regdate"
					size="20" style="width: 100%; border: 0;" readonly="readonly">
			</th>
			
		</tr>
		
	</table>
	<br>
<input type="submit" value="수정">
</form>

</body>
</html>