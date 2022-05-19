package com.dw.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.dw.board.vo.BoardVO;

@Mapper
public interface BoardMapper {
	public int insertBoard(BoardVO vo);
	
	public List<BoardVO> selectAllBoardList();
}
