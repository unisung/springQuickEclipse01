package com.springbook.biz.common;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

@Service
@Aspect // Aspect = Pointcut + Advice
public class LogAdvice {	
	//적용시점 메소드 annotation설정
	@Before("PointcutCommon.allPointcut()")//적용 시점
	public void printLog() {//Advice
		System.out.println("[공통 로그] 비즈니스 로직 수행 전 동작");
	}
}
