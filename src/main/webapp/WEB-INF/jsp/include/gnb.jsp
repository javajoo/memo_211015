<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 코어 라이브러리는 매 페이지마다 넣어줘야 한다 -->

<div class="d-flex pt-3">
	<div class="col-2"></div>
	<div class="display-4 font-weight-bold text-center col-8"><a href="/user/sign_in_view" class="logo">Memo Board</a></div>
	
	
	
	<!-- 세션이 있을때만(로그인 되었을 때만) 출력 -->
	<div class="col-2 mt-4">
		<c:if test="${not empty userName}">
			<span><b>${userName}</b><span class="text-dark">님 안녕하세요</span></span>
			<div><a href="/user/sign_out" class="font-weight-bold text-info">로그아웃</a></div>
		</c:if>
	</div>
</div>
