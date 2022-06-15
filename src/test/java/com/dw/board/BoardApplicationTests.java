package com.dw.board;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.dw.board.mapper.BoardMapper;
import com.dw.board.service.StudentsService;
import com.dw.board.utils.PageHandler;

@SpringBootTest
class BoardApplicationTests {
	
	@Autowired
	private StudentsService service;
	@Autowired
	private PageHandler pageHandler;
	@Autowired
	private BoardMapper boardMapper;
	
	@Test
	void contextLoads() {
		System.out.println("hello world");
		// 학생 전체 조회
//		List<Map<String, Object>> list = service.getAllStudentsListByMap();
//		for(Map<String, Object> map : list) {
//			System.out.println(map);
//		}
//		Map<String, Object> map = boardMapper.selectAllBoardTotal();
		int total = boardMapper.selectAllBoardTotal(); // SQL에서 count로 가져오는 값. 전체 게시물 수
		System.out.println(total);
		int pageNum = 1;
		int pageSize = 10;
		int navigatePages = 5; // 한 블럭에 페이지 5개
		pageHandler.setTotal(total); // pageHandler의 total값을 지정해줌
		pageHandler.setPageNum(pageNum);
		pageHandler.setPageSize(pageSize);
		pageHandler.setNavigatePages(navigatePages);
		
		pageHandler.setNowBlock(pageNum); // pageNum으로 현재 블럭 구함
											// 파라미터로 navigatePages를 받지 않지만, 바로 위에서 navigatePages를 set 해줘서 사용 가능함
		int nowBlock = pageHandler.getNowBlock();
		System.out.println("현재 블록 => "+ nowBlock);
		
		pageHandler.setLastBlock(total); // total로 마지막 블럭 구함
		int lastBlock = pageHandler.getLastBlock();
		System.out.println("마지막 블록 => "+ lastBlock);
		
		pageHandler.setStartPage(nowBlock); // nowBlock으로 
		int startPage = pageHandler.getStartPage();
		System.out.println("시작 페이지 => "+ startPage);
		
		int pages = pageHandler.calcPage(total, pageSize);
		pageHandler.setEndPage(nowBlock, pages);
		int lastPage = pageHandler.getEndPage();
		System.out.println("마지막 페이지 => "+ lastPage);
		
		pageHandler.setPreNext(pageNum);
		boolean hasPreviousPage = pageHandler.isHasPreviousPage(); 
		boolean hasNextPage = pageHandler.isHasNextPage();
		System.out.println("이전 버튼 유무 => "+ hasPreviousPage);
		System.out.println("다음 버튼 유무 => "+ hasNextPage);
		
		int limitStarts = ((pageNum - 1) * pageSize);
		List<Map<String, Object>> list = boardMapper.selectAllBoardListTest(limitStarts,pageSize);
		
		for(Map<String, Object> param : list) {
			String studentsName = (String) param.get("studentsName");
			System.out.println(studentsName);
		}
	}
}
