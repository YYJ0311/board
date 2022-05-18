package com.dw.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dw.board.mapper.StudentsMapper;
import com.dw.board.vo.StudentsVO;

@Service
public class StudentsService {
	@Autowired
	private StudentsMapper studentsMapper;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
//	학생 저장
	public int setStudents(StudentsVO vo) {
		// 학생 비밀번호 암호화
		String password = vo.getStudentsPassword();
		password = passwordEncoder.encode(password);
		vo.setStudentsPassword(password);
//		암호화 후 다시 set 해줌
		return studentsMapper.insertStudents(vo);
	}
	
//	모든 학생 조회
	public List<StudentsVO> getAllStudentsList(){
		return studentsMapper.selectAllStudentsList();
	}
	public List<Map<String, Object>> getAllStudentsListByMap(){
		return studentsMapper.selectAllStudentsListByMap();
	}
	
//	특정 학생 조회
	public StudentsVO getStudents(int studentsId) {
		return studentsMapper.selectStudents(studentsId);
	}
	
//	특정 학생 삭제
	@Transactional(rollbackFor = {Exception.class})
	public int deleteStudents(int studentsId) {
		return studentsMapper.deleteStudents(studentsId);
	}
	
//	특정 학생 수정
	@Transactional(rollbackFor = {Exception.class})
	public int getUpdateStudents(StudentsVO vo, int studentsId) {
		vo.setStudentsId(studentsId);
		return studentsMapper.updateStudents(vo);
	}
}