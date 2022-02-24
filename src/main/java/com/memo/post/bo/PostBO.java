package com.memo.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.memo.common.FileManagerService;
import com.memo.post.dao.PostDAO;
import com.memo.post.model.Post;

@Service
public class PostBO {
	
	//@Autowired 여러번 할때는 각각 해줘야 한다!!!!
	
	@Autowired
	private PostDAO postDAO;
	
	// FileManagerService의 @Component 사용하려면 새로 @Autowired 해줘야 한다!!!!
	@Autowired
	private FileManagerService fileManager;

	public List<Post> getPostList() {
		return postDAO.selectPostList();
	}
	
	// 가공, 로직을 하는 곳
	// userId, userLoginId, subject, content, file
	public void addPost(int userId, String userLoginId, String subject, String content, MultipartFile file) {
		String imagePath = null;
		
		if (file != null) {
			// FileManagerService  ->    input: MultipartFile     output: 이미지의 주소
			imagePath = fileManager.saveFile(userLoginId, file); // 디버깅
		}
		
		// insert DAO
		postDAO.insertPost(userId, subject, content, imagePath);
		
	}
	
}
