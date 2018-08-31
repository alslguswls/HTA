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
	
	function AboardModify(bnum){
		location.href="<%=context%>/boardList.do?mod=getInfo&bnum="+bnum+"&admin=1";
	}
	function AboardDelete(bnum){
		confirm("정말 삭제하시겠 습니까? \n삭제시 관련된 정보가 모두 삭제 됩니다.");
		location.href="<%=context%>/boardList.do?mod=delete&bnum="+bnum+"&admin=1";
	}
</script>