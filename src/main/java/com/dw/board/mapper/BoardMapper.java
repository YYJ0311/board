package com.dw.board.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.dw.board.vo.BoardVO;

@Mapper
public interface BoardMapper {
	public int insertBoard(BoardVO vo);
	
	public List<Map<String, Object>> selectAllBoardList();
}
