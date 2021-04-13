package org.zerock.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor//매개변수있는 생성자
@NoArgsConstructor//기본생성자
public class SampleVO {
	private Integer mno;
	private String firstName;
	private String lastName;
}
