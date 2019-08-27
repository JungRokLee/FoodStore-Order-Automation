--음식주문 테이블
create table ORDERFOOD
(
id varchar2(50),
table_num int,
food_name varchar2(50),
count int,
price int,
total int default 0,
time date default sysdate
)

select * from ORDERFOOD
drop table ORDERFOOD;


