<%@page import="vo.wh.MembersVo"%>
<%@page import="dao.wh.MembersDao"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>loginOk.jsp</title>
</head>
<body>
<%
	String id=request.getParameter("id");
	String pwd=request.getParameter("pwd");
	ArrayList<MembersVo> list = new ArrayList<>();
	MembersDao dao= new MembersDao();
	list = dao.list();
	boolean f = true;
	for (MembersVo vo:list){
		if(id.equals(vo.getId()) && pwd.equals(vo.getPwd())){
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
	}
	
	
%>

</body>
</html>