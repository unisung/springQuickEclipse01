package com.springbook.view.user;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.springbook.biz.user.UserVO;
import com.springbook.biz.user.impl.UserDAO;
/*import com.springbook.view.controller.Controller;*/

@Controller
public class LoginController /* implements Controller */ {
	//@RequestMapping(value="/login.do",method = RequestMethod.POST)
	//public String login(UserVO vo, UserDAO dao, HttpSession session) {
	//	UserVO user = dao.getUser(vo);
	//	if(user!=null) {
	//		session.setAttribute("user", user);
	//		return "redirect:getBoardList.do";// viewResolver 미 사용처리 redirect:
	//	}else
	//		return "login.jsp";
	
	
	/* 메소드에서 매개변수로 선언된 Bean 객체는 client로 부터 파라미터를 받아서
	 * 현재 페이지에서 객체로 생성되어 사용되고, jsp페이지까지 전달됨.
	 * !!!!! UserVO로 선언된 객체는 객체명의 첫 글자가 소문자로 바뀌어서 jsp로 전달됨.
	 *  UserVO -> userVO, BoardVO ->boardVO로 변환되어서 전달됨.
	 *  jsp에서는 ${userVO.속성명}, ${boardVO.속성명}으로 접근 해야함.
	 *  
	 * */
	@RequestMapping(value="/login.do",method = RequestMethod.POST)
	public ModelAndView login(UserVO vo, UserDAO dao, HttpSession session) {
		System.out.println("로그인 처리....");
		UserVO user = dao.getUser(vo);
	if(user!=null) {
		//session.setAttribute("user", user);
			return new ModelAndView("redirect:getBoardList.do");// viewResolver 미 사용처리 redirect:
	}else
			return new ModelAndView("login.jsp");
	}
	/* UserVO, BoardVO 객체명이 아닌 다른 이름으로 jsp로 전달하고 싶을 때!!!!,
	 *  @ModelAttribute("user"), @ModelAttribute("board")로 이름 재 지정해서 
	 *  전달하게됨.
	 *  jsp에서는 ${userVO.속성명}   -> ${user.속성명},
	 *               ${boardVO.속성명} -> ${board.속성명} 으로 사용
	 * */
	//Controller에서 jsp로 보낼때 @ModelAttribute로 설정해서 전달 가능
	//@ModelAttribute만 선언하고 값 설정, ModelAndView나 Model 선언 안 함.
	@RequestMapping(value="/login.do",method = RequestMethod.GET)
	public String loginView(@ModelAttribute("user") UserVO vo) {//매개변수에서 객체 선언과 동시에 전달객체로 저장.
		System.out.println("로그인 화면으로 이동....");
		//UserVO vo=new UserVO();
		vo.setId("test");
		vo.setPassword("test123");
		
		//model.addAttribute("user", vo);
		return "login.jsp";
	}
	
	/*
	 * @Override public ModelAndView handleRequest(HttpServletRequest request,
	 * HttpServletResponse response) { System.out.println("로그인 처리");
	 * 
	 * // 1. 사용자 입력 정보 추출 String id = request.getParameter("id"); String password =
	 * request.getParameter("password");
	 * 
	 * // 2. DB 연동 처리 UserVO vo = new UserVO(); vo.setId(id);
	 * vo.setPassword(password);
	 * 
	 * UserDAO userDAO = new UserDAO(); UserVO user = userDAO.getUser(vo);
	 * 
	 * 
	 * // 3. 화면 네비게이션 ModelAndView mav=new ModelAndView(); if (user != null) {// id와
	 * 패스워드가 맞으면 //세션에 로그인 정보 저장 HttpSession session = request.getSession();
	 * session.setAttribute("user", user); //return "getBoardList.do";
	 * //mav.addObject("user",user);
	 * 
	 * //xxx.do로 이동시는 viewResolver를 이용하지않도록 redirect:를 붙여서 경로 지정
	 * mav.setViewName("redirect:getBoardList.do"); } else { //return "login";//id와
	 * 패스워드가 맞지 않으면 다시 로그인으로 이동하도록 경로 리턴. //mav.setViewName("login");
	 * 
	 * //viewResolver를 사용하지도 않고, redirect로 바로 login.jsp로 이동
	 * mav.setViewName("redirect:login.jsp");//response.sendRedirect(); } return
	 * mav; }
	 */
}
