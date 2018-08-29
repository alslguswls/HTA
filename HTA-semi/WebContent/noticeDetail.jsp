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
	NoticeVO vode = (NoticeVO)request.getAttribute("vodetail");
	//System.out.println(vo3);
	String idde = null;
	
%>
<script type="text/javascript">
	function deleteSomething(){
		document.location.href="deleteWarning.jsp?noti_no=<%=vode.getNoti_no()%>";
	}
</script>
<%
		if(session.getAttribute("id")!=null){
			//idde = (String)session.getAttribute("id");
			String admin1 = (String)session.getAttribute("isAdmin"); 
			System.out.print(admin1);
			if(admin1.equals("1")){
			%>


			
<h2>세부사항</h2>
<form action="notiupdate.do" method="post" name="updatearticle">
	<table style="border: 1px solid; border-collapse: collapse; ">
		<tr style="border: 1px solid;">
		<th style="border: 1px solid;">
			번호
		</th>
			<th>
				<input type="text" value="<%=vode.getNoti_no() %>" name="noti_no"
					size="20" style="width: 100%; border: 0;"  readonly="readonly">
			</th>
			
		</tr>
	
		<tr style="border: 1px solid;">
		<td style="border: 1px solid;">
			제목
		</td>
			<td>
				<input type="text" value="<%=vode.getTitle() %>" name="title"
					size="20" style="width: 100%; border: 0;" readonly="readonly">
			</td>
			
		</tr>
		
		<tr style="border: 1px solid;">
		<td style="border: 1px solid;">
			내용
		</td>
			<td>
				<textarea rows="10"  name="content"
					style="width: 100%; border: 0; resize: none;" readonly="readonly">
				<%=vode.getContent() %>
				</textarea>
				
			</td>
			
		</tr>
		<tr style="border: 1px solid;">
		<th style="border: 1px solid;">
			등록일
		</th>
			<th>
				<input type="text" value="<%=vode.getRegdate() %>" name="regdate"
					size="20" style="width: 100%; border: 0;" readonly="readonly">
			</th>
			
		</tr>
		
	</table>
	<br>
	<input type="submit" value="수정">
	<br>
	</form>
	<input type="button" value="삭제" onclick="deleteSomething()">
	

			<%
		} else if(admin1.equals("0")){
			%>
			<h2>세부사항</h2>
			<form action="notiupdate.do" method="post" name="updatearticle">
				<table style="border: 1px solid; border-collapse: collapse; ">
					<tr style="border: 1px solid;">
					<th style="border: 1px solid;">
						번호
					</th>
						<th>
							<input type="text" value="<%=vode.getNoti_no() %>" name="noti_no"
								size="20" style="width: 100%; border: 0;"  readonly="readonly">
						</th>
						
					</tr>
				
					<tr style="border: 1px solid;">
					<td style="border: 1px solid;">
						제목
					</td>
						<td>
							<input type="text" value="<%=vode.getTitle() %>" name="title"
								size="20" style="width: 100%; border: 0;" readonly="readonly">
						</td>
						
					</tr>
					
					<tr style="border: 1px solid;">
					<td style="border: 1px solid;">
						내용
					</td>
						<td>
							<textarea rows="10"  name="content"
								style="width: 100%; border: 0; resize: none;" readonly="readonly">
							<%=vode.getContent() %>
							</textarea>
							
						</td>
						
					</tr>
					<tr style="border: 1px solid;">
					<th style="border: 1px solid;">
						등록일
					</th>
						<th>
							<input type="text" value="<%=vode.getRegdate() %>" name="regdate"
								size="20" style="width: 100%; border: 0;" readonly="readonly">
						</th>
						
					</tr>
					
				</table>
				</form>
			<%
			
		}
		} else if(session.getAttribute("id")==null){
			%>
			<h2>세부사항</h2>
			<form action="notiupdate.do" method="post" name="updatearticle">
				<table style="border: 1px solid; border-collapse: collapse; ">
					<tr style="border: 1px solid;">
					<th style="border: 1px solid;">
						번호
					</th>
						<th>
							<input type="text" value="<%=vode.getNoti_no() %>" name="noti_no"
								size="20" style="width: 100%; border: 0;"  readonly="readonly">
						</th>
						
					</tr>
				
					<tr style="border: 1px solid;">
					<td style="border: 1px solid;">
						제목
					</td>
						<td>
							<input type="text" value="<%=vode.getTitle() %>" name="title"
								size="20" style="width: 100%; border: 0;" readonly="readonly">
						</td>
						
					</tr>
					
					<tr style="border: 1px solid;">
					<td style="border: 1px solid;">
						내용
					</td>
						<td>
							<textarea rows="10"  name="content"
								style="width: 100%; border: 0; resize: none;" readonly="readonly">
							<%=vode.getContent() %>
							</textarea>
							
						</td>
						
					</tr>
					<tr style="border: 1px solid;">
					<th style="border: 1px solid;">
						등록일
					</th>
						<th>
							<input type="text" value="<%=vode.getRegdate() %>" name="regdate"
								size="20" style="width: 100%; border: 0;" readonly="readonly">
						</th>
						
					</tr>
					
				</table>
				
</form>
				<%
		}
	%>



</body>

</html>