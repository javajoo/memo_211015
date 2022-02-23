<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 코어 라이브러리는 매 페이지마다 넣어줘야 한다 -->

<div class="d-flex">
	<div class="col-2"></div>
	<div class="display-4 font-weight-bold text-center col-8">Memo board</div>
	
	
	
	<!-- 세션이 있을때만(로그인 되었을 때만) 출력 -->
	<div class="col-2 mt-4">
		<c:if test="${not empty userName}">
			<span><b>${userName}</b><span class="text-dark">님 안녕하세요</span></span>
			<div><a href="/user/sign_out" class="font-weight-bold">로그아웃</a></div>
		</c:if>
	</div>
</div>
