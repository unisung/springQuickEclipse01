package org.zerock.sample;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Component
@Getter//getter자동 생성
@ToString//toString 자동생성
//@AllArgsConstructor//매개변수 있는 생성자 자동 생성
@RequiredArgsConstructor//필요한 생성자 지정
public class SampleHotelNonNull {
	
  @NonNull//반드시 필요한 속성에만 적용하는 경우
  private Chef chef;//spring 4.3버전 이후 @Autowired 자동 처리
   
}
