package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/board/*")
@AllArgsConstructor
public class BoardController {
  //spring4.3이후 자동 autowired
	private BoardService service;
	
	/* 게시글 리스트 */
	
	  @GetMapping("/list")//spring 4.3이후 getMapping 
	  public void list(Model model) {
	  
	   log.info("list"); 
	   model.addAttribute("list", service.getList()); 
	  }
	 
	
	/*
	 * @GetMapping("/list")//spring 4.3이후 getMapping public String list(Model model)
	 * {
	 * 
	 * log.info("list"); model.addAttribute("list", service.getList()); return
	 * "list"; }
	 */
	
	  @GetMapping("/register") // /board/register
		public void register() {
		}
	  
	  
	/* 게시글 등록 */
	@PostMapping("/register") // /board/register
	public String register(BoardVO board, RedirectAttributes rttr) {
		
		log.info("register: "+board);
		
		service.register(board);
		
		rttr.addFlashAttribute("result", board.getBno()); //게시글 번호를 result값으로 전달
		
		return "redirect:/board/list";//게시글 등록 후 redirect로 게시글 목록으로 이동
	}
	
	/* 게시글 상세 조회 */
	@GetMapping("/get")
	public void get(@RequestParam("bno") Long bno, Model model) {
		
		log.info("/get");
		
		model.addAttribute("board",service.get(bno));
	}
	
	/* 게시글 수정 */
	@PostMapping("/modify")
	public String modify(BoardVO board, RedirectAttributes rttr) {
		log.info("modify:" + board);
		
		if(service.modify(board)) {
			rttr.addFlashAttribute("result","success");
		}
		return "redirect:/board/list";
	}
	
	/* 게시글 삭제 */
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, RedirectAttributes rttr) {
		log.info("remove...." + bno);
		if(service.remove(bno)) {
			rttr.addFlashAttribute("result","success");
		}
		return "redirect:/board/list";
	}
	
	
}
