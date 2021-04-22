
create table tbl_reply (
  rno number(10,0), 
  bno number(10,0) not null,
  reply varchar2(1000) not null,
  replyer varchar2(50) not null, 
  replyDate date default sysdate, 
  updateDate date default sysdate
);

create sequence seq_reply;

alter table tbl_reply add constraint pk_reply primary key (rno);

alter table tbl_reply  add constraint fk_reply_board  
foreign key (bno)  references  tbl_board (bno);

drop table tbl_sample1;
drop table tbl_sample2;

create table tbl_sample1(col1 varchar2(500));
create table tbl_sample2(col1 varchar2(50));

/* 댓글 갯수 칼럼 추가 */
alter table tbl_board add (replycnt number default 0);

update tbl_board set replycnt = (select count(*) from tbl_reply where tbl_reply.bno = tbl_board.bno);

commit;