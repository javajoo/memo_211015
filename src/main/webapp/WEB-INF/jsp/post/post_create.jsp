<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<form id="postCreateForm" method="post" action="/post/post/create">
	<div class="d-flex justify-content-center">
		<div class="h-50 m-5">
				<div class="flaot-left">
					<input type="text" id="subject" name="subject" class="form-control w-100 mb-3" placeholder="제목을 입력해주세요">
				</div>

			<div class="d-flex justify-content-center">
				<div>
					<textarea id="content" name="content" rows="10" cols="100" placeholder="내용을 입력해주세요" ></textarea>
				</div>
			</div>

			<div class="d-flex justify-content-start">
					<input type="file" id="file" name="file" class=" mt-3 mb-4 mr-5">
			</div>
			
			<div class="d-flex justify-content-around">
				<button id="postListBtn" class="btn btn-dark col-2 ml-5">목록</button>
				<button id="clearBtn" class="btn btn-secondary col-2">모두 지우기</button>
				<button id="saveBtn" class="btn btn-info col-2 mr-5">저장</button>
			</div>
		</div>
	</div>
</form>