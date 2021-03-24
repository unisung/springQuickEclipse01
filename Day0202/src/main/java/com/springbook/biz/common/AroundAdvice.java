package com.springbook.biz.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

//ProceedingJoinPoint는 JoinPoint를 상속 받음.
@Service
@Aspect //Aspect = Pointcut + Advice
public class AroundAdvice {
	@Pointcut("execution(* com.springbook.biz..*Impl.*(..))")
	public void around() {}
	
	
	@Around("around()")
	public Object aroundLog(ProceedingJoinPoint pjp) throws Throwable {
		String method = pjp.getSignature().getName();
		
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		//before
		System.out.println("[AROUND-BEFORE]: 비즈니스 메소드 수행 전 처리할 내용...");
		//메소드 실행후, 실행한 메소드가 속한 객체 정보를 리턴하여 얻기
		Object returnObj = pjp.proceed();
		stopWatch.stop();
		//after
		System.out.println("[AROUND-AFTER]: "+method+"() 메소드 수행시간 :"
		                           + stopWatch.getTotalTimeMillis()+"(ms)초");
		
		//실행한 메소드가 속한 객체 리턴
		return returnObj;
		
	}
}
