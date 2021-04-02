package com.springbook.biz.board.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springbook.biz.board.BoardVO;

@Repository
public class BoardDAOMybatis/* extends SqlSessionDaoSupport */{
	/* @Autowired public void setSqlSessionFactory(SqlSessionFactory
	 * sqlSessionFactory) { super.setSqlSessionFactory(sqlSessionFactory); }
	 */
	 @Autowired
	 private SqlSessionTemplate mybatis;
	 
	public void insertBoard(BoardVO vo) {
		System.out.println("===> Mybatis로 insertBoard() 기능 처리");
		mybatis.insert("BoardDAO.insertBoard",vo);
	}

	public void updateBoard(BoardVO vo) {
		System.out.println("===> Mybatis로 updateBoard() 기능 처리");
		System.out.println("파라 vo: " +vo);
		mybatis.update("BoardDAO.updateBoard", vo);
	}

	public void deleteBoard(BoardVO vo) {
		System.out.println("===> Mybatis로 deleteBoard() 기능 처리");
		mybatis.delete("BoardDAO.deleteBoard", vo);
	}

	public BoardVO getBoard(BoardVO vo) {
		System.out.println("===> Mybatis로 getBoard() 기능 처리");
		return mybatis.selectOne("BoardDAO.getBoard", vo);
	}

	public List<BoardVO> getBoardList(BoardVO vo) {
		System.out.println("===> Mybatis로 getBoardList() 기능 처리");
		return mybatis.selectList("BoardDAO.getBoardList", vo);
	}

	/*
	 * public List<BoardVO> getBoardSearchList(BoardVO vo) { // TODO Auto-generated
	 * method stub return getSqlSession().selectList("BoardDAO.getBoardList", vo); }
	 */

     /* 검색 조건 TiTLE */
	public List<BoardVO> getBoardSearchListT(BoardVO vo) {
		System.out.println("===> Mybatis로 getBoardSearchListT() 기능 처리");
		System.out.println("파라-vo: "+vo);
		return mybatis.selectList("BoardDAO.getBoardSearchListT", vo);
	}

	/* 검색 조건 CONTENT */
	public List<BoardVO> getBoardSearchListC(BoardVO vo) {
		System.out.println("===> Mybatis로 getBoardSearchListC() 기능 처리");
		return mybatis.selectList("BoardDAO.getBoardSearchListC", vo);
	}

	/* 조회수 증가 */
	public void updateBoardCount(BoardVO vo) {
		System.out.println("===> Mybatis로 updateBoardCount() 기능 처리");
		mybatis.update("BoardDAO.updateBoardCount", vo);
	}

  
}
