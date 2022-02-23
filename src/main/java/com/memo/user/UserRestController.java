package com.memo.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.memo.common.EncryptUtils;
import com.memo.user.bo.UserBO;
import com.memo.user.model.User;

//api용(view가 없다!!!, 주소 겹치면 안된다)
@RestController
@RequestMapping("/user")
public class UserRestController {
	
	@Autowired
	private UserBO userBO;
	
	/**
	 * 회원가입시 아이디 중복 확인
	 * @param loginId
	 * @return
	 */
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
	
	/**
	 * 회원가입 - ajax 호출
	 * @param loginId
	 * @param password
	 * @param name
	 * @param email
	 * @return
	 */
	@PostMapping("/sign_up")
	// 완료되면 로그인 화면으로 리다이렉트
	public Map<String, Object> signUp(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			@RequestParam("name") String name,
			@RequestParam("email") String email
			) {
		
		// 비밀번호 암호화
		String encryptPassword = EncryptUtils.md5(password);
		
		// db insert
		int count = userBO.addUser(loginId, encryptPassword, name, email); // 암호화된 변수로 넣어줘야 한다
		
		Map<String, Object> result = new HashMap<>(); // 항상 여기에 디버깅 걸어주기.. 잘 넘어오는지 확인
		result.put("result", "success");
		
		if(count < 1) {
			result.put("result", "error");
		}
	
		
		return result; 
		
	}
	
	/**
	 * 로그인 - ajax 호출
	 * @param loginId
	 * @param password
	 * @param request
	 * @return
	 */
	@PostMapping("/sign_in")
	public Map<String, Object> signIn(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			HttpServletRequest request
			) {
		// db에 저장되어 있는 값과 같은지 select(where절)로 확인
		
		
		// 비밀번호 암호화를 변수에 저장 해준다
		String encrtpyUtils = EncryptUtils.md5(password); // 브레이크 포인트 걸어서 확인
		
		// loginId, 비밀번호로 DB에서 DB에서 셀렉트
		User user = userBO.getUserByLoginIdPassword(loginId, encrtpyUtils); // 암호화된 변수로 넣어줘야 한다
		
		// 결과 JSON 리턴
		Map<String, Object> result = new HashMap<>();
		result.put("result", "success");
		
		// 로그인이 성공하면 세션에 User 정보를 담는다.
		
		if (user != null) {
			// 로그인 - 세션에 저장(로그인 상태를 유지)
			// session: 로그인 판별 용도로만 써야 한다!! 많은 정보 넣으면 안된다
			HttpSession session = request.getSession();
			// 키, 값 (제일 필요한 정보 넣어주면 된다)
			session.setAttribute("userLoginId", user.getLoginId()); 
			session.setAttribute("userId", user.getId());
			session.setAttribute("userName", user.getName());
			
		} else {
			result.put("result", "error");
			result.put("errorMessage", "존재하지 않는 사용자 입니다. 관리자에게 문의해주세요.");
		}
		
		
		return result;
	}
	
	
	
	
}
