package com.springbook.view.board;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.springbook.biz.board.BoardListVO;
import com.springbook.biz.board.BoardService;
import com.springbook.biz.board.BoardVO;
import com.springbook.biz.board.impl.BoardDAO;

@Controller
@SessionAttributes("board")
public class BoardController {
	@Autowired
	BoardService boardService;
	
	@RequestMapping("/dataTransformXml.do")
	@ResponseBody /* 객체를 http 응답 body로 변환 */
	public BoardListVO dataTransofrmXml(BoardVO vo) {
		vo.setSearchCondition("TITLE");
		vo.setSearchKeyword("");
		List<BoardVO> boardList = boardService.getBoardList(vo);
		BoardListVO boardListVO = new BoardListVO();
		boardListVO.setBoardList(boardList);
		return boardListVO;
	}
	
    @RequestMapping("/dataTransform.do")
    @ResponseBody/* 객체를 http 응답 body로 변환 */
    public List<BoardVO> dataTransform(BoardVO vo){
    	vo.setSearchCondition("TITLE");
    	vo.setSearchKeyword("");
    	List<BoardVO> boardList = boardService.getBoardList(vo);
    	return boardList;	
    }
	
    @RequestMapping("/dataTransformSearch.do")
    @ResponseBody/* 객체를 http 응답 body로 변환 */
    public List<BoardVO> dataTransformSearch(BoardVO vo){
    	vo.setSearchCondition("TITLE");
    	vo.setSearchKeyword("");
    	List<BoardVO> boardList = boardService.getBoardSearchList(vo);
    	for(BoardVO board:boardList) {
    		System.out.println(board);
    	}
    	return boardList;	
    }
    
    @RequestMapping("/dataTransformGetBoard.do")
    @ResponseBody/* 객체를 http 응답 body로 변환 */
    public BoardVO dataTransformGetBoard(BoardVO vo){
    	return boardService.getBoard(vo);	
    }
    
	/*/getBoardList.do 실행전에 conditionMap먼저 생성 후 Model에 저장 */
  @ModelAttribute("conditionMap")
	public Map<String, String> searchConditionMap(){
		Map<String, String> conditionMap = new HashMap<String, String>();
		conditionMap.put("제목","TITLE");
		conditionMap.put("내용", "CONTENT");
		return conditionMap;//객체를 리턴
	}
	
	
	 @RequestMapping("/deleteBoard.do")
	    public String deleteBoard(BoardVO vo) {
	    	System.out.println("삭제 글번호: "+ vo.getSeq());
	    	System.out.println("vo: "+vo);
	    	
	    	boardService.deleteBoard(vo);
	    	return "redirect:getBoardList.do";
	    }
	 
	 @RequestMapping("/getBoard.do")
     public String getBoard(BoardVO vo, Model model) {
		 System.out.println("/getBoard.do-vo:"+vo);
    	 model.addAttribute("board", boardService.getBoard(vo));
    	 return "getBoard.jsp";
     }
	 
	 @RequestMapping(value="/getBoardList.do", method=RequestMethod.GET)
		public String getBoardList(BoardVO vo, Model model) {
			List<BoardVO> boardList = boardService.getBoardList(vo);
		
			model.addAttribute("boardList", boardList);
			return "getBoardList.jsp";//View이름 리턴
		}
		
		 @RequestMapping(value="/getBoardList.do", method=RequestMethod.POST )
		 public String getBoardSearchList(BoardVO vo, Model model) {
			 
			if(vo.getSearchCondition()==null) vo.setSearchCondition("TITLE");
			if(vo.getSearchKeyword()==null) vo.setSearchKeyword("");
     
			model.addAttribute("boardList",boardService.getBoardSearchList(vo)); 
			 return "getBoardList.jsp";//View이름 리턴
		 }
		 
		 @RequestMapping(value="/insertBoard.do", method = RequestMethod.POST)
	        public String insertBoard(BoardVO vo) throws IllegalStateException, IOException {
			 //파일 업로드 처리 
			 MultipartFile uploadFile = vo.getUploadFile();
			 //클라이언트로 부터 전송된 파일이 있으면
			 if(!uploadFile.isEmpty()) {
				 String fileName = uploadFile.getOriginalFilename();
				 uploadFile.transferTo(new File("c:/upload/"+fileName));
			 }
			 boardService.insertBoard(vo);
	    	  return "redirect:getBoardList.do";
	      }
		 
		    @RequestMapping("/insertBoardForm.do")
		    public String insertBoardForm(){
		    	return "insertBoard.jsp";
		    }
		    
		    @RequestMapping(value="/updateBoard.do", method=RequestMethod.POST)
		    public String updateBoard(@ModelAttribute("board") BoardVO vo) 
		    		                                        throws IllegalStateException, IOException {
		    	 //파일 업로드 처리 
				 MultipartFile uploadFile = vo.getUploadFile();
				 //클라이언트로 부터 전송된 파일이 있으면
				 if(!uploadFile.isEmpty()) {
					 String fileName = uploadFile.getOriginalFilename();
					 uploadFile.transferTo(new File("c:/upload/"+fileName));
				 }
		    	
		    	System.out.println("board: "+vo);
		 
		 	   boardService.updateBoard(vo);
		 	   return "redirect:getBoardList.do";
		    }

	 

}
