package com.springbook.biz.board;

import java.sql.SQLException;
import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.springbook.biz.user.UserService;
import com.springbook.biz.user.UserVO;

public class BoardServiceClient3 {
	public static void main(String[] args) throws SQLException {
	//1. Spring 컨테이너 구동
		AbstractApplicationContext container = new GenericXmlApplicationContext("applicationContext.xml");
		
    //2. Sring 컨테이너로 부터 BoardServiceImpl객체 얻기
		UserService userService = (UserService)container.getBean("userService");
		
	//3. 회원 등록 기능 테스트
		//3-1. 회원 등록
		 UserVO vo = new UserVO();
		vo.setId("hong15");
		vo.setPassword("1234");
		vo.setName("홍15길동");
		vo.setRole("admin");
		
		userService.insertUser(vo);
		
		
		//3-2. 회원 조회
		vo.setId("hong");
		UserVO userVo  = userService.getUser(vo);
		System.out.println("회원조회 : "+userVo);
		 
		 
	
 	//4. spring 컨테이너 종료	
        container.close();
	}

}
