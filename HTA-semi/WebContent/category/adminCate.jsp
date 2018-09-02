<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="cate_in" style="width: 90%; margin: 0px auto;">
	<form method="post" action="Category.do?mod=insert" id="cateForm"
		name="cateForm">
		<span id="cateText" class="col-sm-3" style="margin: 30px 0px;">카테고리 추가하기 </span>
		<span class="col-sm-5">
		<input type="text" class="form-control" style="margin-top: 20px;" name="cate_n" id="cate_n"> 
		</span>
		<input type="hidden" name="cate" id="cate" value="">
		<span class="" style="float: left; margin: 20px auto;">
		<input type="button" value="추가" class="btn btn-default" id="chButton" onclick="name_ck()"> 
		<input type="button"  class="btn btn-default" style="display: none;" value="저장" id="modify"
			onclick="cateModify()"> 
		<input type="button"  class="btn btn-default"
			style="display: none;" name="cancle" id="cancle" value="취소"
			onclick="cateCancle()">
		</span>
	</form>
</div>
<div id="cate_list" style="width: 80%; margin: 0px auto;">
	<table class="table table-hover" >
		<tr>
			<th class="text-center col-sm-4">카테고리번호</th>
			<th class="text-center col-sm-4">카테고리 이름</th>
			<th class="text-center">/</th>

		</tr>
		<c:forEach var="n" items="${list }">
			<tr>
				<td>${n.cate }</td>
				<td>${n.name }</td>
				<td><input type="button" class="btn btn-default" value="수정"
					onclick="cateInfo(${n.cate },'${n.name }')" /> <input
					type="button" class="btn btn-default" value="삭제"
					onclick="cateDelete(${n.cate })" /></td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="3" style="padding: 0px; width: 30px;">
				<!-- 페이징 --> <!-- 이전 -->
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
	</table>
</div>


