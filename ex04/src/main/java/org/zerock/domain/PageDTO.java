package org.zerock.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageDTO {
	private int startPage;//시작페이지
	private int endPage;//끝 페이지
	private boolean prev, next;//이전 , 이후 버튼 활성화
	
	private int total;//전체 글 갯수
	private Criteria cri;//페이지당갯수, 현재 페이지 번호,
	
	public PageDTO(Criteria cri, int total) {
		
		this.total = total;
		this.cri = cri;
		
		this.endPage = (int)(Math.ceil(cri.getPageNum()/10.0)) * 10;
		this.startPage = this.endPage - 9;
		
		int realEnd = (int)(Math.ceil((total*1.0)/cri.getAmount()));
		
		//마지막페이지 보정
		if(realEnd <this.endPage) {
			this.endPage = realEnd;
		}
		
		this.prev = this.startPage >1;
		this.next = this.endPage < realEnd;
		
	}
	
	
}
