package com.springbook.biz.board.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.common.JDBCUtil;

//@Component("boardDAO")
public class BoardDAO {
	// JDBC 관련 변수
		private Connection conn = null;
		private PreparedStatement stmt = null;
		private ResultSet rs = null;
	
		//SQL문
		private final String BOARD_INSERT = "insert into board(seq, title, writer, content) values((select nvl(max(seq), 0)+1 from board),?,?,?)";
		private final String BOARD_UPDATE = "update board set title=?, content=?, writer=? where seq=?";
		private final String BOARD_DELETE = "delete board where seq=?";
		private final String BOARD_GET = "select * from board where seq=?";
		private final String BOARD_LIST = "select * from board order by seq desc";
		private final String BOARD_UPDATE_CNT = "update board set cnt=cnt+1 where seq=?";
		private String BOARD_SEARCH = "select * from board where ";

     //CRUD 기능의 메소드 구현
	//글 등록
	public void insertBoard(BoardVO vo) {
		System.out.println("===> JDBC로 insertBoard() 기능 처리");
		try {
			    conn = JDBCUtil.getConnection();
			    stmt = conn.prepareStatement(BOARD_INSERT);
			    stmt.setString(1, vo.getTitle());
			    stmt.setString(2, vo.getWriter());
			    stmt.setString(3, vo.getContent());
			    
			    stmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(stmt, conn);
		}
	}
		
	//글 수정
		public void updateBoard(BoardVO vo) {
			System.out.println("===> JDBC로 updateBoard() 기능 처리");
			try {
				    conn = JDBCUtil.getConnection();
				    stmt = conn.prepareStatement(BOARD_UPDATE);
				    stmt.setString(1, vo.getTitle());
				    stmt.setString(2, vo.getContent());
				    stmt.setString(3, vo.getWriter());
				    stmt.setInt(4, vo.getSeq());				    
				
				    stmt.executeUpdate();
				
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				JDBCUtil.close( stmt, conn);
			}
    }
		
		//글 조회수 증가
				public void updateBoardCnt(BoardVO vo) {
					System.out.println("===> JDBC로 updateBoardCnt() 기능 처리");
					try {
						    conn = JDBCUtil.getConnection();
						    stmt = conn.prepareStatement(BOARD_UPDATE_CNT);
						    stmt.setInt(1, vo.getSeq());
						    
						    stmt.executeUpdate();
						
					}catch(Exception e) {
						e.printStackTrace();
					}finally {
						JDBCUtil.close( stmt, conn);
					}
		    }	
		
		//글 삭제
				public void deleteBoard(BoardVO vo) {
					System.out.println("===> JDBC로 deleteBoard() 기능 처리");
					try {
						    conn = JDBCUtil.getConnection();
						    stmt = conn.prepareStatement(BOARD_DELETE);
						    stmt.setInt(1, vo.getSeq());
						    
						    stmt.executeUpdate();
						
					}catch(Exception e) {
						e.printStackTrace();
					}finally {
						JDBCUtil.close( stmt, conn);
					}
		    }
				
				//글 상세 조회
				public BoardVO getBoard(BoardVO vo) {
					BoardVO board=null;
					System.out.println("===> JDBC로 getBoard() 기능 처리");
					try {
						    conn = JDBCUtil.getConnection();
						    stmt = conn.prepareStatement(BOARD_GET);
						    stmt.setInt(1, vo.getSeq());
						    
						    rs=stmt.executeQuery();
						    
						    if(rs.next()) {
						    	board = new BoardVO();
						    	board.setSeq(rs.getInt("seq"));
						    	board.setTitle(rs.getString("title"));
						    	board.setWriter(rs.getString("writer"));
						    	board.setContent(rs.getString("content"));
						    	board.setRegDate(rs.getDate("regdate"));
						    	board.setCnt(rs.getInt("cnt"));
						    }
					}catch(Exception e) {
						e.printStackTrace();
					}finally {
						JDBCUtil.close( stmt, conn);
					}
					return board;
		    }			
		
				
				//글 목록  조회
				public List<BoardVO> getBoardList(BoardVO vo) {
					List<BoardVO> boardList = new ArrayList<BoardVO>();
					System.out.println("===> JDBC로 getBoardList() 기능 처리");
					try {
						    conn = JDBCUtil.getConnection();
						    stmt = conn.prepareStatement(BOARD_LIST);
						    
						    rs=stmt.executeQuery();
						    
						    while(rs.next()) {
						    	BoardVO board = new BoardVO();
						    	board.setSeq(rs.getInt("seq"));
						    	board.setTitle(rs.getString("title"));
						    	board.setWriter(rs.getString("writer"));
						    	board.setContent(rs.getString("content"));
						    	board.setRegDate(rs.getDate("regdate"));
						    	board.setCnt(rs.getInt("cnt"));
						    	
						    	boardList.add(board);
						    }
					}catch(Exception e) {
						e.printStackTrace();
					}finally {
						JDBCUtil.close( stmt, conn);
					}
					return boardList;
		    }	
				
				
	//글 목록  조회-검색			
	 public List<BoardVO> getBoardList(String condition, String keyword){
		 List<BoardVO> boardList = new ArrayList<BoardVO>();
			System.out.println("===> JDBC로 getBoardList(String,String) 기능 처리");
			try {
				    conn = JDBCUtil.getConnection();
				    if(condition.equals("TITLE"))
				    	BOARD_SEARCH = BOARD_SEARCH+" title like '%'||?||'%'  order by seq desc";
				    else if(condition.equals("CONTENT"))
				    	BOARD_SEARCH = BOARD_SEARCH+" content like '%'||?||'%'  order by seq desc";	
				    
				    System.out.println("Query: " + BOARD_SEARCH);
				    
				    stmt = conn.prepareStatement(BOARD_SEARCH);
                    stmt.setString(1, keyword);
                      
				    rs=stmt.executeQuery();
				    
				    while(rs.next()) {
				    	BoardVO board = new BoardVO();
				    	board.setSeq(rs.getInt("seq"));
				    	board.setTitle(rs.getString("title"));
				    	board.setWriter(rs.getString("writer"));
				    	board.setContent(rs.getString("content"));
				    	board.setRegDate(rs.getDate("regdate"));
				    	board.setCnt(rs.getInt("cnt"));
				    	
				    	boardList.add(board);
				    }
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				JDBCUtil.close( stmt, conn);
			}
			return boardList;		 
	}
				
}