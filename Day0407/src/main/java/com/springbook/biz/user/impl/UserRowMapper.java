package com.springbook.biz.user.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.springbook.biz.user.UserVO;

public class UserRowMapper implements RowMapper<UserVO>{
	// resultSet과 해당하는 커서 번호를 받아서 객체로 만들어서 리턴하는 메소드
	//예) 조회 결과 셋의 2번째 객체 얻기 mapRow(rs,2)
	@Override
	public UserVO mapRow(ResultSet rs, int rowNum) throws SQLException{
		UserVO user = new UserVO();
		user.setId(rs.getString("id"));
		user.setPassword(rs.getString("password"));
		user.setName(rs.getString("name"));
		user.setRole(rs.getString("role"));
		
		System.out.println("rowMapper정보: " +user);
		return user;
	}


}
