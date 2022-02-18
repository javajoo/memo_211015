package com.memo.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

	//http://localhost:80/user/sign_up_view
	@RequestMapping("/sign_up_view")
	public String signUpView(Model model) {
		// section 내릴 때에는 이런 식으로 내려야 한다!!
		model.addAttribute("viewName","user/sign_up"); // viewName으로 키 값 내려준다.
		return "template/layout";
	}
}
