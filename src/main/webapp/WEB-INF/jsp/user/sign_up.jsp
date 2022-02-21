<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

	<table class="sign-up-table table d-flex justify-content-center" >
		<tr>
			<th class="text-center">아이디</th>
			<td>
				<div class="d-flex">
					<input type="text" id="loginId" name="loginId" class="form-control" placeholder="아이디">
					<div class="pl-3"><button type="button" id="loginIdCheckBtn" class="btn-success form-control col-4">중복확인</button></div>
				</div>
				<div id="idCheckLength"><small class="text-primary font-weight-bold">ID를 4자 이상 입력 해주세요.</small></div>
				<div id="isCheck"><small class="text-primary font-weight-bold d-none">사용 가능한 아이디 입니다.</small></div>
				<div id="isCheckDupliceted"><small class="text-danger font-weight-bold d-none">중복된 아이디 입니다.</small></div>
			</td>
		</tr>
		
		<tr>
			<th class="text-center">비밀번호</th>
			<td><input type="text" id="password" name="password" class="form-control" placeholder="비밀번호"></td>
		</tr>
		
		<tr>
			<th class="text-center">비밀번호 확인</th>
			<td><input type="text"  id="confirmPassword" name="confirmPassword" class="form-control" placeholder="비밀번호 확인"> </td>
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
		<div><button type="button" id="signUpBtn" class="form-control btn-info col-4">회원가입</button></div>
	</div>
