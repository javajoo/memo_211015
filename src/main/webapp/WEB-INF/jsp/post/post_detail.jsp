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
			
			<div class="d-flex justify-content-around pb-4 pt-2">
				<button type="button" id="postDeleteBtn" class="btn btn-info col-2"><b>삭제</b></button>
				<!-- <a>태그로 해도 되고 script로 해도 된다 -->
				<button type="button" id="postListBtn" class="btn btn-info col-2">목록</button>
				<!-- 저장버튼에다가 postId를 심어준다. -->
				<button type="button" id="saveBtn" class="btn btn-info col-2" data-post-id="${post.id}">저장</button>
				
			</div>
		</div>
	</div>
	
	
	<script>
		$(document).ready(function() {
			//alert('click');
			
			// 목록 버튼 클릭
			$('#postListBtn').on('click',function(e) {
				//alert('click');
				location.href= "/post/post_list_view";
				
			});
			
			// 저장 버튼 클릭 => 수정
			$('#saveBtn').on('click',function(e) {
				//alert('click');
				
				//validation 체크
				
				var subject = $('#subject').val().trim();
				
				if (subject == '') {
					alert('제목을 입력해주세요.');
					return;
				}
				
				var content = $('#content').val();
				
				var file = $('#file').val(); // 파일 경로만 가져온다.
				//alert(file);    
				console.log(file);//C:\fakepath\mainImage.jpg
				// 파일이 있을때만 
				if (file != '') {
					var ext = file.split('.').pop().toLowerCase(); // 파일 경로를 .으로 나누고 확장자가 있는 마지막 문자열을 가져온 후 모두 소문자로 변경
					if ($.inArray(ext, ['gif','jpg','jpeg','png']) == -1) { // 확장자가 포함되어 있지 않으면
						alert("gif, png, jpg, jpeg 파일만 업로드 할 수 있습니다.");
						$('#file').val(''); // 파일을 비운다.
						return;
					}
				}
				
				
				// form 태그 객체를 자바스크립트에서 만든다.
				// 그림을 업로드하려면 폼태그가 반드시 있어야 한다.
				
				var formData = new FormData();
				// 버튼이 눌렸을때 data-post-id를 꺼낸다.
				var postId = $(this).data('post-id');
				console.log(postId);
				formData.append("postId",postId);
				formData.append("subject",subject);
				formData.append("content",content);
				formData.append("file", $('#file')[0].files[0]);
			
				// ajax
				// 수정일때는 PUT로 해준다.
				// ajax url은 무조건 view가 오는 url은 올 수 없다!!!!
				$.ajax({
					url: "/post/update"
					, type: "PUT"
					, data: formData
					, enctype: "multipart/form-data" // 파일 업로드를 위한 필수 설정
					, processData: false // 파일 업로드를 위한 필수 설정
					, contentType: false // 파일 업로드를 위한 필수 설정
					, success: function(data) {
						if (data.result == 'success') { // 성공
							alert('메모가 수정되었습니다.');
							location.reload(); // 수정이 잘 되었는지 확인 시켜주기
						} else { // 실패
							alert(data.errorMessage);
						}
					}
					, error: function(e) { // 통신에러
						alert('메모 저장에 실패했습니다. 관리자에게 문의해주세요.');
					}
				});
				
				
			});
		});
	</script>