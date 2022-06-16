package com.dw.board.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author JOON
 * @date 2022. 6. 16.
 * @comment 엑셀파일을 읽어서 Workbook 객체에 리턴
 * XLS와 XLSX 확장자 비교
 */
public class ExcelFileType {
	public static Workbook getWorkbook(String filePath) {
		
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(filePath); // FileInputStream : 파일의 경로에 있는 파일을 읽어서 Byte로 가져 옴
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e); // 파일이 없는 경우 RuntimeException 발생
		}
		
		Workbook wb = null;
		if(filePath.toUpperCase().endsWith(".XLS")) {
			try {
				wb = new HSSFWorkbook(fis); // 확장자가 XLS라면, HSSFWorkbook에 초기화
			} catch (IOException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		} else if(filePath.toUpperCase().endsWith(".XLSX")) { 
			try {
				wb = new XSSFWorkbook(fis); // 확장자가 XLSX라면, XSSFWorkbook에 초기화
			} catch (IOException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}
		return wb;
	}
}
