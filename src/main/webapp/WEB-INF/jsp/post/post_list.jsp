<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- 가로값 50인 상자가 가운데로 오게 된다 -->
<div class="d-flex justify-content-center">
	<div class="w-50 m-5">
		<h1>글 목록</h1>
		<table class="table text-center table-hover">
			<hr>
			<thead>
				<tr>
					<th>No</th>
					<th>제목</th>
					<th>작성날짜</th>
					<th>수정날짜</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${postList}" var="postList">
					<tr>
						<td>${postList.id}</td>
						<!-- postid id에 도메인명 붙여주는게 좋다 -->
						<td><a href="/post/post_detail_view?postId=${postList.id}">${postList.subject}</a></td>
						<td>
							<!-- date -> string 변환 바로출력(var X)--> 
							<fmt:formatDate	value="${postList.createdAt}" pattern="yyyy-MM-dd HH:mm:ss" />
						</td>
						<td>
							<fmt:formatDate value="${postList.updatedAt}" pattern="yyyy-MM-dd HH:mm:ss" />
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<!-- div에 float를 써서 내가 원하는 위치에 놓을 수 있다 이걸 쓰면 밑에 태그에는 clear 해줘야 한다 -->
		<div class="float-right">
		<!-- 화면으로 이동할때는 <a> 태그 사용해서 페이지만 넘겨준다 -->
			<a href="/post/post_create_view" class="btn btn-info">글쓰기</a>
		</div>
	</div>
</div>