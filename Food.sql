-- 메뉴를 구성하는 DB
create table FOOD
(
id   varchar2(50),  
name varchar2(50),
price int,
picture varchar2(200), 
inform  varchar2(50)
)

select * from FOOD
drop table FOOD;

