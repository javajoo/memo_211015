package com.memo.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.memo.post.bo.PostBO;
import com.memo.post.model.Post;

@Controller
@RequestMapping("/post")
public class PostController {

	// postmvc 만든거 재사용할때는 controller에서 @Autowired만 해주고 model에 담아주기만 사면 재사용 된다!!!!!
	@Autowired
	private PostBO postBO;

	/**
	 * 글 목록 화면
	 * @param model
	 * @return
	 */
	//localhost/post/post_list_view
	@RequestMapping("/post_list_view")
	public String postListView(Model model) {
		
		// 글목록 DB에서 가져오기
		List<Post> postList = postBO.getPostList();
		
		// model.add
		model.addAttribute("postList",postList);
		
		model.addAttribute("viewName", "post/post_list");
		return "template/layout";
	}


	/**
	 * 글쓰기 화면
	 * @param model
	 * @return
	 */
	//localhost/post/post_create_view
	@RequestMapping("/post_create_view")
	public String postCreateView(Model model) {
		model.addAttribute("viewName", "post/post_create");
		return "template/layout";
	}

	//localhost/post/post_detail_view
	@RequestMapping("/post_detail_view")
	public String postDetailView(Model model) {
		model.addAttribute("viewName","post/post_detail");
		return "template/layout";
	}
	
	
	
}
