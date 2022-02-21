package com.memo.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.memo.common.EncryptUtils;
import com.memo.user.bo.UserBO;

//api용(view가 없다!!!, 주소 겹치면 안된다)
@RestController
@RequestMapping("/user")
public class UserRestController {
	
	@Autowired
	private UserBO userBO;
	
	@RequestMapping("/is_duplicated_id")
	public Map<String, Object> isDuplicatedId(
			@RequestParam("loginId") String loginId) {
		
		Map<String, Object> result = new HashMap<>();
		boolean existLoginId = userBO.existLoginId(loginId);
		
		result.put("result", existLoginId); // id가 이미 존재하면 true
		
//		if (existLoginId == true) { 
//			result.put("result", true);
//		} else {
//			result.put("result", false);
//		}
		
		return result;
	}
	
	@PostMapping("/sign_up")
	// 완료되면 로그인 화면으로 리다이렉트
	public Map<String, Object> signUpForSubmit(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			@RequestParam("name") String name,
			@RequestParam("email") String email
			) {
		
		// 비밀번호 암호화
		String encryptPassword = EncryptUtils.md5(password);
		
		// db insert
		int count = userBO.addUser(loginId, encryptPassword, name, email);
		
		Map<String, Object> result = new HashMap<>(); // 항상 여기에 디버깅 걸어주기.. 잘 넘어오는지 확인
		result.put("result", "success");
		
		if(count < 1) {
			result.put("result", "error");
		}
	
		
		return result; 
		
	}
	
}
