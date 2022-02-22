<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!-- <form id="signUpForm" method="post" action="/user/sign_up_for_submit"> -->
<form id="signUpForm" method="post" action="/user/sign_up">
	<table class="sign-up-table table d-flex justify-content-center" >
		<tr>
			<th class="text-center">아이디</th>
			<td>
				<div class="d-flex">
					<input type="text" id="loginId" name="loginId" class="form-control" placeholder="아이디">
					<div class="pl-3"><button type="button" id="loginIdCheckBtn" class="btn-success form-control col-4">중복확인</button></div>
				</div>
				
				<!-- 아이디 체크 결과(모두 숨겨놓는다)div 안의 class에 속성넣어줘야 한다!!!! -->			
				<div id="idCheckLength" class="small text-primary font-weight-bold d-none">ID를 4자 이상 입력 해주세요.</div>
				<div id="isCheck" class="small text-success font-weight-bold d-none">사용 가능한 아이디 입니다.</div>
				<div id="isCheckDupliceted" class="small text-danger font-weight-bold d-none">중복된 아이디 입니다.</div>
			</td>
		</tr>
		
		<tr>
			<th class="text-center">비밀번호</th>
			<td><input type="password" id="password" name="password" class="form-control" placeholder="비밀번호"></td>
		</tr>
		
		<tr>
			<th class="text-center">비밀번호 확인</th>
			<td><input type="password"  id="confirmPassword" name="confirmPassword" class="form-control" placeholder="비밀번호 확인"> </td>
		</tr>
		
		<tr>
			<th class="text-center">이름</th>
			<td><input type="text" id="name" name="name" class="form-control" placeholder="이름"> </td>
		</tr>
		
		<tr>
			<th class="text-center">이메일</th>
			<td><input type="text" id="email" name="email" class="form-control" placeholder="이메일"> </td>
		</tr>
	</table>
	
	<div class="d-flex justify-content-center">
		<button type="submit" id="signUpBtn" class="form-control btn-info col-1">회원가입</button>
	</div>
	</form>
	
	<script>
		$(document).ready(function() {
			//alert("click");
			
			// 아이디 중복확인
			$('#loginIdCheckBtn').on('click',function(e) {
				//alert("click");
				var loginId = $('#loginId').val().trim();
				
				// 상황 문구 안보이게 모두 초기화
				$('#idCheckLength').addClass('d-none');
				$('#isCheck').addClass('d-none');
				$('#isCheckDupliceted').addClass('d-none');
				
				if (loginId.length < 4) {
					// id가 4자 미만일 때 경고 문구 노출하고 끝낸다.
					$('#idCheckLength').removeClass('d-none');
					return;
				}
				
				//ajax - 중복확인(api 호출) 기존에 있는 데이터와 비교해야 해서
				$.ajax({
					type: "get"
					,url: "/user/is_duplicated_id"
					,data: {"loginId":loginId}
					,success: function(data) {
						// 중복인 경우 -> 이미 사용중인 아이디
						if (data.result == true) {
							$('#isCheckDupliceted').removeClass('d-none');
						} else {   // 중복이 아닌 경우 -> 사용 가능한 아이디
							$('#isCheck').removeClass('d-none');
						}
						
					} 
					,error: function(e) {
						alert("아이디 중복확인에 실패 했습니다. 관리자에게 문의 해주세요.");
					}
				});
			});
			
			// 회원가입 -> 버튼을 눌렀을때 하거나 form id로(버튼-submit) 2가지 방법중 선택하면 된다
			$('#signUpForm').on('submit',function(e) {
				e.preventDefault(); // 서브밋 기능 중단(페이지 넘어가는거!!!!)
			
				//validation check 
				var loginId = $('#loginId').val().trim();
				if (loginId == '') {
					alert('아이디를 입력해주세요');
					return false; // submit은 false로 리턴시켜줘야 한다 안해주면 submit이 작동한다
				}
				
				var password = $('#password').val(); // 비번은 trim() 안해줘도 된다
				var confirmPassword = $('#confirmPassword').val(); 
				
				if (password == '' || confirmPassword == '') {
					alert('비밀번호를 입력해주세요');
					return false;
				}
				
				if (password != confirmPassword) {
					alert('비밀번호가 일치하지 않습니다');
					// 텍스트의 값을 초기화 한다
					$('#password').val(''); 
					$('#confirmPassword').val('');
					return false;
				}
				
				var name = $('#name').val().trim();
				if (name == '') {
					alert('이름을 입력해주세요');
					return false;
				}
				
				var email = $('#email').val().trim();
				if (email == '') {
					alert('이메일을 입력해주세요');
					return false;
				}
				
				// 아이디 중복확인이 되었는지 확인
				// isCheck <div>에 클래스 중 d-none이 있는 경우 => 회원가입X
				// d-none 클래스를 가지고 있다면
				if ($('#isCheck').hasClass('d-none')) {
					alert('아이디 중복확인을 다시 해주세요');
					return false;
				}
				
				// submit (여기까지 온 애들은 회원가입 해도 된다)
				// 1. form 서브밋 -> 응답이 화면이 된다
				
				// 위에 나온 이벤트랑 같은 경우 this라고 써줘도 된다
				//$(this)[0].submit();
				
				// 2. ajax 서브밋 -> 응답이 data가 된다
				// form 태그로 감싸서 만들고 ajax로 호출하는 방법
				var url = $(this).attr('action'); // form 태그에 있는 action 주소를 가져오는 법
				//alert(url);
				
				var params = $(this).serialize(); // form 태그에 들어있는 값을 한번에 보낼 수 있게 구성한다(name 속성), 데이터 직렬화
				//console.log(params);
				
				// url : api 
				$.post(url, params)
				.done(function(data){
					if (data.result == 'success') {
						alert('회원 가입을 환영합니다 로그인을 해주세요');
						location.href = "/user/sign_in_view"; // 로그인화면으로 이동(화면)
					} else {
						alert('회원 가입에 실패했습니다 다시 시도해주세요');
					}
				});
			});
		});
	</script>
	
	
	
	
	
	
	
	
