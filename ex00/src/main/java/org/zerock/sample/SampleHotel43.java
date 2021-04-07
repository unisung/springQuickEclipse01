package org.zerock.sample;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Component
@Getter//getter자동 생성
@ToString//toString 자동생성
@AllArgsConstructor//매개변수 있는 생성자 자동 생성
public class SampleHotel43 {
   private Chef chef;//spring 4.3버전 이후 @Autowired 자동 처리
   
}
