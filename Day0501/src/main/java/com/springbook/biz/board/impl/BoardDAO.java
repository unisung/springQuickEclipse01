package com.springbook.biz.board.impl;

import org.apache.ibatis.session.SqlSession;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.util.SqlSessionFactoryBean;

public class BoardDAO {
 private SqlSession mybatis;
 
 public BoardDAO() {
      mybatis = SqlSessionFactoryBean.getSqlSessionInstance();
 }
 
 public void insertBoard(BoardVO vo) {
	 mybatis.insert("BoardDAO.insertBoard",vo);//BoardDAO-네임스페이스, insertBoard-id
	 mybatis.commit();
 }
 
 
}
