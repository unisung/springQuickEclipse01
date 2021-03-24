package com.springbook.biz.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springbook.biz.user.UserService;
import com.springbook.biz.user.UserVO;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    UserDAO userDAO;
    
	@Override
	public void insertUser(UserVO vo)  {
		UserVO user=getUser(vo);
		//if(user.getId().equals(vo.getId()))
		//	  throw new IllegalArgumentException("동일한 아이디가 존재합니다.");
	    userDAO.insertUser(vo);
	}

	@Override
	public UserVO getUser(UserVO vo) {
	  return userDAO.getUser(vo);
	}

	@Override
	public void deleteUser(UserVO vo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateUser(UserVO vo) {
		// TODO Auto-generated method stub

	}

}
