package com.dw.board.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

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

import com.dw.board.service.StudentsService;
import com.dw.board.vo.StudentsVO;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/api/v1") // 중복되는 url 간략화
public class StudentsRestController {
	@Autowired
	private StudentsService studentService;
	
//	중요한 정보를 서버에 전송할 때 POST 사용
	@CrossOrigin
	@PostMapping("/login")
	public boolean callIsLogin(@RequestBody StudentsVO vo, HttpSession httpSession) {
		boolean isLogin = studentService.isStudents(vo);
		if(isLogin) {
			httpSession.setAttribute("name", "YooYoungJoon"); // session에 name 추가. (key, value)로 입력.
		}
		return studentService.isStudents(vo);
	}
	
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
	@CrossOrigin
	@GetMapping("/students")
	public PageInfo<Map<String, Object>> callStudentsList(@RequestParam("pageNum") int pageNum, 
			@RequestParam("pageSize") int pageSize){
		List<Map<String, Object>> list = studentService.getAllStudentsList(pageNum, pageSize);
		return new PageInfo<Map<String, Object>>(list);
	} 
//	http://localhost:8080/api/v1/students?pageNum=1&pageSize=10
	
	// 학생 조회 map으로 리턴해보기
	@CrossOrigin
	@GetMapping("/students/map")
	public List<Map<String, Object>> callStudentsListByMap(HttpSession httpSession){
//		세션 데이터 가져오기. 나중에 할 예정
//		String name = (String)httpSession.getAttribute("name"); // key값으로 get함
//		System.out.println("세션에서 가져온 이름은 ==> "+name); // session에 저장했던 value가 출력됨
//		if(name == null) { // 로그인을 안 했거나 session에 저장되지 않은 경우에 null을 return
//			return null;
//		}
		return studentService.getAllStudentsListByMap();
	}
	
//	특정 학생 조회(pk로 조회할 것임)
	@CrossOrigin
	@GetMapping("/students/id/{id}")
	public StudentsVO callStudents(@PathVariable("id") int studentsId) {
		return studentService.getStudents(studentsId);
	}
	
//	특정 학생 삭제
	@CrossOrigin
	@DeleteMapping("/students/id/{id}")
	public int callRemoveStudents(@PathVariable("id") int studentsId) {
		return studentService.deleteStudents(studentsId);
	}
	
//	특정 학생 수정
	@CrossOrigin
	@PatchMapping("/students/id/{id}")
	public int callUpdateStudents(@PathVariable("id") int studentsId, @RequestBody StudentsVO vo) {
		return studentService.getUpdateStudents(vo, studentsId);
	}
	
//	쿼리 스트링을 사용해서 검색한 학생 게시판 리스트 조회
//	/students/search?name=작성자이름 로 조회가능
//	작성자이름을 빈칸으로 하면 전체조회가 된다.
	@CrossOrigin
	@GetMapping("/students/search")
	public PageInfo<Map<String, Object>> callStudentsSearch(@RequestParam("name") String StudentsName, @RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize){
		List<Map<String, Object>> list = studentService.getStudentsInfo(StudentsName, pageNum, pageSize);
//		return studentService.getStudentsInfo(StudentsName);
		return new PageInfo<Map<String, Object>>(list);
	}
}