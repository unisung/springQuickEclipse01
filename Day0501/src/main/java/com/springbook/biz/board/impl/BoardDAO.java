package com.springbook.biz.board.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.util.SqlSessionFactoryBean;

public class BoardDAO {
 private SqlSession mybatis;
 
 public BoardDAO() {
      mybatis = SqlSessionFactoryBean.getSqlSessionInstance();
 }
 
 /* insert 문*/
 public void insertBoard(BoardVO vo) {
	 mybatis.insert("BoardDAO.insertBoard",vo);//BoardDAO-네임스페이스, insertBoard-id
	 mybatis.commit();
 }

/* 리스트 조회 */
 public List<BoardVO> getBoardList(BoardVO vo){
	 return mybatis.selectList("BoardDAO.getBoardList", vo);
 }
 
 /* 상세 조회*/
 public BoardVO getBoard(BoardVO vo){
	mybatis.update("BoardDAO.updateCount",vo);
	mybatis.commit();
	return mybatis.selectOne("BoardDAO.getBoard", vo);// namespace.id
 }
 
 /* update */
 public void updateBoard(BoardVO vo) {
	 int result=mybatis.update("BoardDAO.updateBoard", vo);// namespace.id
	 System.out.println("DAO-update: "+result+"건 수정 완료");
	 mybatis.commit();/* opensession()로 설정시 commit()처리해야 DB에 반영됨. */
 }
 
 /* delete */
 public void deleteBoard(BoardVO vo) {
	mybatis.delete("BoardDAO.deleteBoard",vo);// namespace.id
	mybatis.commit();
 }
 
 /* 상위 10건 조회*/
 public List<BoardVO> getBoardListTop10(BoardVO vo){
   return mybatis.selectList("BoardDAO.getBoardListTop10", vo);
 }
 
 /* 검색 건 수 */
 public int getTotalCount(BoardVO vo) {
	 return mybatis.selectOne("BoardDAO.getTotalCount", vo);//namespace.id
 }
 
}
