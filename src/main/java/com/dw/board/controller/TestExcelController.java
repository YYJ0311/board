package com.dw.board.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.dw.board.service.ExcelService;

@Controller
public class TestExcelController {
	ExcelService excelService;
	
	@ResponseBody
	@RequestMapping("/excelWrite")
	public ModelAndView writeMassiveArticle(MultipartHttpServletRequest request) throws Exception{
		MultipartFile excelFile = request.getFile("excelFile");
		if(excelFile == null || excelFile.isEmpty()) {
			throw new RuntimeException("엑셀파일을 선택해 주세요.");
		}
		File destFile = new File("C:\\"+excelFile.getOriginalFilename());
		try {
			excelFile.transferTo(destFile);
		} catch(IllegalStateException | IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}

		excelService.excelUpload(destFile);
		destFile.delete();
		ModelAndView view = new ModelAndView();
		view.setViewName("");
		return view;
	}
}
