<%@page import="java.sql.ResultSet"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.sql.SQLException"%>
<%@page import="db.DBConnection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	//1. 파라미터로 전달된 아이디가 존재하는지 검사
	request.setCharacterEncoding("utf-8"); // utf-8로 id 받기
	String id = request.getParameter("id");
	boolean using = false;
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	try {
		con = DBConnection.getConn();
		String sql = "select * from users where id=?";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, id);
		rs = pstmt.executeQuery();
		if (rs.next()) {
			using = true; // 아이디 사용중
		}
	} catch (SQLException se) {
		System.out.println(se.getMessage());
	} finally {
		DBConnection.closeConn(rs, pstmt, con);
	}
	// 2. 결과를 XML로 응답하기
	response.setContentType("text/xml;charset=utf-8");
	PrintWriter pw = response.getWriter();
	pw.println("<?xml version='1.0' encoding='utf-8'?>");
	pw.println("<result>");
	pw.println("<using>" + using + "</using>");
	pw.println("</result>");
	pw.close();
	// http://dmszone.com:32778/ajax02/1/idcheck.jsp?id=test
%>
</body>
</html>