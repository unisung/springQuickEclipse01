package com.springbook.biz.board;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Board
 *
 */
@Entity /* @Entity 어노테이션을 사용하기 위해서는 속성 중에 @Id를 가진 속성이 하나있어야함.*/
@Table(name="myboard") /* 테이블명을 지정하여 생성하도록 처리 */
public class Board implements Serializable {
 private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue//자동 생성 처리
  private int seq;
  private String title;
  private String writer;
  private String content;
  @Temporal(TemporalType.DATE) /* 테이블의 date타입 매핑시키기 */
  private Date regDate=new Date();
  private int cnt;
  
  private String fileName;
  
  
public String getFileName() {
	return fileName;
}
public void setFileName(String fileName) {
	this.fileName = fileName;
}
public int getSeq() {
	return seq;
}
public void setSeq(int seq) {
	this.seq = seq;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getWriter() {
	return writer;
}
public void setWriter(String writer) {
	this.writer = writer;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public Date getRegDate() {
	return regDate;
}
public void setRegDate(Date regDate) {
	this.regDate = regDate;
}
public int getCnt() {
	return cnt;
}
public void setCnt(int cnt) {
	this.cnt = cnt;
}

@Override
public String toString() {
	return "Board [seq=" + seq + ", title=" + title + ", writer=" + writer + ", content=" + content + ", regDate="
			+ regDate + ", cnt=" + cnt + "]";
}
  
  
	
	

   
}
