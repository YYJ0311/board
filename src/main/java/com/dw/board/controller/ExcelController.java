package com.dw.board.controller;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.dw.board.service.ExcelService;

/**
 * @author 29
 * @create-date : 22.06.16
 * comment : excel 다운로드 받는 컨트롤러
 */
@Controller
public class ExcelController {
	@Autowired
	private ExcelService excelService;
	
	// 엑셀, 사진, 한글 PDF, 영상 등등 return type 없음 => return타입을 void 또는 ResponseEntity 으로 함.
	@GetMapping("/excel")
	public void downloadExcelFile(HttpServletResponse response) throws Exception{
		String today = new SimpleDateFormat("yyMMDD").format(new Date()); // util로 import
		String title = "게시판자료";
		
		response.setContentType("ms-vnd/excel"); // 엑셀 파일을 보내라!
        response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(today+"_"+title,"UTF-8")+".xls"); // URLEncoder java net으로 import. excel 파일 이름 설정
        Workbook workBook = excelService.makeExcelForm();
        workBook.write(response.getOutputStream());
        workBook.close();
        
        response.getOutputStream().flush();
        response.getOutputStream().close();
	}
}
