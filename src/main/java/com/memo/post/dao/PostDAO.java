package com.memo.post.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.memo.post.model.Post;

@Repository
public interface PostDAO {

	// select 부분 묶기
	public List<Post> selectPostListByUserId(
			@Param("userId") int userId, 
			@Param("direction") String direction,  
			@Param("standardId") Integer standardId, 
			@Param("limit") int limit);
	
	public Post selectPostById(int id);
	
	
	//int userId, String userLoginId, String subject, String content, MultipartFile file
	public void insertPost(
			@Param("userId") int userId,
			@Param("subject") String subject,
			@Param("content") String content,
			@Param("imagePath") String imagePath
			);
	
	public int updatePostByUserIdPostId( // where절에 들어가는 필드는 메서드명뒤에 붙여준다.
			@Param("userId") int userId,
			@Param("postId") int postId, 
			@Param("subject") String subject,
			@Param("content") String content,
			@Param("imagePath") String imagePath
			);
	
	public int deletePostByUserIdPostId (
			@Param("userId") int userId,
			@Param("postId") int postId
			);
	
	public boolean isLastPage(
			@Param("") int userId,
			@Param("") int nextId);
	
	public int selectPostByUserIdSort(
			@Param("userId") int userId, 
			@Param("sort") String sort);
	
}
