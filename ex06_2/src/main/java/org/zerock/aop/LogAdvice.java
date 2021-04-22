package org.zerock.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Aspect
@Component
@Log4j
public class LogAdvice {
	/* @Aspect로 어노테이션 처리할려면 root-context.xml에 설정후 진행. */
	@Before("execution(* org.zerock.service.SampleService*.*(..))")
	public void logBefore() {
		log.info("=====================");
	}
	
	//메소드에 넘어오는 파라미터 추적
	@Before("execution(* org.zerock.service.SampleService*.doAdd(String, String)) && args(str1,str2))")
	public void logBeforeWithParam(String str1, String str2) {
		log.info("str1 : "+ str1);
		log.info("str2 : "+ str2);
	}
	
	//예외발생시 advice
	@AfterThrowing(pointcut = "execution(* org.zerock.service.SampleService*.*(..))", throwing="exception")
	public void logExcedption(Exception exception) {
		log.info("Exception.....!!!!");
		log.info("exception: " + exception);
	}
	
	//Around 
	@Around("execution(* org.zerock.service.SampleService*.*(..))")
	public Object logTime(ProceedingJoinPoint pjp) {
		
		long start = System.currentTimeMillis();
		
		log.info("Target: " + pjp.getTarget());
		log.info("Param: "+Arrays.toString(pjp.getArgs()));
		
		//invoke method
		Object result = null;
		
		try {
			 result = pjp.proceed();//실행
		}catch(Throwable e) {
			e.printStackTrace();
		}
		
		long end = System.currentTimeMillis();
		
		log.info("TIME: " + (end - start));
		
		return result;
	}
}
