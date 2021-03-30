package com.springbook.view.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.board.impl.BoardDAO;
/*import com.springbook.view.controller.Controller;*/

@Controller
public class GetBoardListController /* implements Controller */ {
	/*/getBoardList.do 실행전에 conditionMap먼저 생성 후 Model에 저장 */
	@ModelAttribute("conditionMap")
	public Map<String, String> searchConditionMap(){
		Map<String, String> conditionMap = new HashMap<String, String>();
		conditionMap.put("제목","TITLE");
		conditionMap.put("내용", "CONTENT");
		return conditionMap;//객체를 리턴
	}
	
	@RequestMapping("/getBoardList.do")
	public String getBoardList(BoardVO vo, Model model) {
		BoardDAO boardDAO = new BoardDAO();
		List<BoardVO> boardList = boardDAO.getBoardList(vo);
	
		model.addAttribute("boardList", boardList);
		return "getBoardList.jsp";//View이름 리턴
	}
	
	 @RequestMapping(value="/getBoardList.do", method=RequestMethod.POST )
	 public String getBoardList(BoardVO vo, Model model, BoardDAO boardDAO,
			 @RequestParam(value="searchCondition", defaultValue = "TITLE",required = false) String condition,
			 @RequestParam(value="searchKeyword", defaultValue="", required=false) String keyword) {
		 
		 System.out.println("검색 조건: "+condition);
		 System.out.println("검색 단어: " +keyword);
		 List<BoardVO> boardList = boardDAO.getBoardList(condition, keyword);
		// List<BoardVO> boardList = boardDAO.getBoardList(vo);
		 model.addAttribute("boardList", boardList);
		 return "getBoardList.jsp";//View이름 리턴
	 }
	
	 
	
	
	/*
	 * @Override public ModelAndView handleRequest(HttpServletRequest request,
	 * HttpServletResponse response) { System.out.println("글 목록 검색 처리"); // 1. 사용자
	 * 입력 정보 추출(검색 기능은 나중에 구현) // 2. DB 연동 처리 BoardVO vo = new BoardVO(); BoardDAO
	 * boardDAO = new BoardDAO(); List<BoardVO> boardList =
	 * boardDAO.getBoardList(vo);
	 * 
	 * // 3. 검색 결과를 ModelAndView 에 저장하고 목록 화면을 리턴한다. //HttpSession session =
	 * request.getSession(); //session.setAttribute("boardList", boardList);
	 * //return "getBoardList";// WEB-INF/getBoardList.jsp <-"WEB-INF/"+
	 * getBoardList +".jsp" ModelAndView mav=new ModelAndView("getBoardList");
	 * mav.addObject("boardList", boardList); return mav;
	 * 
	 * // /WEB-INF/board/getBoardList.do.jsp" }
	 */
}