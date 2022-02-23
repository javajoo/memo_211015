<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- 가운데 먼저 설정하고 가로너비 50으로 지정!!!! 그래야 가운데로 들어온다 -->
<div class="d-flex justify-content-center">
	<div class="w-50 h-50 m-5">
		<table border=1 class="table text-center">
			<thead>
				<tr>
					<th>No</th>
					<th>제목</th>
					<th>날짜</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>1</td>
					<td>memo board 프로젝트입니다</td>
					<td>2022-02-23</td>
				</tr>
			</tbody>
		</table>
		
		<div class="float-right">
			<a href="/post/post_create_view" class="btn btn-info">글쓰기</a>
		</div>
	</div>
</div>