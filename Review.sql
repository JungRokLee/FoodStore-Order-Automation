   -- 리뷰를 위한 DB 
   CREATE TABLE REVIEW(
   id varchar(20),
   name varchar(20),
   title varchar(50),
   inform varchar(200) NOT NULL,
   time date default sysdate,
   num int
   );
   