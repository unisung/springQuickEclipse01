package com.springbook.biz.common;

import org.aspectj.lang.ProceedingJoinPoint;

//ProceedingJoinPoint는 JoinPoint를 상속 받음.
public class AroundAdvice {
	public Object aroundLog(ProceedingJoinPoint pjp) throws Throwable {
		//before
		System.out.println("[AROUND-BEFORE]: 비즈니스 메소드 수행 전 처리할 내용...");
		//메소드 실행후, 실행한 메소드가 속한 객체 정보를 리턴하여 얻기
		Object returnObj = pjp.proceed();
		
		//after
		System.out.println("[AROUND-AFTER]: 비즈니스 메소드 수행 후에 처리할 내용...");
		
		//실행한 메소드가 속한 객체 리턴
		return returnObj;
		
	}
}
