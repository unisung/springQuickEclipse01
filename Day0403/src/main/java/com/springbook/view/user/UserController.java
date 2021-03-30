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

	@RequestMapping(value="/login.do",method = RequestMethod.POST)
	public ModelAndView login(UserVO vo, HttpSession session) {
		System.out.println("로그인 처리....");
		UserVO user;
		try {
			user = userService.getUser(vo);
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
