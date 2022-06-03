package com.dw.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dw.board.mapper.LogsMapper;
import com.dw.board.vo.LogsVO;
import com.github.pagehelper.PageHelper;

@Service
public class LogsService {
	@Autowired
	private LogsMapper logsMapper;
	
	public int setLogs(LogsVO vo) {
		return logsMapper.insertLogs(vo);
	}
	
	public List<Map<String, Object>> getLogsList(int pageNum, int pageSize){
		PageHelper.startPage(pageNum, pageSize);
		return logsMapper.selectBoardLogs(0);
	}
	
	public Map<String, Object> getLogs(int logId){
		List<Map<String, Object>> list = logsMapper.selectBoardLogs(logId);
		return list.get(0);
	}
	
	public int sangwon() {
		int array[] = {10, 20 ,30};
		int x = array[1];
		return x; // 20
	}
}
