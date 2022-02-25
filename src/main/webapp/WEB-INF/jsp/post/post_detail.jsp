<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<div class="d-flex justify-content-center">

		<div class="h-50 pt-3">
			<h1>게시글 수정</h1>
			<hr>

			<input type="text" id="subject" name="subject" class="form-control w-100 mb-3" placeholder="제목을 입력해주세요">

			<textarea id="content" name="content" rows="10" cols="100"
				placeholder="내용을 입력해주세요" class="form-control"></textarea>

			<div class="d-flex justify-content-start">
				<!-- accept: 허용되는 확장자 편의성을 위해서 그림만 보여지게 해주는 기능만 한다. (사용자가 모든 파일 다 올릴수 있다) -->
				<input type="file" id="file" name="file" class=" my-3 mr-5" accept=".png,.jpg,.gif,.jpeg"> <!-- mt-3 mb-3  = my-3 -->
			</div>

			<div class="d-flex justify-content-around pb-5">
				<button type="button" id="" class="btn btn-secondary ml-5 col-2"><b>삭제</b></button>
				<button type="button" id="" class="btn btn-secondary col-2">수정</button>
				<button  type="button" id="" class="btn btn-secondary col-2 mr-5">목록</button>
			</div>
		</div>
	</div>