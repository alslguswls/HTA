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

div.button1

{

   margin: auto;

   width: 50%;

}

div.button2

{

   margin: auto;

   width: 50%;

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
	<table class="table table-hover" style="width: 700px; margin: 50px auto 0px auto;">
		<tr >
		<th >
			번호
		</th>
			<th class="form-group">
				<input type="text" class="form-control" value="<%=vode.getNoti_no() %>" name="noti_no"
					size="20" style="width: 100%; border: 0;"  readonly="readonly">
			</th>
			
		</tr>
	
		<tr >
		<td >
			제목
		</td>
			<td class="form-group">
				<input type="text" class="form-control" value="<%=vode.getTitle() %>" name="title"
					size="20" style="width: 100%; border: 0;" readonly="readonly">
			</td>
			
		</tr>
		
		<tr >
		<td >
			내용
		</td>
			<td class="form-group">
				<textarea rows="10"  name="content" class="form-control col-sm-5"
					style="width: 100%; border: 0; resize: none;" readonly="readonly">
				<%=vode.getContent() %>
				</textarea>
				
			</td>
			
		</tr>
		<tr >
		<th >
			등록일
		</th>
			<th class="form-group">
				<input type="text" class="form-control" value="<%=vode.getRegdate() %>" name="regdate"
					size="20" style="width: 100%; border: 0;" readonly="readonly">
			</th>
			
		</tr>
		
	</table>
	<br>
	<div class="button1">
	<input type="submit" class="btn "  value="수정">&nbsp;
	<input type="button" class="btn "  value="삭제" onclick="deleteSomething()">
	</div>
	</form>
	

			<%
		} else if(admin1.equals("0")){
			%>
			<h2>세부사항</h2>
			<form action="notiupdate.do" method="post" name="updatearticle">
				<table class="table table-hover" style="width: 700px; margin: 50px auto 0px auto;">
					<tr >
					<th >
						번호
					</th>
						<th class="form-group">
							<input type="text" class="form-control" value="<%=vode.getNoti_no() %>" name="noti_no"
								size="20" style="width: 100%; border: 0;"  readonly="readonly">
						</th>
						
					</tr>
				
					<tr >
					<td >
						제목
					</td>
						<td class="form-group">
							<input type="text" class="form-control" value="<%=vode.getTitle() %>" name="title"
								size="20" style="width: 100%; border: 0;" readonly="readonly">
						</td>
						
					</tr>
					
					<tr >
					<td >
						내용
					</td>
						<td class="form-group">
							<textarea rows="10"  name="content" class="form-control col-sm-5"
								style="width: 100%; border: 0; resize: none;" readonly="readonly">
							<%=vode.getContent() %>
							</textarea>
							
						</td>
						
					</tr>
					<tr >
					<th >
						등록일
					</th>
						<th class="form-group">
							<input type="text" class="form-control"  value="<%=vode.getRegdate() %>" name="regdate"
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
				<table class="table table-hover" style="width: 700px; margin: 50px auto 0px auto;">
					<tr >
					<th >
						번호
					</th>
						<th class="form-group">
							<input type="text" class="form-control" value="<%=vode.getNoti_no() %>" name="noti_no"
								size="20" style="width: 100%; border: 0;"  readonly="readonly">
						</th>
						
					</tr>
				
					<tr >
					<td >
						제목
					</td>
						<td class="form-group">
							<input type="text" class="form-control" value="<%=vode.getTitle() %>" name="title"
								size="20" style="width: 100%; border: 0;" readonly="readonly">
						</td>
						
					</tr>
					
					<tr >
					<td >
						내용
					</td>
						<td class="form-group">
							<textarea rows="10"  name="content" class="form-control col-sm-5"
								style="width: 100%; border: 0; resize: none;" readonly="readonly">
							<%=vode.getContent() %>
							</textarea>
							
						</td>
						
					</tr>
					<tr >
					<th >
						등록일
					</th>
						<th class="form-group">
							<input type="text" class="form-control"  value="<%=vode.getRegdate() %>" name="regdate"
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