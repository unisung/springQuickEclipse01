package com.springbook.biz.board;

import com.springbook.biz.board.impl.BoardDAO;

public class BoardServiceClient {
	public static void main(String[] args) {
	 BoardDAO boardDAO = new BoardDAO();
	 BoardVO vo = new BoardVO();
	 
	 vo.setTitle("myBatis 제목");
	 vo.setWriter("홍길동");
	 vo.setContent("myBatis 내용입니다....");
	 
	 boardDAO.insertBoard(vo);
	 System.out.println("완료");
	 
}
}
