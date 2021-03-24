package com.springbook.biz.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

//1.Bean등록
@Service
//2. Aspect 설정 Aspect = Pointcut + Advice
@Aspect
public class BeforeAdvice {
	@Pointcut("execution(* com.springbook.biz..*Impl.*(..))")
	public void allPointcut() {}
	
	@Before("allPointcut()")
	public void beforeLog(JoinPoint jp){
		//실행된 메소드 정보 얻기, 메소드 매개변수 정보 얻기
		String method = jp.getSignature().getName();
		Object[] args = jp.getArgs();
	   	
		System.out.println("[사전 처리] "+method+"() 메소드 args 정보 :" +args[0].toString());
	}
}
