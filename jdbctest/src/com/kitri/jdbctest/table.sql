
-- 테이블 생성
create table jdbctest
(
	no number primary key,
	name varchar2(16),
	id varchar2(16),
	joindate date default sysdate

);

-- 테이블 삭제
drop table jdbctest;

--시퀀스 생성
create sequence jdbctest_no_seq
start with 1 increment by 1;

--시퀀스 삭제 
drop sequence jdbctest_no_seq;