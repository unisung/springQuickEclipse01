package com.springbook.biz.user.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import com.springbook.biz.user.UserVO;

@Repository
public class UserDAOJPA {
	@PersistenceContext
	private EntityManager em;

	public void insertUser(UserVO vo) {
	  em.persist(vo);	
	}

	public UserVO getUser(UserVO vo) {
		TypedQuery<UserVO> query
		   =em.createQuery("from UserVO b where id=:id and password=:password ",UserVO.class);
		  query.setParameter("id", vo.getId());
		  query.setParameter("password", vo.getPassword());
		  return query.getSingleResult();
	}

	public int getUserCnt(UserVO vo) {
		TypedQuery<UserVO> query
		   =em.createQuery("from UserVO b where id=:id",UserVO.class);
		  query.setParameter("id", vo.getId());
		  return query.getSingleResult()!=null? 1:0;
	}

	public void deleteUser(UserVO vo) {
		em.remove(em.find(UserVO.class, vo));
	}

	public void updateUser(UserVO vo) {
	   em.merge(vo);
	}
	
	

}
