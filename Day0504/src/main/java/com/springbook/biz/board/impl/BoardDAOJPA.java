package com.springbook.biz.board.impl;

import java.awt.print.Book;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.springbook.biz.board.BoardVO;

import oracle.net.aso.e;

@Repository
public class BoardDAOJPA {
	@PersistenceContext
	private EntityManager em;

	public void insertBoard(BoardVO vo) {
		System.out.println("===> JPA로 insertBoard() 기능 처리");
		em.persist(vo);
	}

	public void updateBoard(BoardVO vo) {
		System.out.println("===> JPA로 updateBoard() 기능 처리");
		em.merge(vo);
	}

	public void deleteBoard(BoardVO vo) {
		System.out.println("===> JPA로 deleteBoard() 기능 처리");
	em.remove(em.find(BoardVO.class, vo.getSeq()));
		
	}

	public void updateBoardCount(BoardVO vo) {
		System.out.println("===> JPA로 updateBoardCount() 기능 처리");
		em.merge(vo);
	}

	public BoardVO getBoard(BoardVO vo) {
		System.out.println("===> JPA로 getBoard() 기능 처리");
		return em.find(BoardVO.class, vo.getSeq());
	}

	public List<BoardVO> getBoardList(BoardVO vo) {
		System.out.println("===> JPA로 getBoardList() 기능 처리");
		return em.createQuery("from BoardVO b order by b.seq desc").getResultList();
	}

	public List<BoardVO> getBoardSearchList(BoardVO vo) {
		/* TypedQuery 생성 후 , setParameter로 바인딩 변수 설정 후, getResultList()로 추출 */
		TypedQuery<BoardVO> query=null;
		if(vo.getSearchCondition().equals("TITLE")) {
		query
		   =em.createQuery("from BoardVO b where title like '%'||:searchKeyword||'%' order by b.seq desc",
				                    BoardVO.class);
		}else if(vo.getSearchCondition().equals("CONTENT")) {
			query
			   =em.createQuery("from BoardVO b where content like '%'||:searchKeyword||'%' order by b.seq desc",
					                    BoardVO.class);	
		}
		query.setParameter("searchKeyword", vo.getSearchKeyword());
		return query.getResultList();
	}


	

}
