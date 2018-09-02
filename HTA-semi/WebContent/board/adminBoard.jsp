<%@page import="lib.lib"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String context = application.getContextPath();
	String bstatus[] = lib.bStatus();
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="bstatus" value="<%=bstatus%>" />
<!-- <div><a href="boardList.do?mod=getCate">글작성</a></div> -->
<table class="table table-hover" style="width: 90%; margin: 30px auto;">
	<tr>
		<th colspan="9">
			<span class="col-sm-2">
				<select id="AdminCategorySel" class="form-control form-control-sm" onchange="selCate(this.value)">
					<c:forEach var="Acate" items="${list1 }">
						<option value="${Acate.cate }"
							<c:if test="${cate eq Acate.cate}">selected</c:if>>${Acate.name }</option>
					</c:forEach>
				</select>
			</span>
			<span style="padding-top: 10px;">
				 <a
					href="<%=context %>/layout.jsp?page=boardList.do?mod=list&cul=1&admin=1&cate=${cate}">조회순</a>
				<a
					href="<%=context %>/layout.jsp?page=boardList.do?mod=list&cul=0&admin=1&cate=${cate}">최근등록일</a>
				<a
					href="<%=context %>/layout.jsp?page=boardList.do?mod=list&cul=2&admin=1&cate=${cate}">경매임박순</a>
				<a
					href="<%=context %>/layout.jsp?page=boardList.do?mod=list&cul=3&admin=1&cate=${cate}">예약자순</a>
			</span>
		</th>

	</tr>
	<tr>
		<th class="text-center col-sm-1">글번호</th>
		<th class="text-center col-sm-2">아이디</th>
		<th class="text-center col-sm-3">제목</th>
		<th class="text-center col-sm-2">경매시작시간</th>
		<th class="text-center col-sm-2">시작가</th>
		<th class="text-center col-sm-2">진행상태</th>
		<th colspan="3" class="" >등록일</th>
	</tr>
	<c:forEach var="n" items="${list}">
		<tr>
			<td>${n.bnum }</td>
			<td>${n.id }</td>
			<td><a href="detail.do?cmd=detail&bnum=${n.bnum}&admin=1">${n.title }</a></td>
			
			<td>${n.starttime }</td>
			<td>${n.startprice }</td>
			<td>${bstatus[n.status]}</td>
			<td>${n.regdate }</td>
			<td><input type="button" value="수정"
				onclick="javascript:AboardModify(${n.bnum})"
				class="btn btn-default btn-xs"></td>
			<td><input type="button" value="삭제"
				onclick="javascript:AboardDelete(${n.bnum})"
				class="btn btn-default btn-xs"></td>
		</tr>
	</c:forEach>
	<tr>
		<td colspan="9">
			<!-- 페이징 -->
				<!-- 이전 -->
				<ul class="pagination">
					<c:choose>
						<c:when test="${startPage>10 }">
							<li><a href="memberList.do?pageNum=${startPage-1 }">[이전]</a>
							</li>
						</c:when>
						<c:otherwise>
							<li><span> 이전 </span></li>
						</c:otherwise>
					</c:choose>
					<!-- 페이지 숫자들 -->

					<c:forEach var="i" begin="${startPage }" end="${endPage }">
						<c:choose>
							<c:when test="${pageNum==i }">
								<%--현재페이지인경우 --%>
								<li class="active"><a href="memberList.do?pageNum=${i }">${i }</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="memberList.do?pageNum=${i }">${i }</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<!-- 다음 -->
					<c:choose>
						<c:when test="${endPage<pageCount }">
							<li><a href="memberList.do?pageNum=${endPage+1 }">[다음]</a></li>
						</c:when>
						<c:otherwise>
							<li><span>다음</span></li>
						</c:otherwise>
					</c:choose>
				</ul>
		</td>
	</tr>
	<tr>
		<td colspan="9">
			<form method="post"
				action="<%=context%>/boardList.do?mod=list&admin=1">
				<span class="col-sm-2">
				<select name="searchSel" class="form-control form-control-sm">
					<option value="0">제목</option>
					<option value="1">아이디</option>
				</select>
				</span> 
				<span class="input-group">
				<input type="text" class="form-control" name="search" value="${search1}" style="width: 300px;">
				<span class="input-group-btn">
				<button type="submit" class="btn btn-default">
					 <i class="glyphicon glyphicon-search" style="height:20px;"></i>
				</button>
			 	</span>
  			</span>
			</form>
		</td>
	</tr>
</table>