package com.memo.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component // 스프링 bean
public class FileManagerService {
	// CDN 서버 (이미지, CSS, JS): 다른 서버에 분리
	// 한번 설정하면 바꿀 수 없다(상수로 설정)
	// 워크스페이스안에다가 새로운 폴더 만들어서 저장해준다.(깃까지 들어가면 용량이 커서 복잡)
	// 경로 마지막에 / 넣어준다!!!!
	public final static String FILE_UPLOAD_PATH = "D:\\김은주\\6_spring-project\\memo\\workspace\\images/";
	// 집
	// C:\김은주java\6_spring_project\memo\workspace\images/
	// 학원 경로
	// D:\김은주\6_spring-project\memo\workspace\images/

	// 파일 업로드용 주소 -> images
	// 우리가 임의로 올리는 이미지 주소 -> image
	// 위의 주소를다르게 설정해야 한다 -> 우선적으로 파일 업로드용 주소가 뜨게 된다.

	// 파일 업로드
	public String saveFile(String userLoginId, MultipartFile file) {
		// 파일 디렉토리 경로 예: kimje205_165434132/sun.png (아이디 + 시간 + 이미지)
		// 파일명이 겹치지 않게 하기 위해 현재시간을 경로에 붙여준다.
		String directoryName = userLoginId + "_" + System.currentTimeMillis() + "/";
		String filePath = FILE_UPLOAD_PATH + directoryName;

		// 디렉토리 만들기
		File directory = new File(filePath);
		if (directory.mkdir() == false) { // makedirectory의 약자
			return null; // 디렉토리 생성 시 실패하면 null 리턴
		}

		// 파일 업로드: byte 단위로 업로드
		try {
			byte[] bytes = file.getBytes(); // 예외처리 해줘야 한다
			Path path = Paths.get(filePath + file.getOriginalFilename()); // getOriginalFilename()는 INPUT에서 올린 파일명(한글 X)
			Files.write(path, bytes);

			// 이미지 url을 리턴 (WebMvcConfig에서 매핑한 이미지 path)
			// 예)http://localhost/images/kimje205_165434132/sun.png
			return "/images/" + directoryName + file.getOriginalFilename(); // 성공

		} catch (IOException e) {
			e.printStackTrace();
		}

		return null; // 실패

	}

	// 파일 삭제
	public void deleteFile(String imagePath) throws IOException {
		// imagePath의 /images/kimje205_165434132/sun.png 에서 /images/ 를 제거한 path를 실제 저장경로
		// 뒤에 붙인다.
		Path path = Paths.get(FILE_UPLOAD_PATH + imagePath.replace("/images/", "")); // 중복된 문자열을 빈문자열로 바꿔준다. // 디버깅

		if (Files.exists(path)) { // 이미지 파일이 있으면 삭제
			Files.delete(path);

		}

		// 폴더(디렉토리) 삭제
		path = path.getParent(); // 사진의 부모인 폴더

		if (Files.exists(path)) {
			Files.delete(path);
		}

	}

}
