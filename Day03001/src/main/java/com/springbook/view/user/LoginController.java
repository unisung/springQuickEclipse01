package com.springbook.view.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.springbook.biz.user.UserVO;
import com.springbook.biz.user.impl.UserDAO;
import com.springbook.view.controller.Controller;

public class LoginController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("로그인 처리");
		
		// 1. 사용자 입력 정보 추출
		String id = request.getParameter("id");
		String password = request.getParameter("password");

		// 2. DB 연동 처리
		UserVO vo = new UserVO();
		vo.setId(id);
		vo.setPassword(password);

		UserDAO userDAO = new UserDAO();
		UserVO user = userDAO.getUser(vo);

		
		// 3. 화면 네비게이션
		if (user != null) {// id와 패스워드가 맞으면 
			//세션에 로그인 정보 저장
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			
			return "getBoardList.do";
		} else {
			return "login";//id와 패스워드가 맞지 않으면 다시 로그인으로 이동하도록 경로 리턴.
		}
	}
}
