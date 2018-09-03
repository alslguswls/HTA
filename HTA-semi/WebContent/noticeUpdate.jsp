<%@page import="vo.notice.NoticeVO"%>
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
	NoticeVO vo3 = (NoticeVO)request.getAttribute("vo2");
	//System.out.println(vo3);
%>
<h2>수정사항</h2>
<form action="notiupdateResult.do" method="post" name="updatearticle">
	<table class="table table-hover" style="width: 700px; margin: 50px auto 0px auto;">
		<tr >
		<th >
			번호
		</th>
			<th class="form-group">
				<input type="text" class="form-control" value="<%=vo3.getNoti_no() %>" name="noti_no"
					size="20" style="width: 100%; border: 0;"  readonly="readonly">
			</th>
			
		</tr>
	
		<tr >
		<td >
			제목
		</td>
			<td class="form-group">
				<input type="text" class="form-control" value="<%=vo3.getTitle() %>" name="title"
					size="20" style="width: 100%; border: 0;">
			</td>
			
		</tr>
		
		<tr >
		<td >
			내용
		</td>
			<td class="form-group">
				<textarea rows="10" class="form-control col-sm-5"  name="content"
					style="width: 100%; border: 0; resize: none;">
				<%=vo3.getContent() %>
				</textarea>
				
			</td>
			
		</tr>
		<tr >
		<th >
			등록일
		</th>
			<th class="form-group">
				<input type="text" class="form-control" value="<%=vo3.getRegdate() %>" name="regdate"
					size="20" style="width: 100%; border: 0;" readonly="readonly">
			</th>
			
		</tr>
		
	</table>
	<br>
<input type="submit" class="btn " value="수정">
</form>

</body>
</html>