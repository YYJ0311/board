package com.dw.board.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dw.board.mapper.BoardMapper;
import com.dw.board.utils.ExcelRead;
import com.dw.board.utils.ExcelReadOption;

@Service
public class ExcelService {
	
	@Autowired
	private BoardMapper boardMapper;
	
	public Workbook makeExcelForm() throws Exception{ // Workbook = excel
		Workbook workbook = new HSSFWorkbook(); // excel 생성
		Sheet sheet = workbook.createSheet("게시판 사용"); // sheet는 import 할 때 ss로 import 해야 함. 다운로드 받을 때, 시트 이름이 "게시판 사용"으로 됨
		Row row = null; // 엑셀 행
		Cell cell = null; // 엑셀 열
		int rowNumber = 0; // 행 번호
		
		CellStyle headStyle = makeExcelHeadStyle(workbook);
		CellStyle bodyStyle = makeExcelBodyStyle(workbook);
		
		row = sheet.createRow(rowNumber++); // 행을 1부터 생성
		cell = row.createCell(0); // 열은 0부터 시작
		cell.setCellStyle(headStyle);
		cell.setCellValue("게시판 번호"); // 첫번째 열의 값
		
		cell = row.createCell(1);
		cell.setCellStyle(headStyle);
		cell.setCellValue("작성자");

		cell = row.createCell(2);
		cell.setCellStyle(headStyle);
		cell.setCellValue("제목");
		
		cell = row.createCell(3);
		cell.setCellStyle(headStyle);
		cell.setCellValue("수정 날짜");

		cell = row.createCell(4);
		cell.setCellStyle(headStyle);
		cell.setCellValue("작성 날짜");
		
		cell = row.createCell(5);
		cell.setCellStyle(headStyle);
		cell.setCellValue("조회 수");
		
		// mapper 데이터 호출
		List<Map<String, Object>> list = boardMapper.selectAllBoardList();
		for(Map<String, Object> data : list) {
			row = sheet.createRow(rowNumber++);
			cell = row.createCell(0); // 게시판 번호
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(data.get("boardId").toString());
			
			cell = row.createCell(1); // 작성자
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(data.get("studentsName").toString());
			
			cell = row.createCell(2); // 제목
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(data.get("title").toString());
			
			cell = row.createCell(3); // 수정날짜
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(data.get("updateAt").toString());
			
			cell = row.createCell(4); // 작성날짜
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(data.get("createAt").toString());
			
			cell = row.createCell(5); // 조회수
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(data.get("cnt").toString());
		}
		
		return workbook;
	}
	// 엑셀 head style 수정
	public CellStyle makeExcelHeadStyle(Workbook workbook) {
		CellStyle cellStyle = null; 
		cellStyle = workbook.createCellStyle();
		// 가는 경계선 생성
		cellStyle.setBorderTop(BorderStyle.THIN);
		cellStyle.setBorderLeft(BorderStyle.THIN);
		cellStyle.setBorderRight(BorderStyle.THIN);
		cellStyle.setBorderBottom(BorderStyle.THIN);
		// 배경색 생성
		cellStyle.setFillForegroundColor(HSSFColor.HSSFColorPredefined.YELLOW.getIndex());
		cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		// 가운데 정렬
		cellStyle.setAlignment(HorizontalAlignment.CENTER);
		return cellStyle;
	}
	// 엑셀 body style 수정
	public CellStyle makeExcelBodyStyle(Workbook workbook) {
		CellStyle cellStyle = null; 
		cellStyle = workbook.createCellStyle();
		// 가는 경계선 생성
		cellStyle.setBorderTop(BorderStyle.THIN);
		cellStyle.setBorderLeft(BorderStyle.THIN);
		cellStyle.setBorderRight(BorderStyle.THIN);
		cellStyle.setBorderBottom(BorderStyle.THIN);
		// 배경색 생성
		cellStyle.setFillForegroundColor(HSSFColor.HSSFColorPredefined.LIGHT_GREEN.getIndex());
		cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		// 가운데 정렬
		cellStyle.setAlignment(HorizontalAlignment.CENTER);
		return cellStyle;
	}
	
	// 엑셀 업로드
	public void excelUpload(File destFile) throws Exception{
		ExcelReadOption excelReadOption = new ExcelReadOption();
		excelReadOption.setFilePath(destFile.getAbsolutePath());
		excelReadOption.setOutputColumns("A", "B", "C", "D", "E", "F");
		excelReadOption.setStartRow(2);
		
		List<Map<String, String>> excelContent = ExcelRead.read(excelReadOption);
		for(Map<String, String> article: excelContent) {
			System.out.println(article.get("A"));
			System.out.println(article.get("B"));
			System.out.println(article.get("C"));
			System.out.println(article.get("D"));
			System.out.println(article.get("E"));
			System.out.println(article.get("F"));
		}
	}
}
