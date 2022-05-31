package com.dw.board.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageHandler {
	private int total; // 전체 게시물 수
	private int pageNum; // 현재 페이지
	private int pageSize; // 한 페이지에 있는 게시물
	private int startPage; // 현재 블럭의 첫 페이지
	private int endPage; // 현재 블럭의 마지막 페이지
	private boolean hasPreviousPage; // 이전 버튼 유무
	private boolean hasNextPage; // 다음 버튼 유무
	private int nowBlock; // 현재 블럭
	private int lastBlock; // 마지막 블럭
	private int navigatePages; // 한 블럭에 있는 페이지 수
	
	/**
	 * @return
	 * @author : YoungJoon Yoo
	 * @date : 2022. 5. 31.
	 * comment : 총 페이지 수
	 */
	public int calcPage(int total, int pageSize) {
		int pages = total / pageSize;
		if(total % pageSize > 0) ++pages;
		return pages;
	}
	
	/**
	 * 
	 * @author : YoungJoon Yoo
	 * @date : 2022. 5. 31.
	 * comment : 현재 페이지 블록 알아내기
	 */
	public void setNowBlock(int pageNum, int navigatePages) {
		this.nowBlock = pageNum / navigatePages;
		if(pageNum % navigatePages > 0) this.nowBlock++;
	}
	
	/**
	 * @param total
	 * @author : YoungJoon Yoo
	 * @date : 2022. 5. 31.
	 * comment : 마지막 블록 알아내기
	 */
	public void setLastBlock(int total) {
		this.lastBlock = total / (this.navigatePages * this.pageSize);
		if(total % (this.navigatePages * this.pageSize) > 0) this.lastBlock++;
	}
	// 현재 블럭의 처음 페이지
	public void setStartPage(int nowBlock) {
		this.startPage = (nowBlock * this.navigatePages) - (this.navigatePages -1);
	}
	// 현재 블럭의 마지막 페이지
	public void setEndPage(int nowBlock, int total) {
		this.endPage = nowBlock * this.navigatePages;
		if(nowBlock == this.lastBlock) { // 마지막 블록이라면 마지막 페이지로 총 페이지 수 대입
			this.endPage = total;
		}
	}
	
	public void setPreNext(int pagNum) {
		if(this.lastBlock == 1) { 
			setHasNextPage(false);
			setHasPreviousPage(false);
		}
		if(this.lastBlock > 1 && this.lastBlock == this.nowBlock) { 
			setHasPreviousPage(true);
			setHasNextPage(false);
		}
		if(this.lastBlock > 1 && pageNum <= this.navigatePages) { 
			setHasPreviousPage(false);
			setHasNextPage(true);
		}
	}
}