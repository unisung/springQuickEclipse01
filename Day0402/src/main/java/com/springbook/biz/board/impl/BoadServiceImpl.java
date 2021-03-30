package com.springbook.biz.board.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springbook.biz.board.BoardService;
import com.springbook.biz.board.BoardVO;

@Component("boardService")
public class BoadServiceImpl implements BoardService {
	//속성
	@Autowired
	private BoardDAOSpring boardDAO;

	//입력
	@Override
	public void insertBoard(BoardVO vo) {
		// TODO Auto-generated method stub
	  //강제 예외 발생
		//if(vo.getSeq()==0) 
		//	throw new IllegalArgumentException("0번 글은 등록 할 수없습니다.");
       boardDAO.insertBoard(vo);
       //boardDAO.insertBoard(vo);
	}

	//수정
	@Override
	public void updateBoard(BoardVO vo) {
		// TODO Auto-generated method stub
		 boardDAO.updateBoard(vo); 
	}

	//삭제
	@Override
	public void deleteBoard(BoardVO vo) {
		// TODO Auto-generated method stub
		boardDAO.deleteBoard(vo);
	}
    
	//상세 조회
	@Override
	public BoardVO getBoard(BoardVO vo) {
		// TODO Auto-generated method stub
		boardDAO.updateBoardCnt(vo); //조회 수 증가
		return boardDAO.getBoard(vo);
	}

	//글 목록 조회 
	@Override
	public List<BoardVO> getBoardList(BoardVO vo) {
		// TODO Auto-generated method stub
		return boardDAO.getBoardList(vo);
	}
	

}
