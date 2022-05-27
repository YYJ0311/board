package com.dw.board.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dw.board.vo.BoardVO;

@Mapper
public interface BoardMapper {
	public int insertBoard(BoardVO vo);
	
	public List<Map<String, Object>> selectAllBoardList();
	
	public int updateBoard(BoardVO vo);
	
	public int deleteBoard(int boardId);
	
	public BoardVO openDetailedBoard(int boardId);
	
	public int updateBoardViews(BoardVO vo);
	
	public List<Map<String, Object>> selectSearchBoardList(@Param("studentsName") String studentsName);
//	파라미터가 1개일 땐 @Param 생략가능. 2개 이상이면 써줘야 한다.
}
