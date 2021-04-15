package org.zerock.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.SampleVO;
import org.zerock.domain.Ticket;

import lombok.extern.log4j.Log4j;

/* RESTful API 컨트롤러 어노테이션 */
@RestController // jsp파일로 데이타 전송을 하지않고 객체를 리턴하는 컨트롤러
@RequestMapping("/sample")
@Log4j
public class SampleController {
	
	//String문자열 리턴 
	@GetMapping(value="/getText", produces = "text/plain;charset=UTF-8")
	public String getText() {
		
		log.info("MIME TYPE: " + MediaType.TEXT_PLAIN_VALUE);
		
		return "안녕하세요";
	}
	
	//사용자 정의 객체 리터
	@GetMapping(value="/getSample",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE,
			                                                          MediaType.APPLICATION_XML_VALUE})
  public SampleVO getSample() {
		return new SampleVO(112, "스타","로드");
	}
	
	//Collection타입 객체 리터
	@GetMapping(value="/getList")
	public List<SampleVO> getList(){
		List<SampleVO> list=new ArrayList<SampleVO>();
		 for(int i=0;i<9;i++) {
			 list.add(new SampleVO(i+1, (i+1)+"First", (i+1)+"Last"));
		 }
		 
		 return list;
		
	//	return IntStream.range(1,10)
	//			 .mapToObj(i->new SampleVO(i, i+"First", i+"Last"))
	//			 .collect(Collectors.toList());
	}
	
	//Map타입 객체 리턴
	@GetMapping(value="/getMap")
	public Map<String, SampleVO> getMap(){
		Map<String,SampleVO> map = new HashMap<>();
		map.put("First", new SampleVO(111, "그루트", "주니어"));
		return map;
	}
	
	//ResponseEntity객체 타입 리턴
	@GetMapping(value ="/check", params= {"height","weight"})
	public ResponseEntity<SampleVO> check(Double height, Double weight){
		SampleVO vo = new SampleVO(0, ""+height, ""+weight);
		
		ResponseEntity<SampleVO> result = null;
		
		if(height < 150) {
			result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(vo);
		}else {
			result = ResponseEntity.status(HttpStatus.OK).body(vo);
		}
		
		return result;
	}
	
	
	//2. 컨트롤러로 넘어오는 파라미터 수집방법
	/*1. pathvariable */
	@GetMapping("/product/{cat}/{pid}") // http://localhost:8580/sample/product/phone/123
	/*/ product.do?cat=사과&pid=123 -> /product/사과/123 */
	public String[] getPath(
			@PathVariable("cat") String cat,
			@PathVariable("pid") Integer pid
			) {
		return new String[] {"category: "+cat, "productid: "+pid};
	}
	
	/*2. 클라이언트쪽에서 JSON 데이타로 요청시 파라미터 수집 방법 */
	@PostMapping("/ticket")
	public Ticket covert(@RequestBody Ticket ticket) {//json파라미터를 자바객체에 매핑 @RequestBody
		 log.info("convert....... ticket" + ticket);
		 
		 return ticket;
	}
	
	
}
