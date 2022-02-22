<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<form id="loginFrom" method="post" action="/user/sign_in">
<div class="d-flex justify-content-center align-items-end">
<div>
	<div class="d-flex justify-content-center mb-3">
		<div><input type="text" id="loginId" name="loginId" class="form-control" placeholder="아이디"></div>
	</div>

	<div class="d-flex justify-content-center mb-3">
		<div><input type="password" id="password" name="password" class="form-control" placeholder="비밀번호"></div>
	</div>
	
	<div class="d-flex justify-content-center mb-3">
		<button type="submit" id="signInBtn" class="btn-info form-control">로그인</button>
	</div>

	<div class="d-flex justify-content-center mb-3">
		<!-- <button type="button" id="signUpBtn" class="btn-secondary form-control">회원가입</button> -->
		<a class="btn btn-secondary form-control" href="/user/sign_up_view">회원가입</a>
	</div>
	
	</div>
</div>
</form>

<!--form태그 사용시 3종 세트: form태그 , name, 버튼 타입:submit  -->

<script>
	$(document).ready(function() {
		//alert('click');
		$('#loginFrom').on('submit',function(e) {
			//alert('click');
			
			// 유효성 체크
			var loginId = $('#loginId').val().trim(); 
			var password = $('#password').val();
			
			if (loginId == '') {
				alert("아이디를 입력하세요");
				return false;
			}
			
			if (password == '') {
				alert("비밀번호를 입력하세요");
				return false;
			}
			
			
			// submit 안되게 해주는 함수 (둘 다 똑같다) $(this)[0].submit();
			e.preventDefault(); 
			
			
			// ajax 호출
			// $('#loginFrom') 위에 있는 form태그랑 똑같다
			var url = $(this).attr('action'); // form 태그에 있는 action 주소를 가져옴
			var params = $(this).serialize(); // form 태그에 있는 name 속성을 가져와서 쿼리 스트링으로 구성
			// serialize: 데일터를 키,값으로 만들어줌
			
			$.post(url, params) // post 방식으로 간다
			.done(function(data) { // ajax가 성공하면 if 실패하면 else로 구성해줌
				if (data.result == 'success') {
					alert(loginId +"님 환영 합니다!!!");
					location.href= "/post/post_list_view"; // 로그인이 완료되면 리스트 화면으로 이동
				} else {
					alert(data.errorMessage); 
				}
			});
			
		});
	});
</script>