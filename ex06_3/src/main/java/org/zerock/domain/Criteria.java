package org.zerock.domain;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Criteria {
	
	private int pageNum;
	private int amount;
	
	
	//검색조건 추가
	private String type;
	private String keyword;
	
	private String y;
	private String m;
	private String id;
	
	
	public Criteria() {
		this(1,10);
	}
	
	public Criteria(int pageNum, int amount) {
		this.pageNum=pageNum;
		this.amount=amount;
	}
	
	//"TCW","T","TC","CW","TW" 검색조건 배열
	public String[] getTypeArr() {
		return type==null?new String[] {}:type.split("");//예 {"T","C","W"}, {"T"}
	}

	public String getListLink() {
		UriComponentsBuilder builder = 
				  UriComponentsBuilder.fromPath("")
				                                .queryParam("pageNum", this.pageNum)
				                                .queryParam("amount", this.amount)
				                                .queryParam("type", this.getTypeArr())
				                                .queryParam("keyword", this.getKeyword());
		return builder.toUriString();//
	}
	
	public String getCalendarLink() {
		UriComponentsBuilder builder = 
				  UriComponentsBuilder.fromPath("")
				                                .queryParam("id", this.id)
				                                .queryParam("y", this.y)
				                                .queryParam("m", this.m)
				                                ;
		return builder.toUriString();//
	}
	
}
