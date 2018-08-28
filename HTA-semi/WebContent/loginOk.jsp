<%@page import="vo.wh.MembersVo"%>
<%@page import="dao.wh.MembersDao"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--
2018. 8. 27	윤우현	- 로그인시 어드민 체크 가능하도록 lev를 세션에 넣음.
			- id, pw가 vo에 존재할 시 로그인 처리함. 없으면 login.jsp 로 이동
-->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>loginOk.jsp</title>
</head>
<body>
<%
	MembersVo vo = new MembersVo();
	MembersVo paramVo = new MembersVo();
	paramVo.setId(request.getParameter("id"));
	paramVo.setPwd(request.getParameter("pwd"));
	
	MembersDao dao= new MembersDao();
	vo = dao.loginCheck(paramVo);
	
	// 로그인 성공시
	if(vo != null){
		// 아이디, 패스워드 값이 일치하면 세션 스코프에 값 담기
		session.setAttribute("id", vo.getId());
		session.setAttribute("isAdmin", String.valueOf(vo.getLev())); // 로그인시 어드민 여부 체크
		response.sendRedirect("layout.jsp");
	// 로그인 실패시
	}else {
		request.setAttribute("errMsg","아이디 또는 패스워드가 맞지 않습니다");
		// login.jsp로 이동
		RequestDispatcher rd=request.getRequestDispatcher("layout.jsp?page=login.jsp");
		rd.forward(request,response);
	}
	
	/* 
	boolean f = true;
	for (MembersVo vo:list){
		if(id.equals(vo.getId()) && pwd.equals(vo.getPwd()) && ){
			// 아이디, 패스워드 값이 일치하면 세션 스코프에 값 담기
			session.setAttribute("id", id);
			response.sendRedirect("layout.jsp");
			f = false;
			break;
		}
	}
	if(f){ // 설명좀
		request.setAttribute("errMsg","아이디 또는 패스워드가 맞지 않습니다");
		request.setAttribute("id", id);
		request.setAttribute("pwd", pwd);
		// 로그인 jsp로 이동
		RequestDispatcher rd=request.getRequestDispatcher("layout.jsp?page=login.jsp");
		rd.forward(request,response);
	} */
	
	
%>

</body>
</html>