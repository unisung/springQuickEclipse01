package com.springbook.biz.board;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class BoardServiceClient2 {
	public static void main(String[] args) {
	//1. Spring 컨테이너 구동
		AbstractApplicationContext container = new GenericXmlApplicationContext("applicationContext.xml");
		
    //2. Sring 컨테이너로 부터 BoardServiceImpl객체 얻기
		BoardService boardService = (BoardService)container.getBean("boardService");
		
	//3. 글 등록 기능 테스트
		//3-1. 글 등록
		 BoardVO vo = new BoardVO();
		 vo.setSeq(998);
		 vo.setTitle("임시 제목");
		 vo.setWriter("홍길동");
		 vo.setContent("임시 내용 ...................");
		 boardService.insertBoard(vo);
		 
		//3-1-2. 글 수정
		/* vo.setSeq(2);
		 vo.setTitle("임시 제목-수정");
		 vo.setWriter("홍길동");
		 vo.setContent("임시 내용-수정 ...................");
		 boardService.updateBoard(vo);
		 
		
		//3-2. 글 목록 검색
		 List<BoardVO> boardList = boardService.getBoardList(vo);
		  for(BoardVO board:boardList) { 
			   System.out.println("-----> " + board.toString()); 
			}
		  
		//3-2-2. 글 상세 조회
		  vo.setSeq(2);
		  System.out.println(vo.getSeq()+"번 내용:"+boardService.getBoard(vo));
		  
		  
		//3-2-2-1. 글 삭제 
		  vo.setSeq(10);
		  boardService.deleteBoard(vo);
		  
		 //3-2-2-2. 수정된 내용 보기
		  boardList = boardService.getBoardList(vo);
		  for(BoardVO board:boardList) { 
			   System.out.println("-----> " + board.toString()); 
			} 
		 */ 

 	//4. spring 컨테이너 종료	
        container.close();
	}

}
