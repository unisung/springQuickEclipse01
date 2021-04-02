package com.springbook.biz.user.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springbook.biz.user.UserVO;


@Repository
public class UserDAOMybatis extends SqlSessionDaoSupport{
 @Autowired
 public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
	super.setSqlSessionFactory(sqlSessionFactory);
	}

public void insertUser(UserVO vo) {
	// TODO Auto-generated method stub
	
}

public UserVO getUser(UserVO vo) {
	// TODO Auto-generated method stub
	System.out.println("파라:"+vo);
	
	  vo= getSqlSession().selectOne("UserDAO.getUser", vo);
	  System.out.println("결과:"+vo);
	 
	return vo;
}

public int getUserCnt(UserVO vo) {
	// TODO Auto-generated method stub
	return 0;
}

 	

 
  
}
