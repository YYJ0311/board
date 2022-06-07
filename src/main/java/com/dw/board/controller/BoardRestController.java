package com.dw.board.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dw.board.service.BoardService;
import com.dw.board.vo.BoardVO;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/api/v1")
public class BoardRestController {
//	@GetMapping("/")
//	public String callHello(){
//		return "Hello World";
//	}
	@Autowired
	private BoardService boardService;
	
//	게시판 저장(C)
	@CrossOrigin
	@PostMapping("/board")
	public int callSaveBoard(@RequestBody BoardVO vo) {
		return boardService.setBoard(vo);
	}
	
//	게시판 조회(R)
	@CrossOrigin
	@GetMapping("/board")
	public PageInfo<Map<String, Object>> callBoardList(@RequestParam("pageNum") int pageNum, 
			@RequestParam("pageSize") int pageSize){
//		리턴 타입을 list에서 PageInfo로 변경함
		List<Map<String, Object>> list = boardService.getAllBoardList(pageNum, pageSize);
		return new PageInfo<Map<String, Object>>(list);
	}
//	http://localhost:8080/api/v1/board?pageNum=1&pageSize=10
	
//	게시판 수정(U)
	@CrossOrigin
	@PatchMapping("/board/boardId/{id}")
	public int callUpdateBoard(@PathVariable("id") int boardId, @RequestBody BoardVO vo) {
		return boardService.getUpdateBoard(boardId, vo);
	}
	
//	게시판 삭제(D)
	@CrossOrigin
	@DeleteMapping("/board/boardId/{id}")
	public int callRemoveBoard(@PathVariable("id") int boardId) {
		return boardService.deleteBoard(boardId);
	}
	
//	게시판 상세보기
	@CrossOrigin
	@GetMapping("/board/boardId/{id}")
	public BoardVO callBoard(@PathVariable("id") int boardId) {
		return boardService.getDetail(boardId);
	}
	
//	게시물 조회수 카운트
	@CrossOrigin
	@PatchMapping("/board/views/boardId/{id}")
	public int callBoardViews(@PathVariable("id") int boardId) {
		System.out.println(boardId);
		return boardService.getUpdateCnt(boardId);
	}
	
//	쿼리 스트링으로 검색한 작성자 게시판 리스트 조회
//	/board/search?writer=작성자이름 로 조회가능
//	작성자이름을 빈칸으로 하면 전체조회가 된다.
	@CrossOrigin
	@GetMapping("/board/search")
	public PageInfo<Map<String, Object>> callBoardSearch(@RequestParam("writer") String writer, @RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize){
		List<Map<String, Object>> list = boardService.getSearchBoardList(writer, pageNum, pageSize);
		return new PageInfo<Map<String, Object>>(list);
	}

//	게시판 통계 조회
	@CrossOrigin
	@GetMapping("/board/boardstatistics")
	public Map<String, Object> callBoardStatics(){
		return boardService.getBoardStatistics();		
	}
}

