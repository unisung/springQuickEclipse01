package com.springbook.view.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.board.impl.BoardDAO;
/*import com.springbook.view.controller.Controller;*/

public class GetBoardController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("글 상세 조회 처리");
		
		// 1. 검색할 게시글 번호 추출
		String seq = request.getParameter("seq");
		
		// 2. DB 연동 처리
		BoardVO vo = new BoardVO();
		vo.setSeq(Integer.parseInt(seq));
		
		BoardDAO boardDAO = new BoardDAO();
		BoardVO board = boardDAO.getBoard(vo);
		
		// 3. 검색 결과를 ModelAndView 에 저장하고 상세 화면을 리턴
		//HttpSession session = request.getSession();//
		//session.setAttribute("board", board);
		//return "getBoard";
		
		//이동할 경로가 ~.jsp 이므로 view명만 지정
		ModelAndView mav = new ModelAndView("getBoard");
		mav.addObject("board", board);//세션에 저장하던 정보를 -> ModelAndView에 저장
		return mav;
	}

}
