package com.springbook.view.common;

public class MyNullPointerException extends NullPointerException {
   public MyNullPointerException() {
	   this("아이디가 없습니다.");
   }

public MyNullPointerException(String s) {
	super(s);
}
}
