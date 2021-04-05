package com.springbook.biz.user.impl;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbook.biz.user.UserService;
import com.springbook.biz.user.UserVO;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	//UserDAOMybatis userDAO;
	UserDAOJPA userDAO;
    
	@Override
	public void insertUser(UserVO vo) throws SQLException  {
		if(getUserCnt(vo)==0)
	        userDAO.insertUser(vo);
	}

	@Override
	public UserVO getUser(UserVO vo) throws SQLException {
	  return userDAO.getUser(vo);
	}

	@Override
	public void deleteUser(UserVO vo) {
		userDAO.deleteUser(vo);
	}

	@Override
	public void updateUser(UserVO vo) {
		userDAO.updateUser(vo);
	}

	@Override
	public int getUserCnt(UserVO vo) throws SQLException {
		return userDAO.getUserCnt(vo);
	}

}
