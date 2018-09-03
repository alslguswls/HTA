<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">


</script>
</head>
<body >
<h3>공지사항 작성</h3>
<form action="notiinsert.do" name="insert" method="post">
<table class="table table-hover" style="width: 700px; margin: 50px auto 0px auto;">
<tr>
<th>
제목
</th>
<th class="form-group">
<input type="text"  class="form-control" name="title">
</th>
</tr>
<tr>
<th>
내용
</th>
<td class="form-group">
<textarea rows="5" class="form-control col-sm-5" cols="50" name="content">
</textarea>
</td>
</tr>

</table>
<input type="submit" class="btn " value="등록">
</form>
</body>
</html>