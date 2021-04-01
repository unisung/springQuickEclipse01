package com.springbook.biz.board;

import java.util.List;

import com.springbook.biz.board.impl.BoardDAO;

public class BoardServiceClient3 {
	public static void main(String[] args) {
	 BoardDAO boardDAO = new BoardDAO();
	 BoardVO vo = new BoardVO();
	 
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
	 System.out.println("========================");	 
	
	 /*삭제 후 결과 보기 */
	 boardList = boardDAO.getBoardList(vo);
	 for(BoardVO board:boardList) {
		 System.out.println("--->" + board);
	 }
	 
	 System.out.println("=== 결과를 상위 10건만 출력 ============");	 
	
	 /*삭제 후 결과 보기 */
	  boardList = boardDAO.getBoardListTop10(vo);
	 for(BoardVO board:boardList) {
		 System.out.println("--->" + board);
	 }
	  
	 System.out.println("게시글 조회수 출력");
	 int count = boardDAO.getBoard(vo).getCnt();
	 System.out.println(vo.getSeq()+"번 글의 조회수:"+count);
	 
	 /* 게시글 전체 건수 */
	 int totalCount = boardDAO.getTotalCount(vo);
	 System.out.println("검색결과 전체 건수 :"+totalCount);
}
}
