package com.memo.post;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.memo.post.bo.PostBO;

@RestController
@RequestMapping("/post")
public class PostRestController {

	@Autowired
	private PostBO postBO;
	
	/*
	 * // 테스트용 컨트롤러
	 * 
	 * @RequestMapping("/posts") public List<Post> posts() { List<Post> postList =
	 * postBO.getPostList(); // 디버깅으로 확인가능 return postList; }
	 */

	/**
	 * 글쓰기
	 * @param subject
	 * @param content
	 * @param file
	 * @param request
	 * @return
	 */
	@PostMapping("/create")
	public Map<String, Object> create(
			@RequestParam("subject") String subject,
			@RequestParam(value ="content", required = false) String content,
			@RequestParam(value ="file", required = false) MultipartFile file,
			HttpServletRequest request
			) {
		
		
		Map<String, Object> result = new HashMap<>(); // 서버에서 파라미터 잘 넘어오는지 가장먼저 확인!! 브레이크포인트 확인
		result.put("result", "success");
		
		// 글쓴이 정보를 가져온다. (세션에서)
		
		HttpSession session = request.getSession();
		// 꺼낼 때는 getAttribute로 해준다.
		// null이 들어갈 수도 있기 때문에 Integer로 캐스팅해주고 저장도 Integer로 해준다.
		Integer userId = (Integer)session.getAttribute("userId");
		String userLoginId = (String)session.getAttribute("userLoginId");
		
		if (userId == null) { // 로그인 되어있지 않음
			result.put("result", "error");
			result.put("errorMessage", "로그인 후 사용 가능합니다.");
			return result;
		}
		
		// userId, userLoginId, subject, content, file -> BO insert 요청
		postBO.addPost(userId, userLoginId, subject, content, file);
		
		
		
		return result;
	}
	
	/**
	 * 글 수정
	 * @param postId
	 * @param subject
	 * @param content
	 * @param file
	 * @param request
	 * @return
	 */
	@PutMapping("/update")
	public Map<String, Object> update(
			@RequestParam("postId") int postId, // 누구의 id인지 테이블명 앞에 붙여줘야 한다
			@RequestParam("subject") String subject,
			@RequestParam(value="content", required = false) String content,
			@RequestParam(value="file", required = false) MultipartFile file,
			HttpServletRequest request
			) {
		
		
 		HttpSession session = request.getSession(); // 디버깅
		
		// 로그인될때 저장해둔 세션을 get으로 꺼내온다.
		String userLoginId = (String) session.getAttribute("userLoginId");
		int userId = (int) session.getAttribute("userId"); // 필수값, null이면 안됨!!
		
		Map<String, Object> result = new HashMap<>();
		result.put("result", "success");
		
		// update DB
		int row = postBO.updatePost(postId, userLoginId, userId, subject, content, file);
		if (row < 1) {
			result.put("result", "error");
			result.put("errorMessage", "메모 수정에 실패했습니다.");
		}
		
		return result;
		
		
	}
	
	@DeleteMapping("/delete")
	public Map<String, Object> delete(
			@RequestParam("postId") int postId,
			HttpServletRequest request
			) {
		
		HttpSession session = request.getSession(); // 디버깅
		int userId = (int)session.getAttribute("userId");
		
		Map<String, Object> result = new HashMap<>();
		
		result.put("result", "success");
		
		int row = postBO.deletePostByUserIdPostId(postId, userId);
		
		if (row < 1) {
			result.put("result", "error");
			result.put("errorMessage", "삭제하는데 실패했습니다.");
		}

		return result;
		
	}
	
	
}



