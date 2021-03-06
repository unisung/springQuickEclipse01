package org.zerock.domain;

import java.util.Date;
import lombok.Data;

@Data
public class BoardVO {
  private Long bno;
  private String title;
  private String content;
  private String writer;
  private Date regdate;
  private Date updateDate;
  
  private int replyCnt;//댓글 갯수 칼럼 추가
}
