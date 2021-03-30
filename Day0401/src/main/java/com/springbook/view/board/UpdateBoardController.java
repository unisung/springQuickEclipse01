package com.springbook.view.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
/*import org.springframework.web.servlet.mvc.Controller;*/

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.board.impl.BoardDAO;
/*import com.springbook.view.controller.Controller;*/

@Controller
public class UpdateBoardController /* implements Controller */ {
   @RequestMapping(value="/updateBoard.do", method=RequestMethod.POST)
   public String updateBoard(BoardVO vo, BoardDAO boardDAO) {
	   boardDAO.updateBoard(vo);
	   return "redirect:getBoardList.do";
   }
	
	
	/*
	 * @Override public ModelAndView handleRequest(HttpServletRequest request,
	 * HttpServletResponse response) { System.out.println("글 수정 처리");
	 * 
	 * // 1. 사용자 입력 정보 추출 // request.setCharacterEncoding("EUC-KR"); String title =
	 * request.getParameter("title"); String content =
	 * request.getParameter("content"); String seq = request.getParameter("seq");
	 * 
	 * // 2. DB 연동 처리 BoardVO vo = new BoardVO(); vo.setTitle(title);
	 * vo.setContent(content); vo.setSeq(Integer.parseInt(seq));
	 * 
	 * BoardDAO boardDAO = new BoardDAO(); boardDAO.updateBoard(vo);
	 * 
	 * // 3. 화면 네비게이션 //xxx.do로 이동시는 viewResolver를 이용하지않도록 redirect:를 붙여서 경로 지정
	 * return new ModelAndView("redirect:getBoardList.do"); }
	 */
}
