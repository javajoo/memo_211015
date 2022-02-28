<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<div class="d-flex justify-content-center">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

		<div class="h-50 pt-3">
			<h1>게시글 상세/수정</h1>
			<hr>

			<input type="text" id="subject" name="subject" class="form-control w-100 mb-3" placeholder="제목을 입력해주세요" value="${post.subject}">

			<textarea id="content" name="content" rows="10" cols="100"
				placeholder="내용을 입력해주세요" class="form-control" >${post.content}</textarea>
				<!-- textarex 는 <>여기에 value 값 들어가야 한다<> -->

			<div class="d-flex justify-content-start">
				<!-- accept: 허용되는 확장자 편의성을 위해서 그림만 보여지게 해주는 기능만 한다. (사용자가 모든 파일 다 올릴수 있다) -->
				<input type="file" id="file" name="file" class=" my-3 mr-5" accept=".png,.jpg,.gif,.jpeg"> <!-- mt-3 mb-3  = my-3 -->
			</div>
			
			<!-- 이미지가 있을 때만 이미지 영역 추가 -->
			<c:if test="${not empty post.imagePath}">
				<div class="image-area mb-3">
					<img src="${post.imagePath}" alt="업로드 이미지" width="300px">
				</div>
			</c:if>
			
			<div class="d-flex justify-content-around pb-5">
				<button type="button" id="postDeleteBtn" class="btn btn-info col-2"><b>삭제</b></button>
				<button type="button" id="postListBtn" class="btn btn-info col-2">목록</button>
				<button type="button" id="saveBtn" class="btn btn-info col-2">수정</button>
				
			</div>
		</div>
	</div>