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
			
			//한 건 조회
			Board b=new Board();
			b.setSeq(102);
			//한 건 출력 find(클래스타입, primary key값);
			b = em.find(Board.class, b.getSeq());
			
			System.out.println("b--->"+b);
			
			//수정
			b.setTitle("JPA 수정 제목");
			b.setContent("JPA 글 수정 잘 됩니다.....");
			//merge(수정된 vo); 
		    em.merge(b)	;
		    
		    
			  //Transaction 처리
		//		tx.commit(); 
				
			
		    //삭제 - update(merge)와 delete(remove)시 
		    //java VO를 다르게 선언하여 처리
		    //준 영속 상태에서, 영속상태의 정보를 가지고 있음.
			Board vo = new Board();
			vo.setSeq(103); 
			vo=em.find(Board.class, vo.getSeq());
		    
		    //remove(삭제할 vo);
		    em.remove(vo);
		    
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
