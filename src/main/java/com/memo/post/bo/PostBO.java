package com.memo.post.bo;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.memo.common.FileManagerService;
import com.memo.post.dao.PostDAO;
import com.memo.post.model.Post;

@Service
public class PostBO {

	// 맨위에 해준다.
	//slf4j.LoggerFactory로 임포트 해줘야 한다.
	//slf4j.Logger로 임포트 해줘야 한다.
	//private Logger logger = LoggerFactory.getLogger(PostBO.class);
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	// @Autowired 여러번 할때는 각각 해줘야 한다!!!!

	@Autowired
	private PostDAO postDAO;

	// FileManagerService의 @Component 사용하려면 새로 @Autowired 해줘야 한다!!!!
	@Autowired
	private FileManagerService fileManager;

	//alt + shift + r: 부르고 있는 애들 이름다 바꾼다
	public List<Post> getPostListByUserId(int userId) {
		return postDAO.selectPostListByUserId(userId);
	}
	
	// 단건
	// 기본이 되는 메서드
	public Post getPostById(int id) {
		return postDAO.selectPostById(id);
	}
	
	
	

	// 가공, 로직을 하는 곳
	// userId, userLoginId, subject, content, file
	public void addPost(int userId, String userLoginId, String subject, String content, MultipartFile file) {
		String imagePath = null;

		if (file != null) {
			// FileManagerService -> input: MultipartFile output: 이미지의 주소
			imagePath = fileManager.saveFile(userLoginId, file); // 디버깅
		}

		// insert DAO
		postDAO.insertPost(userId, subject, content, imagePath);

	}
	
	// 성공행수를 받고 싶으면 int, 리턴타입이 필요 없으면 void
	// 순서는 내가 정하면 된다.
	public int updatePost(int postId, String userLoginId, int userId, String subject, 
			String content, MultipartFile file) {
		
		// 공부해보고 써보는게 좋다
		//logger.error("에러 로깅 테스트");
		
		// postId에 해당하는 게시글이 있는지 DB에서 가져와본다.
	
		
		// 위에 메서드 불러올때 수정된게 포함되어 있기 때문에 메서드명을 불러오는게 안정적이다
		// 타입은 빼준다
		Post post = getPostById(postId);   //postDAO.selectPostListByUserId(userId);
		if (post == null) {
			logger.error("[update post] 수정할 메모가 존재하지 않습니다." + postId);
			return 0;
		}
		
		// 파일이 있는지 본 후 있으면 서버에 업로드하고 image path 받아온다.
		// 파일이 만약 없으면 수정하지 않는다.
		String imagePath = null;
		if (file != null) {
			imagePath = fileManager.saveFile(userLoginId, file); // 컴퓨터에 파일 업로드 후 url path를 얻어낸다.
			// 새로 업로드된 이미지가 성공하면 기존 이미지 삭제 ( 기존 이미지가 있을 때에만)
			if (post.getImagePath() != null && imagePath != null) {
				// 기존 이미지 삭제
				try {
					fileManager.deleteFile(post.getImagePath());
				} catch (IOException e) {
					// 에러났을때 logger로 남겨놓는다. {} {}에 , 로 들어간다.
					logger.error("[update post] 이미지 삭제 실패 {}, {}", post.getId(), post.getImagePath());
				} // 이전에 등록되었던 이미지 삭제
			}
		}
		
		
		// DB에서 update
		
		return postDAO.updatePostByUserIdPostId(userId, postId, subject, content, imagePath);
		
		
	}
	
	public int deletePostByUserIdPostId (int postId, int userId) { // 내 글일때만 글을 삭제해야 하니까 세션으로 받아온 파라미터 넣어준다.
		
		// 삭제 전에 게시글을 먼저 가져와 본다. (imagePath가 있을 수 있기 때문에)
		Post post = getPostById(postId); 
		
		if (post == null) {
			logger.warn("[delete post] 삭제할 메모가 존재하지 않습니다.");
			return 0; //삭제할 행이 없으니까 0 리턴해준다.
		}
		
		// imagePath가 있는 경우 파일 삭제
		if (post.getImagePath() != null) {
			// 기존 이미지 삭제
			try {
				fileManager.deleteFile(post.getImagePath());
			} catch (IOException e) {
				logger.error("[delete post] 이미지 삭제 실패 {}, {}", post.getId(), post.getImagePath());
			}
		}
		
		// DB에서 Post 삭제
		
		return postDAO.deletePostByUserIdPostId(userId, postId);
		
	}
	

}
