package com.springbook.biz.user.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.springbook.biz.user.UserVO;

@Repository
public class UserDAOSpring /* extends JdbcDaoSupport */{
	@Autowired
	JdbcTemplate spring;
	
	// JDBC 관련 변수
			private Connection conn = null;
			private PreparedStatement stmt = null;
			private ResultSet rs = null;
		
			//SQL문 
			private final String USER_INSERT = "insert into users(id, password,name,role) values(?,?,?,?)";
			private final String USER_GET = "select * from users where id=?";
			private final String USER_CNT = "select count(*) from users where id=?";
			
		 //JdbcTemplate setter injection처리
			/*
			 * @Autowired public void setSuperDataSource(DataSource dataSource) {
			 * super.setDataSource(dataSource); }
			 */
			
			
			
	//회원 등록
	public void insertUser(UserVO user) {
		System.out.println("===>Spring JDBC로 insertUser() 기능 처리");
		spring.update(USER_INSERT,user.getId(),user.getPassword(),user.getName(),user.getRole());
			}

	
	//해당 id로 회원이 있는 지 여부 확인
	public int getUserCnt(UserVO vo) {
		System.out.println("===>Spring JDBC로 getUserCnt() 기능 처리");
		return  spring.queryForInt(USER_CNT,vo.getId());
	}
	
	//회원 조회
	public UserVO getUser(UserVO user) throws SQLException{
		System.out.println("===> Spring JDBC로 getUser() 기능 처리");
		Object[] args= {user.getId()};
		System.out.println("아이디:"+args[0]);
		try {
		     user =spring.queryForObject(USER_GET, args, new UserRowMapper());
		}catch(EmptyResultDataAccessException e) {}
		return user;	
	}

 


}
