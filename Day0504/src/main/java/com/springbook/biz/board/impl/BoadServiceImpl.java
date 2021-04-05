package com.springbook.biz.board.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springbook.biz.board.BoardService;
import com.springbook.biz.board.BoardVO;

@Component("boardService")
public class BoadServiceImpl implements BoardService {
	//속성
	//@Autowired
	//private BoardDAOMybatis boardDAO;
	@Autowired
	private BoardDAOJPA boardDAO;

	//입력
	@Override
	public void insertBoard(BoardVO vo) {
       boardDAO.insertBoard(vo);
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
		return boardDAO.getBoard(vo);
	}
	
	

	//글 목록 조회 
	@Override
	public List<BoardVO> getBoardList(BoardVO vo) {
		// TODO Auto-generated method stub
	  if(vo.getSearchKeyword()==null) vo.setSearchKeyword("");
	   return boardDAO.getBoardList(vo);
	}

/*	@Override
	public List<BoardVO> getBoardSearchList(BoardVO vo) {
		if(vo.getSearchCondition().equals("TITLE")) 
			return boardDAO.getBoardSearchListT(vo);
		else if(vo.getSearchCondition().equals("CONTENT")) 
			return boardDAO.getBoardSearchListC(vo);
		else
			return null;
	}
*/
	public List<BoardVO> getBoardSearchList(BoardVO vo) {
			return boardDAO.getBoardSearchList(vo);

	}

	@Override
	public void updateBoardCount(BoardVO vo) {
		  boardDAO.updateBoardCount(vo); //조회 수 증가
	}

}
