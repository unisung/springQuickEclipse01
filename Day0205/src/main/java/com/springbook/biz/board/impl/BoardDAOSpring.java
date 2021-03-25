package com.springbook.biz.board.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.common.JDBCUtil;

@Repository
public class BoardDAOSpring /* extends JdbcDaoSupport */{
	@Autowired
	private JdbcTemplate spring;
	
	// JDBC 관련 변수
		private Connection conn = null;
		private PreparedStatement stmt = null;
		private ResultSet rs = null;
	
		//SQL문
		private final String BOARD_INSERT = "insert into board(seq, title, writer, content) values((select nvl(max(seq), 0)+1 from board),?,?,?)";
		private final String BOARD_UPDATE = "update board set title=?, content=? where seq=?";
		private final String BOARD_DELETE = "delete board where seq=?";
		private final String BOARD_GET = "select * from board where seq=?";
		private final String BOARD_LIST = "select * from board order by seq desc";
		private final String BOARD_UPDATE_CNT = "update board set cnt=cnt+1 where seq=?";

     //CRUD 기능의 메소드 구현
	//글 등록
	public void insertBoard(BoardVO vo) {
		System.out.println("===> Spring JDBC로 insertBoard() 기능 처리");
		spring.update(BOARD_INSERT,vo.getTitle(),vo.getWriter(),vo.getContent());		
	}
		
	
	  //글 수정 
	public void updateBoard(BoardVO vo) {
		try {
	  System.out.println("===> Spring JDBC로 updateBoard() 기능 처리");
	  spring.update(BOARD_UPDATE, vo.getTitle(),vo.getContent(),vo.getSeq());
		}catch(Exception e) {
			System.out.println("에러: "+e.getMessage());
		}
	  }
	 
	 
	 //글 조회수 증가 
	  public void updateBoardCnt(BoardVO vo) {
	  System.out.println("===> Spring JDBC로 updateBoardCnt() 기능 처리"); 
	  spring.update(BOARD_UPDATE_CNT, vo.getSeq());
	  }
	  
	//글 목록 조회 
	  public List<BoardVO> getBoardList(BoardVO vo) { 
	  System.out.println("===> Spring JDBC로 getBoardList() 기능 처리");
	   return spring.query(BOARD_LIST,new BoardRowMapper());
	  }
	  
	  //글 상세 조회 
	  public BoardVO getBoard(BoardVO vo) { 
		  System.out.println("===> Spring JDBC로 getBoard() 기능 처리");
		  Object[] args = {vo.getSeq()};
		  //queryForObject(쿼리문, 바인딩변수배열, RowMapper);
		  return spring.queryForObject(BOARD_GET, args,new BoardRowMapper());
	  }

	  //글 삭제 
	  public void deleteBoard(BoardVO vo) {
	  System.out.println("===> Spring JDBC로 deleteBoard() 기능 처리"); 
	  spring.update(BOARD_DELETE, vo.getSeq());
	  }

}