package org.zerock.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyPageDTO;
import org.zerock.domain.ReplyVO;
import org.zerock.service.ReplyService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/replies/") /*/replies/** <- rest에서는 /replies/로 만 처리 */
@Log4j
@AllArgsConstructor
public class ReplyController {
	
	private ReplyService service;
	
	//등록처리 json데이타를 파라미터로 받아서 java ReplyVO객체로 변환하여 DB저장
	@PreAuthorize("isAuthenticated()")
	@PostMapping(value="/new", // replies/new
			             consumes = "application/json", 
			             produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> create(@RequestBody ReplyVO vo){
		
		log.info("ReplyVO: " + vo);
		
		int insertCount = service.register(vo);
		
		log.info("Reply INSERT COUNT: "+insertCount);
		
		return insertCount==1
				 ? new ResponseEntity<>("success", HttpStatus.OK)
				 : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//댓글 목록 보기
	@GetMapping(value="/pages/{bno}/{page}",
					produces= {MediaType.APPLICATION_XML_VALUE,
							         MediaType.APPLICATION_JSON_UTF8_VALUE} )
	/*public ResponseEntity<List<ReplyVO>> getList(*/
	public ResponseEntity<ReplyPageDTO> getList(
			 @PathVariable("page") int page,
			 @PathVariable("bno") Long bno ){
		 log.info("getList ................");
		 Criteria cri = new Criteria(page, 10);
		 
		 log.info(cri);
		 
		// List<ReplyVO> list = service.getList(cri, bno);
		// for(ReplyVO vo:list)
		//	 System.out.println(vo);
		 
		//return new ResponseEntity<List<ReplyVO>>(service.getList(cri, bno), HttpStatus.OK); 
		 //댓글갯수와 리스트를 담은 객체리턴 
		 return new ResponseEntity<ReplyPageDTO>(service.getListPage(cri, bno), HttpStatus.OK); 
	}
	
	//댓글 한 건 조회  /  /replies/3
	@GetMapping(value="/{rno}",produces= {MediaType.APPLICATION_XML_VALUE,
							                                   MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<ReplyVO> get(@PathVariable("rno") Long rno){
		 log.info("get ........: " + rno);
		 
		 return new ResponseEntity<ReplyVO>(service.get(rno),HttpStatus.OK);
	}
	
	//댓글 한 건 삭제  /replies/3
	@PreAuthorize("principal.username==#vo.replyer")
	@DeleteMapping(value="/{rno}",produces= {MediaType.TEXT_PLAIN_VALUE})
public ResponseEntity<String> remove(@RequestBody ReplyVO vo, @PathVariable("rno") Long rno){
		log.info("remove ........: " + rno);

return service.remove(rno)==1
        ? new ResponseEntity<String>("success",HttpStatus.OK)
		: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//댓글 수정 - JSON으로 전달되는 파라미터를 JAVA로 변환하여 처리
	@PreAuthorize("principal.username==#vo.replyer")
	@RequestMapping(value="/{rno}",  //  /replies/3
			                  method= {RequestMethod.PUT, RequestMethod.PATCH},
					          consumes = "application/json", 
					          produces = {MediaType.TEXT_PLAIN_VALUE})
public ResponseEntity<String> modify(@RequestBody ReplyVO vo, 
		                                               @PathVariable("rno") Long rno){
		vo.setRno(rno);

	log.info("rno: " + rno);
	log.info("modify: " + vo);
	
	return service.modify(vo)==1
		 ? new ResponseEntity<>("success", HttpStatus.OK)
		 : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

}
