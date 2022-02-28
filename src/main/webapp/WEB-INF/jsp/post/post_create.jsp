<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 
<form id="postCreateForm" method="post" action="/post/post/create"> -->
	<div class="d-flex justify-content-center">

		<div class="h-50 pt-3">
			<h1>글쓰기</h1>
			<hr>

			<input type="text" id="subject" name="subject" class="form-control w-100 mb-3" placeholder="제목을 입력해주세요">

			<textarea id="content" name="content" rows="10" cols="100"
				placeholder="내용을 입력해주세요" class="form-control"></textarea>

			<div class="d-flex justify-content-start">
				<!-- accept: 허용되는 확장자 편의성을 위해서 그림만 보여지게 해주는 기능만 한다. (사용자가 모든 파일 다 올릴수 있다) -->
				<input type="file" id="file" name="file" class=" my-3 mr-5" accept=".png,.jpg,.gif,.jpeg"> <!-- mt-3 mb-3  = my-3 -->
			</div>

			<div class="d-flex justify-content-around pb-5">
				<button type="button" id="postListBtn" class="btn btn-info col-2">목록</button>
				<button type="button" id="clearBtn" class="btn btn-info col-2">모두 	지우기</button>
				<button  type="button" id="saveBtn" class="btn btn-info col-2">저장</button>
			</div>
		</div>
	</div>
<!-- </form> -->

<script>
	$(document).ready(function() {
		//alert("click");
		
		// 목록 버튼 클릭 -> 글 목록 화면으로 이동
		$('#postListBtn').on('click',function(e) {
			//alert("click");
			// 함수가 아니어서 바로 사용 가능!!
			location.href="/post/post_list_view";
		});
		
		// 모두 지우기 버튼 클릭 -> 모두 지우기
		$('#clearBtn').on('click',function(e) {
			//alert('click');
			
			// 제목과 내용 부분을 초기화 해준다.
			$('#subject').val('');
			$('#content').val('');
		});
		
		// 글 내용 저장
		$('#saveBtn').on('click', function(e) {
			//alert("click");
			
			
			// validation 체크
			
			var subject = $('#subject').val().trim();
			
			if (subject == '') {
				alert('제목을 입력해주세요');
				return;
			}
			
			// 비필수여도 정보는 가져와야 한다.
			var content = $('#content').val();
			
			if (content == '') {
				alert('내용을 입력해주세요');
				return;
			}
			
			// 파일이 업로드 된 경우 확장자 체크
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
			
			// 폼태그를 자바스크립트에서 만든다.
			var formData = new FormData();
			formData.append("subject", subject);
			formData.append("content", content);
			formData.append("file", $('#file')[0].files[0]); 
			// $('#file')[0]은 첫번째 input file 태그를 의미, files[0]은 업로드된 첫번째 파일을 의미
			
			
			// ajax form 데이터 전송
			$.ajax({
				type: "post" // 파일이 크기 때문에 보여지면 길게 나옴
				, url: "/post/create"
				, data: formData
				, enctype: "multipart/form-data" // 파일 업로드를 위한 필수 설정
				, processData: false // 파일 업로드를 위한 필수 설정
				, contentType: false // 파일 업로드를 위한 필수 설정
				, success: function(data) {
					if (data.result == "success") {
						alert("메모가 저장되었습니다.");
						location.href = "/post/post_list_view"; // 글 목록 화면으로 이동
					} else {
						// 로그인 실패시
						alert(data.errorMessage);
						location.href= "/user/sign_in_view"; // 로그인 화면으로 이동
					}
				}
				, error: function(e) { // 서버에 들어오지도 못하면
					alert("메모 저장에 실패했습니다. 관리자에게 문의해주세요.");
				}
			});
		});
	});
</script>







