package com.springbook.biz.util;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionFactoryBean {
  private static SqlSessionFactory sqlSessionInstance = null;
  
  static {
	   try {
		     
		    if(sqlSessionInstance==null) {
		    	Reader reader = Resources.getResourceAsReader("sql-map-config.xml");
		    	sqlSessionInstance= new SqlSessionFactoryBuilder().build(reader);
		    	}
		   }catch(Exception e) {
			   e.printStackTrace();
		   }
  }

  /* openSession()는 default가 manual commit,
   * AutoCommit으로 바꿀려면
   * openSession(true)로 변경처리 
   * */
public static SqlSession getSqlSessionInstance() {
	return sqlSessionInstance.openSession();//openSession(true);
}
  
  
	
}
