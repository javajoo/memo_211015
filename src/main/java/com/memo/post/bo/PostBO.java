package com.memo.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.memo.post.dao.PostDAO;
import com.memo.post.model.Post;

@Service
public class PostBO {
	
	@Autowired
	private PostDAO postDAO;

	public List<Post> getPostList() {
		return postDAO.selectPostList();
	}
	
	// 가공, 로직을 하는 곳
	// userId, userLoginId, subject, content, file
	public void addPost(int userId, String userLoginId, String subject, String content, MultipartFile file) {
		String imagePath = null;
		
		if (file != null) {
			
		}
		
		// insert DAO
	}
	
}
