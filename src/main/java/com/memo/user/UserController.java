package com.memo.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

// 화면용(view)
@Controller
@RequestMapping("/user")
public class UserController {

	//http://localhost/user/sign_up_view
	@RequestMapping("/sign_up_view")
	public String signUpView(Model model) {
		// section 내릴 때에는 이런 식으로 내려야 한다!!
		model.addAttribute("viewName","user/sign_up"); // viewName으로 키 값 내려준다.
		return "template/layout";
	}
	
	//http://localhost/user/sign_in_view
	@RequestMapping("/sign_in_view")
	public String signInView(Model model) {
		model.addAttribute("viewName","user/sign_in");
		return "template/layout";
	}
	
	@PostMapping("/sign_up_for_submit")
	// 완료되면 로그인 화면으로 리다이렉트
	public String signUpForSubmit(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			@RequestParam("name") String name,
			@RequestParam("email") String email
			) {
		
		// db insert
		
		// 리다이렉트 => 로그인 화면으로 이동
		return "redirect:/user/sign_in_view"; // redirect: 뒤에 절대경로 써주면 된다
		
	}
}
