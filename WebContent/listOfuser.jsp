<%@ page import="Member_Package.MemberDto"%>
<%@ page import="Member_Package.MemberDao"%>
<%@ page import="Foodstore_Package.FoodStoreDao"%>
<%@ page import="Foodstore_Package.FoodStoreDto"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.sql.Timestamp" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>

<!DOCTYPE html>
<html lang="kr">
	<head>
	<meta charset="UTF-8">
	<title>유저목록</title>

<style rel="stylesheet">
body {
  font-family: "Helvetica Neue", Helvetica, Arial;
  font-size: 14px;
  line-height: 20px;
  font-weight: 400;
  color: #3b3b3b;
  -webkit-font-smoothing: antialiased;
  font-smoothing: antialiased;
  background: #2b2b2b;
}

.wrapper {
  margin: 0 auto;
  padding: 40px;
  max-width: 800px;
}

.table {
  margin: 0 0 40px 0;
  width: 100%;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.2);
  display: table;
}
@media screen and (max-width: 580px) {
  .table {
    display: block;
  }
}

.row {
  display: table-row;
  background: #f6f6f6;
}
.row:nth-of-type(odd) {
  background: #e9e9e9;
}
.row.header {
  font-weight: 900;
  color: #ffffff;
  background: #ea6153;
}
.row.green {
  background: #27ae60;
}
.row.blue {
  background: #2980b9;
}
@media screen and (max-width: 580px) {
  .row {
    padding: 8px 0;
    display: block;
  }
}

.cell {
  padding: 6px 12px;
  display: table-cell;
}
@media screen and (max-width: 580px) {
  .cell {
    padding: 2px 12px;
    display: block;
  }
}


</style>
</head>
<body>
<form action="searchOkuser.jsp" method="post" name="listOfstore">
 

<div class="wrapper">

  <div class="table">
    <h2> <font color ='white' >사용자 목록 </font> </h2>
    <div class="row header">
      <div class="cell">
           아이디
      </div>
      <div class="cell">
           비밀번호
      </div>
      <div class="cell">
            이름
      </div>
      <div class="cell">
            주소
      </div>
       <div class="cell">
            삭제
      </div>
        <div class="cell">
            불량회원등록
      </div>
    </div>
    
    <% 
		MemberDao m_dao = MemberDao.getInstance();
		// MemberDAO() 클래스를 이용하여 m_dao 객체 생성

		ArrayList<MemberDto> dtos = m_dao.getList();
		// memberDAO 에 있는 getList() 를 실행하고 나서 
		// ArrayList<MemberDto>	의 dtos 객체를 생성 후 
		// memberDao.getList() 를 dtos 로 반환 받아 출력
		for (int i = 0; i < dtos.size(); i++) { // dtos 를 size 만큼 for 문 돌림
			//dtos의 get(0)--> 첫번째사람을 dto에 저장한다..
			//그리고 그 첫번째사람의 이름,패스워드,아이디,이메일,주소를 저장하고
			//밑에 내려가서 테이블에서 그 정보를 찍는다
			//그리고 다시 올라와서 다음사람..이렇게
			MemberDto dto = dtos.get(i); // dtos 의 i 번재 정보를 가져와 dto 에 저장
			String name = dto.getName(); // dto의 name 추출하여 저장
			String pw = dto.getPw();
			String id = dto.getId(); // dto의 id 추출하여 저장
			String address = dto.getAddress(); // dto의 address 추출하여 저장
			
			/////////////////////////////////////////////////////////
		

	%>
		
	 <div class="row">
      <div class="cell">
     <%=id%>
      </div>
      <div class="cell">
        <%=pw%>  
      </div>
      <div class="cell">
       <%=name%> 
      </div>
      <div class="cell">
      <%=address%>
      </div>
                  <div class="cell"><a href="delMidUser.jsp?id=<%=id%>">삭제</a> </div>
                  <div class="cell"><a href="enrollBad.jsp?id=<%=id%>">불량회원등록</a> </div>
    </div>
  <% 	
  }
  %>
    
  </div>
  <select name="search_for">
    <option value="이름">이름으로</option>
    <option value="아이디">아이디로</option>
</select>
	검색:<input type ="text" name="search_context" color ="white">
	<input type="submit" value="검색">
	</form> 
  
</body>
</html>
