package com.dw.board.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.dw.board.utils.ExcelReadOption;

@Controller
public class TestExcelController {
//	@RequestMapping("/board/excelWrite")
//	public ModelAndView writeMassiveArticle(MultipartHttpServletRequest request){
////		MultipartFile excelFile = request.getFile("excelFile");
////		if(excelFile == null || excelFile.isEmpty()) {
////			throw new RuntimeException("엑셀파일을 선택해 주세요.")
////		}
////		File destFile = new File("D:\\"+excelFile.getOriginalFilename());
////		try {
////			excelFile.transferTo(destFile);
////		} catch(IllegalStateException | IOException e) {
////			throw new RuntimeException(e.getMessage(), e);
////		}
////		
////        boardService.insertMassiveArticleInBoard(destFile);
////        
////        FileUtils.deleteFile(destFile.getAbsolutePath());
////        
////        ModelAndView view = new ModelAndView();
////        view.setViewName("redirect:/board/list");
//        return null;
//    }
}
