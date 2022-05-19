package com.dw.board.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.dw.board.vo.StudentsVO;

@Mapper
public interface StudentsMapper {
	/**
	 * @param vo
	 * @return
	 * @author : YoungJoon Yoo
	 * @date : 2022. 5. 18.
	 * comment : 학생 추가
	 */
	public int insertStudents(StudentsVO vo);
	
	/**
	 * @return
	 * @author : YoungJoon Yoo
	 * @date : 2022. 5. 18.
	 * comment : 학생 조회
	 */
	public List<StudentsVO> selectAllStudentsList();
	public List<Map<String, Object>> selectAllStudentsListByMap();
	public StudentsVO selectStudents(int studentsId);
	
	public int deleteStudents(int studentsId);
	
	
	/**
	 * @param vo
	 * @return
	 * @author : YoungJoon Yoo
	 * @date : 2022. 5. 18.
	 * comment : 학생 수정
	 */
	public int updateStudents(StudentsVO vo);
	
	
//	5.19
	/**
	 * @param vo
	 * @return
	 * @author : YoungJoon Yoo
	 * @date : 2022. 5. 19.
	 * comment : 학생 이름으로 학생정보 조회
	 */
	public StudentsVO selectStudentsOne(StudentsVO vo);
}
