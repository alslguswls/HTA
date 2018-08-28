<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String context = request.getContextPath();
%>
<script type="text/javascript">
	function boardModify(bnum){
		location.href="<%=context%>/boardList.do?mod=getInfo&bnum="+bnum;
	}
	
	function boardDelete(bnum){
		location.href="<%=context%>/boardList.do?mod=delete&bnum="+bnum;
	}

</script>