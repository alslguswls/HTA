<%@page import="board.vo.boardVo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="board.dao.boardDao"%>
<%@page import="dao.ms.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- String context = application.getContextPath();-->
<%
	String cate =request.getParameter("cate");
	if(cate==null)cate="0";
	boardDao dao = boardDao.getInstance();
	ArrayList<boardVo> list = dao.list(cate);
%>