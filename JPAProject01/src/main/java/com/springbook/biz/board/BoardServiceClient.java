package com.springbook.biz.board;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class BoardServiceClient {
	public static void main(String[] args) {
		//EntityManager 생성
		EntityManagerFactory emf= Persistence.createEntityManagerFactory("JPAProject01");
		EntityManager em = emf.createEntityManager();
		//TransactionManager 생성
		EntityTransaction tx = em.getTransaction();
		
		try {
			  //Transaction 시작:
			tx.begin();
			
			Board board = new Board();
			board.setTitle("JPA 제목");
			board.setWriter("홍길동");
			board.setContent("JPA 글 잘 등록 되네요......");
			
			//글 등록
			em.persist(board);
			
			//글 목록 조회
			String sql = "select b from Board b order by b.seq desc";
			List<Board> boardList = em.createQuery(sql, Board.class).getResultList();
			
			for(Board b:boardList) { System.out.println("-----> " +b);}
			
			//Transaction 처리
			tx.commit();
			
		}catch(Exception e) {
			e.printStackTrace();
			//rollback처리
			tx.rollback();
		}finally {
			em.close();//자원 해제
		}
		emf.close();//자원 해제
	}
}
