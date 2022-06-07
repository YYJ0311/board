package com.dw.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dw.board.mapper.StudentsMapper;
import com.dw.board.vo.StudentsVO;
import com.github.pagehelper.PageHelper;

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
	public List<Map<String, Object>> getAllStudentsList(int pageNum, int pageSize){
		PageHelper.startPage(pageNum, pageSize);
		return studentsMapper.selectAllStudentsList();
	}
	public List<Map<String, Object>> getAllStudentsListByMap(){
		return studentsMapper.selectAllStudentsListByMap();
	}
	
//	특정 학생 조회
	public StudentsVO getStudents(int studentsId) {
		return studentsMapper.selectStudents(studentsId);
	}
	
//	특정 학생 수정
	@Transactional(rollbackFor = {Exception.class})
	public int getUpdateStudents(StudentsVO vo, int studentsId) {
		vo.setStudentsId(studentsId);
		String password = vo.getStudentsPassword();
		password = passwordEncoder.encode(password);
		vo.setStudentsPassword(password);
		return studentsMapper.updateStudents(vo);
	}
	
//	특정 학생 삭제
	@Transactional(rollbackFor = {Exception.class})
	public int deleteStudents(int studentsId) {
		return studentsMapper.deleteStudents(studentsId);
	}
	
//	5.19
//	가입된 학생인지 여부 체크
	public boolean isStudents(StudentsVO vo) {
		StudentsVO student = studentsMapper.selectStudentsOne(vo);
		if(student == null) { // 회원이 없다면(쿼리결과가 null이면)
			return false;
		}
		String inputPassword = vo.getStudentsPassword(); // HTML에서 받아온 비밀번호
		String password = student.getStudentsPassword(); // DB에서 가져온 진짜 비밀번호
//		PasswordEncoder 안에 matches 메소드 사용
		if(!passwordEncoder.matches(inputPassword, password)) { // 비밀번호가 다르다면
//			HTML에서 받아오는 비밀번호를 encoding 한 후 DB의 비밀번호랑 비교한다.
//			따라서 만약 matches 안에 두 비밀번호가 문자 그대로 같다면(encoding 하기 전부터 같다면, 예를들어 (123,123)) matches는 false를 리턴한다.
			return false;
		}
		return true;
	}
	
	public List<Map<String, Object>> getStudentsInfo(String studentsName, int pageNum, int pageSize){
		PageHelper.startPage(pageNum, pageSize);
		return studentsMapper.selectSearchStudentsInfo(studentsName);
	}
}
