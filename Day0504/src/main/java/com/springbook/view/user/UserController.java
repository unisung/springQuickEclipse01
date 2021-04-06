package com.springbook.view.user;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.springbook.biz.user.UserService;
import com.springbook.biz.user.UserVO;

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
		e.printStackTrace();
	 }
		return null;
	}
	
	@RequestMapping(value="/login.do",method = RequestMethod.GET)
	public String loginView(@ModelAttribute("user") UserVO vo) {//매개변수에서 객체 선언과 동시에 전달객체로 저장.
		System.out.println("로그인 화면으로 이동....");
		vo.setId("test");
		vo.setPassword("test123");
		return "login.jsp";
	}
	
	 @RequestMapping("/logout.do")
	    public String logout(HttpSession session) {
		   session.invalidate();
		   return "login.jsp";
	   }
	 
	 @RequestMapping("insertMember.do")
	 public String insertMemberForm() {
		 return "memberForm.jsp";
	 }
	 
	 @RequestMapping(value="/insertUser.do",method=RequestMethod.POST)
	   public String insertMember(UserVO vo) throws SQLException{
		  System.out.println("회원 가입 처리");
		  userService.insertUser(vo);
		  return "redirect:login.do";
	 }
	 
	 @RequestMapping(value="/idchek.do", method=RequestMethod.GET)
	 @ResponseBody
	 public int idCheck(UserVO vo) throws SQLException {
		 System.out.println("id 확인");
		 return userService.getUserCnt(vo);
	 }
}


