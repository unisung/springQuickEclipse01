package org.zerock.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/sample/*")
@Log4j
public class SampleController {
	
  @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')")		
  @GetMapping("/annoMember")
  public void doMember2() {
	 log.info("logined annotation member");  
  }
  
  @Secured({"ROLE_ADMIN"})
  @GetMapping("/annoAdmin")
  public void doAdmin2() {
	  log.info("admin annotation only");
  }
	
  @GetMapping("/all") //  /sample/all - 모든 유저 접속 가능 
  public void doAll() {
	  log.info("do all can access everybody");
  }
  
  @GetMapping("/member")  //  /sample/member- 회원만 접근 가능 리소스
  public void doMember() {
	  log.info("logined member");
  }
  
  @GetMapping("/admin") // /sample/admin - 관리자만 접근 가능 리소스
  public void doAdmin() {
	  log.info("admin only");
  }
     
  @GetMapping("/oper")  //  /sample/oper- 운영자만 접근 가능 리소스
  public void doOper() {
	  log.info("logined operation");
  }	
}
