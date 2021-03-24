package com.springbook.biz.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCUtil {
   public static Connection getConnection() {
	    try {
	    	Class.forName("oracle.jdbc.driver.OracleDriver");
	    	return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","sa", "sa");
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }
	    return null;
   }
   
 //자원 해제
   public static void close(ResultSet rs, Statement stmt, Connection con) {
  	 try {
  		         if(rs!=null) rs.close();
  		         if(stmt!=null) stmt.close();
  		         if(con!=null) con.close();
  	 }catch(Exception e) {
  		  System.out.println(e.getMessage());
  	 }
    }
   
   public static void close(Statement stmt, Connection con) {
  	 try {
  		         if(stmt!=null) stmt.close();
  		         if(con!=null) con.close();
  	 }catch(Exception e) {
  		  System.out.println(e.getMessage());
  	 }
    }
   
   
   
}
