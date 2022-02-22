package com.memo.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.memo.user.dao.UserDAO;
import com.memo.user.model.User;

@Service
public class UserBO {
	@Autowired
	private UserDAO userDAO;

	// 중복인지 아닌지 
	public boolean existLoginId(String loginId) {
		return userDAO.existLoginId(loginId);
	}
	
	public int addUser(String loginId,String password,String name,String email) {
		return userDAO.insertUser(loginId, password, name, email);
	}
	
	// 한행을 모두 리턴하고 싶으면 객체 만들어야 한다
	// 여기서 암호화된 비번 상관없이 파라미터로 받으면 된다
	public User getUserByLoginIdPassword(String loginId, String password) {
		return userDAO.getUserByLoginIdPassword(loginId, password);
	}
}
