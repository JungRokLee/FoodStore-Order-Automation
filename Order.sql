-- 주문을 위한 DB
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
