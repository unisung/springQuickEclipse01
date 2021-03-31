package com.springbook.view.common;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice("com.springbook.view")
public class CommonExceptionHandler {
	@ExceptionHandler(ArithmeticException.class)
   public ModelAndView handlerArithmeticException(Exception e) {
	    ModelAndView mav = new ModelAndView("/common/arithmeticError.jsp");
	    mav.addObject("exception",e);
	    return mav;
   }
   
	@ExceptionHandler(NullPointerException.class)
   public ModelAndView handlerNullPointException(Exception e) {
		 ModelAndView mav = new ModelAndView("/common/nullPointerError.jsp");
		 System.out.println("에러: "+e.getMessage());
		    mav.addObject("exception", new MyNullPointerException() );
		    return mav;
   }
   
	@ExceptionHandler(Exception.class)
   public ModelAndView handlerException(Exception e) {
		 ModelAndView mav = new ModelAndView("/common/error.jsp");
		    mav.addObject("exception",e);
		    return mav;
   }
   
}
