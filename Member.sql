   --회원
   CREATE TABLE MEMBER(
   id varchar(20)PRIMARY KEY,
   pw varchar(20),
   name varchar(20),
   address varchar(200) NOT NULL,
   status int  --불량회원 체크
   );
   



  