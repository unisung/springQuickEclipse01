package org.zerock.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardAttachVO;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageDTO;
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
	  public void list(Criteria cri, Model model) {
	  
	   log.info("list: " + cri); 
	   model.addAttribute("list", service.getList(cri)); 
	  
	   int total = service.getTotal(cri);
	   
	   log.info("total:"+total);
	   
	   model.addAttribute("pageMaker",new PageDTO(cri, total));
	  }
	 
	
	/*
	 * @GetMapping("/list")//spring 4.3이후 getMapping public String list(Model model)
	 * {
	 * 
	 * log.info("list"); model.addAttribute("list", service.getList()); return
	 * "list"; }
	 */

	  
    @GetMapping("/register") // /board/register
    @PreAuthorize("isAuthenticated()")
		public void register() {
		}
	  
	  
	/* 게시글 등록 */
	@PostMapping("/register") // /board/register
	@PreAuthorize("isAuthenticated()")
	public String register(BoardVO board, RedirectAttributes rttr) {
		log.info("=====================");
		log.info("register: "+board);
		
		/* 첨부 파이일 있으면 */
		if(board.getAttachList() !=null) {
			board.getAttachList().forEach(attach -> log.info(attach));
		}
		log.info("=====================");
		
		//등록처리
		service.register(board);
		//BoardServiceImple에서 insertSelectKey()메소드로 글등록 후 글 번호를 받을수 있음.
		
		rttr.addFlashAttribute("result", board.getBno()); //게시글 번호를 result값으로 전달
		
		return "redirect:/board/list";//게시글 등록 후 redirect로 게시글 목록으로 이동
	}
	
	/* 게시글 상세 조회 / 게시글 수정 폼 */
	@GetMapping({"/get","/modify"})
	public void get(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri,Model model) {
		
		log.info("/get");
		
		model.addAttribute("board",service.get(bno));
	}
	
	//첨부파일 리스트 출력
	@GetMapping(value="/getAttachList",
			            produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<BoardAttachVO>> getAttachList(Long bno){
		log.info("getAttachList " + bno);
		return new ResponseEntity<>(service.getAttachList(bno), HttpStatus.OK);
	}
	
	/* 게시글 수정 처리 */
	@PreAuthorize("principal.username == #board.writer")
	@PostMapping("/modify")
	/*public String modify(BoardVO board, RedirectAttributes rttr, @ModelAttribute("cri") Criteria cri) { */
	public String modify(BoardVO board, RedirectAttributes rttr, 
			 @ModelAttribute("cri") Criteria cri) {
		log.info("modify:" + board);
		
		if(service.modify(board)) {
			rttr.addFlashAttribute("result","success");
		}
		
		
		rttr.addAttribute("pageNum",cri.getPageNum());
		rttr.addAttribute("amount",cri.getAmount());
		rttr.addAttribute("type",cri.getType());
		rttr.addAttribute("keyword",cri.getKeyword());
		
		return "redirect:/board/list"+cri.getListLink();//UriComponentBuilder를 이용한 파라미터 전송
	}
	
	/* 게시글 삭제 */
	//#writer 파라미터 writer값 비교
	@PreAuthorize("principal.username==#writer")
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, 
			                       RedirectAttributes rttr,  Criteria cri, String writer) {
		
		log.info("remove...." + bno);
		
		//첨부파일리스트 얻기
		List<BoardAttachVO> attachList = service.getAttachList(bno);
		
		//게시글 삭제 
		if(service.remove(bno)) {
			//첨부파일 삭제
			deleteFiles(attachList);
			
			rttr.addFlashAttribute("result","success");
		}
		
		rttr.addAttribute("pageNum",cri.getPageNum());
		rttr.addAttribute("amount",cri.getAmount());
		
		return "redirect:/board/list"+cri.getListLink();//UriComponentBuilder를 이용한 파라미터 전송
	}


	private void deleteFiles(List<BoardAttachVO> attachList) {
		//첨부파일이 없으면 return;
		if(attachList==null || attachList.size()==0) {
			return;
		}
		log.info("delete attach files .............................");
		log.info(attachList);
		
		//람다식
		attachList.forEach(attach->{
			try {
			//파일경로 및 파일정보 얻기
			Path file = 
		    Paths.get("c:\\upload\\"+attach.getUploadPath()+"\\"+attach.getUuid()+"_"+attach.getFileName());
			//파일삭제
			Files.deleteIfExists(file);
			//이미지 파일이면 썸네일도 삭제
			if(Files.probeContentType(file).startsWith("image")) {
			//썸네일 정보 얻기
				Path thumbNail = 
			 Paths.get("c:\\upload\\"+attach.getUploadPath()+"\\s_"+attach.getUuid()+"_"+attach.getFileName());
           //썸네일 삭제
			 Files.deleteIfExists(thumbNail);
			}
			}catch(Exception e) {
				log.error("delete file error" + e.getMessage());
			}
		});
		
		
	}
	
	
}
