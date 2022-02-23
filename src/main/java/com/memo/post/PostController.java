package com.memo.post;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/post")
public class PostController {

	//localhost/post/post_create_view
	@RequestMapping("/post_create_view")
	public String postCreateView(Model model) {
		model.addAttribute("viewName","post/post_create");
		return "template/layout";
	}
	
	@RequestMapping("/post_list_view")
	public String postListView(Model model) {
		model.addAttribute("viewName","post/post_list");
		return "template/layout";
	}
}
