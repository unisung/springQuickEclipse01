package org.zerock.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.log4j.Log4j;

/* servlet-context.xml에 component-scan 설정 후 사용 */
@ControllerAdvice
@Log4j
public class CommonExcepionAdvice {

	  @ExceptionHandler(Exception.class)
	  public String except(Exception ex, Model model) {
		  
		  log.error("Exception ........." + ex.getMessage());
		  model.addAttribute("exception", ex);
		  log.error(model);
		  return "error_page"; //    /WEB-INF/views/error_page.jsp
	  }
}
