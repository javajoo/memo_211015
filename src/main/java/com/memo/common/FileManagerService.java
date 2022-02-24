package com.memo.common;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component  // 스프링 bean
public class FileManagerService {
	// CDN 서버 (이미지, CSS, JS): 다른 서버에 분리
	// 한번 설정하면 바꿀 수 없다(상수로 설정)
	// 워크스페이스안에다가 새로운 폴더 만들어서 저장해준다.(깃까지 들어가면 용량이 커서 복잡)
	// 경로 마지막에 / 넣어준다!!!!
	public final static String FILE_UPLOAD_PATH = "D:\\김은주\\6_spring-project\\memo\\workspace\\images/";
	
	public String saveFile(String userLoginId, MultipartFile file) {
		// 파일 디렉토리 경로 예: kimje205_165434132/sun.png (아이디 + 시간 + 이미지)
		// 파일명이 겹치지 않게 하기 위해 현재시간을 경로에 붙여준다.
		String directoryName = userLoginId + "_" + System.currentTimeMillis() + "/";
		String filePath = FILE_UPLOAD_PATH + directoryName;
		return "";
	}
	
}
