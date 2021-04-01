package com.springbook.biz.user.impl;

import org.apache.ibatis.session.SqlSession;

import com.springbook.biz.user.UserVO;
import com.springbook.biz.util.SqlSessionFactoryBean;

public class UserDAO {
  private SqlSession mybatis;
  
  public UserDAO() {
	  mybatis = SqlSessionFactoryBean.getSqlSessionInstance();
  }
  
  public void insertUser(UserVO vo){
	  mybatis.insert("UserDAO.insertUser", vo);
	  mybatis.commit();
  }
  
  
  public UserVO getUser(UserVO vo) {
	  return mybatis.selectOne("UserDAO.getUser", vo);
  }
  
  public void updateUser(UserVO vo) {
	  mybatis.update("UserDAO.updateUser", vo);
	  mybatis.commit();
  }

  public int getTotalCount() {
	  return mybatis.selectOne("UserDAO.getTotalCount");
  }
}
