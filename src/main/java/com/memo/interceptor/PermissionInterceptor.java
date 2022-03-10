package com.memo.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component // 스프링으로 만들어야 하는데 딱히 정해진 설정이 아닌경우 @Component 해주면 된다.
public class PermissionInterceptor  implements HandlerInterceptor{

	private Logger logger = LoggerFactory.getLogger(PermissionInterceptor.class);

	@Override
	public boolean preHandle(
			HttpServletRequest request, 
			HttpServletResponse response
			,Object handler) throws IOException {
		
		// /user/sign_in_view -> 로그인 페이지,회원가입  로그인 상태&/user... -> /post/post_list_view로 이동시킨다
		// 로그아웃은 처리하면 /post로 이동하면서 로그아웃 안되는 현상 발생하므로 권한검사 제외
		
		// /post/post_detail, list,   로그인이 안된 상태 && /post -> /user/sign_in_view로 이동시킨다.
		
		// 세션이 있는지 확인한다 -> 있으면 로그인
		HttpSession session = request.getSession();
		// 로그인이거나 비로그인이거나 둘다 들어올 수 있다.
		Integer userId = (Integer)session.getAttribute("userId");
		
		// URI 확인 (URL path를 가져온다)
		String uri = request.getRequestURI();
		
		if (userId != null && uri.startsWith("/user")) {
			// 로그인 상태 이면서, 접근을 시도한 uri path가 /user로 시작되면 게시판 목록으로 리다이렉트 
			// 로그인 되어 있으면 로그인, 회원가입 화면으로 다시 안들어가게 해준다.
			response.sendRedirect("/post/post_list_view");
			return false; // 지금요청은 못들어오게 해준다.
		} else if (userId == null && uri.startsWith("/post")) {
			// 비로그인 상태 이면서, 접근을 시도한 uri path가 /post로 시작되면 로그인 페이지로 리다이렉트
			// 로그아웃 생태에서 타임라인 화면으로 못들어가게 해준다. 
			response.sendRedirect("/user/sign_in_view");
			return false; // 지금요청은 못들어오게 해준다.
		}
		
		
		logger.warn("######### preHandle 호출, uri:{}", uri);
		
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler, ModelAndView modelAndView) {
		
		// URI 확인 (URL path를 가져온다)
		String uri = request.getRequestURI();
		
		
		logger.warn("######### postHandle 호출,url:{}" ,uri);
		
	}
	
	@Override
	public void afterCompletion (HttpServletRequest request, HttpServletResponse response,
			Object handler, Exception ex) {


		// URI 확인 (URL path를 가져온다)
		String uri = request.getRequestURI();
		
		logger.warn("######## afterCompletion 호출,uri:{}", uri);
		
	}
	
}
