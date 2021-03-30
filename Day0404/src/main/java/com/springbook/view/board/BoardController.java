package com.springbook.view.board;

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
import org.springframework.web.bind.annotation.SessionAttributes;

import com.springbook.biz.board.BoardService;
import com.springbook.biz.board.BoardVO;
import com.springbook.biz.board.impl.BoardDAO;

@Controller
@SessionAttributes("board")
public class BoardController {
	@Autowired
	BoardService boardService;
	
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
	        public String insertBoard(BoardVO vo) {
			 boardService.insertBoard(vo);
	    	  return "redirect:getBoardList.do";
	      }
		 
		    @RequestMapping("/insertBoardForm.do")
		    public String insertBoardForm(){
		    	return "insertBoard.jsp";
		    }
		    
		    @RequestMapping(value="/updateBoard.do", method=RequestMethod.POST)
		    public String updateBoard(@ModelAttribute("board") BoardVO vo) {
		 	   System.out.println("board: "+vo);
		 
		 	   boardService.updateBoard(vo);
		 	   return "redirect:getBoardList.do";
		    }

	 

}
