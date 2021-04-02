package com.springbook.biz.user.impl;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springbook.biz.user.UserVO;

@Repository
public class UserDAOMybatis /* extends SqlSessionDaoSupport */{
	/*
	 * @Autowired public void setSqlSessionFactory(SqlSessionFactory
	 * sqlSessionFactory) { super.setSqlSessionFactory(sqlSessionFactory); }
	 */
     @Autowired
     SqlSessionTemplate mybatis;
	
	public void insertUser(UserVO vo) {
		// TODO Auto-generated method stub
		
	}

	public UserVO getUser(UserVO vo) {
		System.out.println("파라미터 vo: "+vo);
		UserVO user=mybatis.selectOne("UserDAO.getUser", vo);//namespace.id
		System.out.println("조회결과 vo: "+vo);
		return user;
	}

	public int getUserCnt(UserVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
