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
import org.springframework.web.bind.annotation.RestController;

import com.dw.board.service.StudentsService;
import com.dw.board.vo.StudentsVO;

@RestController
@RequestMapping("/api/v1") // 중복되는 url 간략화
public class StudentsRestController {
	@Autowired
	private StudentsService studentService;
	
//	학생 저장
//	post는 body로 데이터를 받음 ==> 보안 때문에
//	위에서 @RequestMapping으로 중복되는 주소를 입력해줬기 때문에 @PostMapping("/api/v1/students")는 다음이랑 같다.
	@CrossOrigin
	@PostMapping("/students")
	public int callSaveStudents(@RequestBody StudentsVO vo) {
		return studentService.setStudents(vo);
	}
	
//	학생 조회(vo로 리턴)
//	@GetMapping("/api/v1/students")
	@GetMapping("/students")
	public List<StudentsVO> callStudentsList(){
		return studentService.getAllStudentsList();
	}
	// 학생 조회 map으로 리턴해보기
	@GetMapping("/students/map")
	public List<Map<String, Object>> callStudentsListByMap(){
		return studentService.getAllStudentsListByMap();
	}
	
//	특정 학생 조회(pk로 조회할 것임)
	@GetMapping("/students/id/{id}")
	public StudentsVO callStudents(@PathVariable("id") int studentsId) {
		return studentService.getStudents(studentsId);
	}
	
//	특정 학생 삭제
	@DeleteMapping("/students/id/{id}")
	public int callRemoveStudents(@PathVariable("id") int studentsId) {
		return studentService.deleteStudents(studentsId);
	}
	
//	특정 학생 수정
	@PatchMapping("/students/id/{id}")
	public int callUpdateStudents(@PathVariable("id") int studentsId, @RequestBody StudentsVO vo) {
		return studentService.getUpdateStudents(vo, studentsId);
	}
}