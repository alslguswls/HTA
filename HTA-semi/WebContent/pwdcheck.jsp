<%@page import="java.sql.ResultSet"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.sql.SQLException"%>
<%@page import="db.DBConnection"%>
<%@page import="com.sun.corba.se.spi.orbutil.fsm.Guard.Result"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
//1. 파라미터로 전달된 패스워드 두개 값이 서로 일치하는지 검사
	request.setCharacterEncoding("utf-8"); // utf-8로 pwd받기
	String pwd=request.getParameter("pwd");
	String pwd2=request.getParameter("pwd2");
	System.out.println("pwd=" + pwd);
	System.out.println("pwd2=" + pwd2);
	boolean same=false;
	if(pwd.equals(pwd2)){
		same=true;
	}
	
// 2. 결과를 XML로 응답하기
	response.setContentType("text/xml;charset=utf-8");
	PrintWriter pw=response.getWriter();
	pw.println("<?xml version='1.0' encoding='utf-8'?>");
	pw.println("<result>");
	pw.println("<same>" + same + "</same>");
	pw.println("</result>");
	pw.close();
	

%>
</body>
</html>