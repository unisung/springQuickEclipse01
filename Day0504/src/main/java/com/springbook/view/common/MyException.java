package com.springbook.view.common;

public class MyException extends Exception {
   public MyException() {
	   this("예외 발생 아이디가 없습니다.");
   }

public MyException(String s) {
	super(s);
}
}
