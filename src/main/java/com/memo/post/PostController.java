package com.memo.post;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	// 로그인 안되어있으면 주소로 안들어가진다
	//localhost/post/post_list_view
	@RequestMapping("/post_list_view")
	public String postListView(
			Model model, 
			HttpServletRequest request,
			@RequestParam(value= "prevId", required = false) Integer prevIdParam,
			@RequestParam(value= "nextId", required = false) Integer nextIdParam
			) {
		
		// 글쓴이 정보를 가져오기 위해 세션에서 userId를 꺼낸다.
		HttpSession session = request.getSession();
		int userId = (int) session.getAttribute("userId");
		// 글목록 DB에서 가져오기
		List<Post> postList = postBO.getPostListByUserId(userId,prevIdParam, nextIdParam); // 디버깅
		
		int prevId = 0;
		int nextId = 0;
		if (CollectionUtils.isEmpty(postList) == false) { // postList가 없는 경우 에러 방지
			prevId = postList.get(0).getId(); // 리스트 중 가장 앞쪽 ( 제일 큰 값) id
			nextId = postList.get(postList.size()-1).getId(); // 리스트 중 가장 뒤쪽 ( 제일 작은 값) id
			
			// 이전이나 다음이 없는 경우 nextId, prevId를 0으로 세팅한다.
			
			// 마지막 페이지 (다음 방향 끝)-> nextId를 0으로  
			if (postBO.isLastPage(userId, nextId)) {
				nextId = 0;
			}
			
			// 첫 페이지(이전 방향 끝) -> prevId를 0으로
			if (postBO.isFirstPage(userId, prevId)) {
				prevId = 0;
			}
		}
		
		
		// model.add
		model.addAttribute("postList",postList);
		model.addAttribute("prevId",prevId);
		model.addAttribute("nextId",nextId);
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

	// 로그인 안되어있으면 주소로 안들어가진다
	//localhost/post/post_detail_view
	@RequestMapping("/post_detail_view")
	public String postDetailView(
			Model model,
			@RequestParam("postId") int postId
			) {
		
		// postId에 해당하는 글을 가져옴
		
		Post post = postBO.getPostById(postId); //디버깅
		
		model.addAttribute("post",post); // 키 : 값 
		// "post" : 내가 내릴 이름 , post: 객체 
		model.addAttribute("viewName","post/post_detail");
		return "template/layout";
	}
	
	
	
}
