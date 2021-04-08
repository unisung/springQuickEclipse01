package org.zerock.domain;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class SampleDTOList {

	private List<SampleDTO> list;
	
	/* 기본생성자에서 list 변수 초기화 처리 */
	public SampleDTOList() {
		list = new ArrayList<SampleDTO>();
	}
	
	
}
