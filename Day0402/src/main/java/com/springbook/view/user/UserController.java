package com.springbook.view.user;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.springbook.biz.user.UserService;
import com.springbook.biz.user.UserVO;
import com.springbook.biz.user.impl.UserDAO;

@Controller
public class UserController {
	@Autowired
	 UserService userService;
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
		UserVO user;
		try {
			user = userService.getUser(vo);
		
		//UserVO user = dao.getUser(vo);
	if(user!=null) {
		session.setAttribute("user", user);
		return new ModelAndView("redirect:getBoardList.do");// viewResolver 미 사용처리 redirect:
	}else
		return new ModelAndView("login.jsp");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	 }
		return null;
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
	
	 @RequestMapping("/logout.do")
	    public String logout(HttpSession session) {
		   session.invalidate();
		   return "login.jsp";
	   }
}
