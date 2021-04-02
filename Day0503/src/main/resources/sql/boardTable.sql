drop table board;    
     
create table board(
seq number primary key,
title varchar2(100),
writer varchar2(100),
content varchar2(2000),
regDate date default sysdate,
cnt number default 0,
filename varchar2(100)
);
  
select * from board;


select * from board
    where title like '%'||'테스트'||'%'
    order by seq desc