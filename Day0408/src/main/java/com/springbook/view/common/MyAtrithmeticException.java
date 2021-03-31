package com.springbook.view.common;

public class MyAtrithmeticException extends ArithmeticException {
   public MyAtrithmeticException() {
	   this("수치연산 오류");
   }

public MyAtrithmeticException(String s) {
	super(s);
}
}
