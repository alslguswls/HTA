<%@page import="vo.ms.BoardVo"%>
<%@page import="lib.lib"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	//카테고리 리스트 가져오기
	lib lb = new lib();
	String[] cate = lb.category();
	String sid = (String) session.getAttribute("id");
	if (sid == null) {
		pageContext.forward("Category.do?mod=getCate&to=login.jsp");
	}
	//게시글 수정시 사용
	BoardVo vo = null;
	vo = (BoardVo) request.getAttribute("vo");
	String day = null;
	String hour = null;
	String minute = null;

	if (vo != null) {
		String time = vo.getStarttime();
		day = time.substring(0, 10).replaceAll("-", "");
		hour = time.substring(11, 13);
		minute = time.substring(14, 16);
	}
%>
<c:set var="sid" value="<%=sid%>" />
<c:set var="cate" value="<%=cate%>" />
<c:set var="day" value="<%=day%>" />
<c:set var="hour" value="<%=hour%>" />
<c:set var="minute" value="<%=minute%>" />
<c:set var="id1" value="${vo.getId() }" />
<form id="newBoard" name="newBoard" method="post"
	action="insertBoard.do" enctype="multipart/form-data">
	<table class="table table-hover" style="width: 700px; margin: 50px auto 0px auto;" >
		<tr>
			<th>작성자</th>
			<td><c:choose>
					<c:when test="${id1 ne null }">
						<span>${id1 }</span>
						<input type="hidden" id="id" name="id" value="${id1 }">
					</c:when>
					<c:otherwise>
						<span>${sid }</span>
						<input type="hidden" id="id" name="id" value="${sid }">
					</c:otherwise>
				</c:choose> <input type="hidden" name="boardNum" id="boardNum"
				value="${vo.getBnum() }"></td>
		</tr>
		<tr>
			<th>제목</th>
			<td class="form-group">
				<input type="text" class="form-control" name="title" id="title"
				value="${vo.getTitle() }">
			</td>
		</tr>
		<tr>
			<th>카테고리</th>
			<td><select id="cate" name="cate" class="form-control">
					<c:forEach var="n" items="${list1 }" varStatus="cate">
						<option value="${n.cate}"
							<c:if test="${cate.index eq  reCate}">selected</c:if>>${n.name }</option>
					</c:forEach>
			</select></td>
		</tr>
		<tr>
			<th>시작가격</th>
			<td class="form-group">
				<input type="text" class="form-control" name="price" id="price"
				value="${ vo.getStartprice() }">
			</td>
		</tr>
		<tr>
			<th>시작날짜</th>
			<td class="form-group">
				<input type="text" class="form-control" id="sdate" name="sdate" value="${day}">
			</td>
		</tr>
		<tr>
			<th>시작시간선택</th>
			<td class="form-group">
			<select id="hh" name="hh" class="form-control form-control-sm" style="width: 100px; float: left; margin-right: 10px;">
					<option value="00">00 시</option>
					<c:forEach var="n" begin="0" step="1" end="22" varStatus="st">
						<fmt:formatNumber var="no" minIntegerDigits="2"
							value="${st.count }" type="number" />
						<option value="${no }" <c:if test="${no eq  hour}">selected</c:if>>${no } 시</option>
					</c:forEach>
			</select>
			
			<select id="mm" name="mm" class="form-control form-control-sm" style="width: 100px;float: left;">
					<option value="00">00 분</option>
					<c:forEach var="m" begin="10" step="10" end="50" varStatus="st1">
						<option value="${m }" <c:if test="${m eq  minute}">selected</c:if>>${m } 분</option>
					</c:forEach>
			</select>
			</td>
		</tr>
		<tr>
			<th>이미지</th>
			<td>
			<input type="file" class="form-control-file" name="orgfile" id="orgfile"
				value="${vo.getOrgfilename()}">
		
				 <input type="hidden"
				name="oldOrgfile" value="${vo.getOrgfilename() }"> <input
				type="hidden" name="oldSavefile" value="${vo.getSavefilename() }">
			</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>
				<div class="form-group">
 				 <textarea class="form-control col-sm-5" rows="5"  id="contents" name="contents">${vo.getContent()}</textarea>
				</div>
			</td>
		</tr>
		<tr>
			<td colspan="2"><c:if test="${vo eq null}">
					<input type="button" class="btn btn-default" value="게시글 등록하기" onclick="check('insert')">
				</c:if> <c:if test="${vo ne null}">
					<input type="button" class="btn btn-default" value="수정완료"  onclick="check('update')">
				</c:if> <input type="button" class="btn btn-default" value="취소" onclick="history.go(-1)">
			</td>
		</tr>
	</table>
</form>