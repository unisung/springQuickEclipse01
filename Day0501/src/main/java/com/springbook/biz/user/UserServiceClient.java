package com.springbook.biz.user;

import com.springbook.biz.user.impl.UserDAO;

public class UserServiceClient {

public static void main(String[] args) {
		
	//회원 입력""
	UserVO vo = new UserVO();
	vo.setId("wang2");
	vo.setPassword("1234");
	vo.setName("왕길동2");
	vo.setRole("user");
	
	UserDAO userDAO=new UserDAO();
	///userDAO.insertUser(vo);
	
	vo.setId("hong");
	vo.setPassword("1234");
	UserVO user = userDAO.getUser(vo);
	
	System.out.println("회원정보 :"+user==null?"없음":user);
	
	
	//회원정보 수정
	vo.setId("hong");
	vo.setPassword("5678");
	userDAO.updateUser(vo);
	
    user = userDAO.getUser(vo);
	System.out.println("수정 후 - 회원정보 :"+user==null?"없음":user);
	
	//회원수 조회
	int count = userDAO.getTotalCount();
	System.out.println("전체 회원 수: "+count);
	
	}
}