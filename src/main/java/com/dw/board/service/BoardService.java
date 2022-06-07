package com.dw.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dw.board.mapper.BoardMapper;
import com.dw.board.vo.BoardVO;
import com.github.pagehelper.PageHelper;

@Service
public class BoardService {
	@Autowired
	private BoardMapper boardMapper;
	
//	보드 저장
	public int setBoard(BoardVO vo) {
		return boardMapper.insertBoard(vo);
	}
//	보드 조회
//	pageNum : 현재 페이지, pageSize : 한 페이지에 게시물 몇개 보여줄지
	public List<Map<String, Object>> getAllBoardList(int pageNum, int pageSize){
		// 이클립스에서 만들었던 페이징 처리가 아래 한 줄에 전부 들어있음
		PageHelper.startPage(pageNum, pageSize);
		return boardMapper.selectAllBoardList();
	}
//	보드 수정
	@Transactional(rollbackFor = {Exception.class})
	public int getUpdateBoard(int boardId, BoardVO vo) {
		vo.setBoardId(boardId);
		return boardMapper.updateBoard(vo);
	}
//	보드 삭제
	@Transactional(rollbackFor = {Exception.class})
	public int deleteBoard(int boardId) {
		return boardMapper.deleteBoard(boardId);		
	}
//	게시판 상세보기
	public BoardVO getDetail(int boardId) {
		return	boardMapper.openDetailedBoard(boardId);
	}
//	조회수 업데이트
	public int getUpdateCnt(int boardId) {
		// 1. 보드 번호를 이용해서 조회수 컬럼을 select
		BoardVO vo = boardMapper.openDetailedBoard(boardId);
		// 2. 조회수 증가
		int views = vo.getCnt();
		++views; 
		vo.setCnt(views);
		vo.setBoardId(boardId);
		// 3. 업데이트
		return boardMapper.updateBoardViews(vo);
	}
//	studentsName로 보드 조회
	public List<Map<String, Object>> getSearchBoardList(String studentsName, int pageNum, int pageSize){
		PageHelper.startPage(pageNum, pageSize);
		return boardMapper.selectSearchBoardList(studentsName);
	}

//	통계값(학생 수, 게시글 수, 작성자 수, 총 조회 수) 조회
	public Map<String, Object> getBoardStatistics(){
		return boardMapper.selectBoardStatistics();
	}
}
