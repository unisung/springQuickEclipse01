package com.springbook.biz.board;

import java.util.List;

import com.springbook.biz.board.impl.BoardDAO;

public class BoardServiceClient2 {
	public static void main(String[] args) {
	 BoardDAO boardDAO = new BoardDAO();
	 BoardVO vo = new BoardVO();
	 
	 /* 입력*/
	 vo.setTitle("myBatis 제목");
	 vo.setWriter("홍길동");
	 vo.setContent("myBatis 내용입니다....");
	 
	// boardDAO.insertBoard(vo);
	 System.out.println("완료");
	 
	 /* 리스트 조회 */
	 vo.setSearchCondition("TITLE");
	 vo.setSearchKeyword("");
	 
	 List<BoardVO> boardList = boardDAO.getBoardList(vo);
	 for(BoardVO board:boardList) {
		 System.out.println("--->" + board);
	 }
	 
	 System.out.println("========================");
	 
	 /* 상세 조회 */
	 vo.setSeq(1009);
	 
	 BoardVO b=boardDAO.getBoard(vo);
	 System.out.println("상세조회 : " +b);
	 
	 System.out.println("========================");
	 
	 /* 수정 */
	 vo.setSeq(1009);
	 vo.setTitle("myBatis 제목- 또또수정 ");
	 vo.setContent("myBatis -또또수정 -내용입니다....,");
	 
	 boardDAO.updateBoard(vo);
	 
	 System.out.println("========================"); 
	 
	 /* 수정 결과 조회 */
	 b=boardDAO.getBoard(vo);
	 System.out.println("수정내용-상세조회 : " +b);	 
	 System.out.println("========================");
	 /* 삭제 처리 */
	 
	 vo.setSeq(8);
	 boardDAO.deleteBoard(vo);
	 System.out.println("========================");	 
	
	 /*삭제 후 결과 보기 */
	 boardList = boardDAO.getBoardList(vo);
	 for(BoardVO board:boardList) {
		 System.out.println("--->" + board);
	 }
	  
}
}
