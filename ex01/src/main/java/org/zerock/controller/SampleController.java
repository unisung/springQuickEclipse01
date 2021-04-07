package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.zerock.domain.SampleDTO;

import lombok.extern.log4j.Log4j;


@Controller
@RequestMapping("/sample/*")
@Log4j
public class SampleController {
 
	// @RequestMapping("")  // /sample/basic -> 이동할 jsp는 sample폴더의 basic.jsp로 이동
	// public void basic() {
	//	 log.info("basic............................");
	// }
	
	@RequestMapping(value="/basic",method={RequestMethod.GET,RequestMethod.POST})
	public void basicGet() {
		log.info("basic get................................");
	}
	
    /* spring 4.3 이후 @GetMapping, @PostMapping 적용가능 */
	@GetMapping("/basicOnlyGet") // /sample/basicOnlyGet
	public void basicGet2() {
		log.info("basic get only get..............................");
	}
	
	/* 파라미터가 속성명과 이름이 같으면 값이 세팅되어서 객체로 넘어옴 */
	/* 파라미터로 넘어온 값으로 생성된 객체는 jsp까지 전달됨. 
	 * jsp에서는 해당타입명의 첫 글자가 소문자로 전달됨 SampleDTO -> sampleDTO로 전달.*/
	@GetMapping("/ex01") // /sample/ex01
	public String ex01(SampleDTO dto) {
		log.info(" " +dto);
		return "ex01";//이동할 jsp경로
	}
	
	@GetMapping("/ex02") //  /sample/ex02
	public String ex02(@RequestParam("name") String name,
			                   @RequestParam("age") int age) {
		log.info("name:"+name);
		log.info("age: "+age);
		
		return "ex02";
	}
	
	/* @ModelAttribute("age")로 설정하면 기본타입값을 jsp로 전달 가능 */
	@GetMapping("/ex04") // /sample/ex01
	public String ex04(SampleDTO dto, @ModelAttribute("age") int age) {
		log.info(" " +dto);
		log.info("age:"+age);
		return "ex04";//이동할 jsp경로
	}
	
}
