<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
	function boardModify(){
		var id_ck="${vo.id }";
		var id="<%=session.getAttribute("id") %>";
		var level = "${isAdmin}";
		if( id != id_ck){
			alert("작성");
		}else if(id==id_ck && isAdmin=="1" ){
			
		}
	}

</script>