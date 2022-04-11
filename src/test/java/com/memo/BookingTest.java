package com.memo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class BookingTest {

	//@Autowired
	BookingBO bookingBO;
	
	
	// 단위 테스트 (간단)
	// 이것만 동작되는지 빨리 보고 싶을 때 사용한다.
	//@Test
	void test1 () {
		//assertEquals(3 * 5, 15); 참
		
		int a = 10;
		assertTrue(1 < 100); // 참
	
		
		// 수행시키는 방법  Run As -> junit test
		// 초록색 : 정상, 빨간색 : 오류
	}
	
	//@Test
	// select 테스트
	void test2 () {
		List<Booking> bookingList = bookingBO.getbookingList();
		assertNotNull(bookingList);
	}
	
	@Transactional // 복구가 되어야 되는 대상을 묶어놓은 것, 메소드 단위로 붙여주면 실행하고, 종료되면 없었던 것처럼 만들어준다.
	// 수행되는거 넣으지만 보고 싶을때 
	@Test
	// insert 테스트
	void text3 () {
		int rowCount = bookingBO.addBooking("신보람","2022-03-15",5,3,"010-1111-3333");
		assertEquals(1, rowCount);
	}
}
