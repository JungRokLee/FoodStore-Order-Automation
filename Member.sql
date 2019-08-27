   -- 일반 사용자를 위한 DB 
   CREATE TABLE MEMBER(
   id varchar(20)PRIMARY KEY,
   pw varchar(20),
   name varchar(20),
   address varchar(200) NOT NULL,
   status int  -- 불량 사용자 
   );
   



  