package org.zerock.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.SampleDTO;
import org.zerock.domain.SampleDTOList;
import org.zerock.domain.TodoDTO;

import lombok.extern.log4j.Log4j;


@Controller
@RequestMapping("/sample/*")
@Log4j
public class SampleController {
	//@InitBinder
	//public void initBinder(WebDataBinder binder) {
	//	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	//	binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dateFormat, false));
	//}
 
	// @RequestMapping("")  // /sample/basic -> 이동할 jsp는 sample폴더의 basic.jsp로 이동
	// public void basic() {
	//	 log.info("basic............................");
	// }
	
	@RequestMapping(value="/basic",method={RequestMethod.GET,RequestMethod.POST})
	public void basicGet() {
		log.info("basic get................................");
		/* return 경로가 없고, 자동으로 /sample/basic.jsp로 이동 */
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
		/*
		 * String name=request.getParameter("name"); int age =
		 * request.getParameter("age"); SampleDTO dto=new SampleDTO();
		 * dto.setName(name); dto.setAge(age);
		 */
		log.info(" " +dto);
		return "ex01";//이동할 jsp경로 ${sampleDTO}
	}
	
	@GetMapping("/ex02") //  /sample/ex02
	public String ex02(@RequestParam("name") String name,
			                   @RequestParam("age") int age) {//기본타입은 jsp로 전달불가
		log.info("name:"+name);
		log.info("age: "+age);
		
		return "ex02";
	}
	
	/* @ModelAttribute("age")로 설정하면 기본타입값을 jsp로 전달 가능 */
	@GetMapping("/ex04") // /sample/ex01
	public String ex04(SampleDTO dto, @ModelAttribute("age") int age) {//기본타입을 전달
		log.info(" " +dto);
		log.info("age:"+age);
		return "ex04";//이동할 jsp경로 "/WEB-INF/views/"+"ex04"+".jsp"
	}
	
	/* 파라미터가 배열이나 리스트 인 경우 */
	@RequestMapping("/ex02List")
	public String ex02List(@RequestParam("ids") ArrayList<String> ids, Model model) {
		log.info("ids:" +ids);
		model.addAttribute("ids",ids);
		return "ex02List";
	}
	
	@RequestMapping("/ex02Array")
	public String ex02Array(@RequestParam("ids") String[] ids,Model model) {
		log.info("ids:"+Arrays.toString(ids));
		model.addAttribute("ids",ids);
		return "ex02Array";
	}
	
	/* 파라미터를 List가 있는 객체로 받기 */
	@RequestMapping("/ex02Bean")
	public String ex02Bean(SampleDTOList list) {
		log.info("list dtos: " + list );
		return "ex02Bean";
	}
	
	/*  데이타 변환  */
	@GetMapping("/ex03")
	public String ex03(TodoDTO todo) {
		log.info("todo:"+todo);
		return "ex03";
	}
	
	/* Redirectattributes  */
	@GetMapping("/home")
	public String home(@RequestParam("name") String name, 
			                    @RequestParam("age") int age,
			                    RedirectAttributes rttr) {
		rttr.addFlashAttribute("name",name);
		rttr.addFlashAttribute("age",10);
		return "redirect:/";
	}
	
	
	/* JSON 데이타 리턴 */
	@RequestMapping("/ex06")
	public  @ResponseBody SampleDTO ex06() {
		log.info("/ex06.....................");
		
		SampleDTO dto = new SampleDTO();
		dto.setAge(10);
		dto.setName("홍길동");
		
		return dto;
	}
	
	/* ResponseEntity 타입 리턴 */
	@GetMapping("/ex07")
	public ResponseEntity<String> ex07(){
		log.info("/ex07..................");
		
		//{"name":"홍길동"}
		String msg = "{\"name\":\"홍길동\"}";
		
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "application/json;charset=UTF-8");
		
		return new ResponseEntity<String>(msg, header, 
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
