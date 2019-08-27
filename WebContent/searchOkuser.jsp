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
    </div>
    
    <% 
	request.setCharacterEncoding("EUC-KR");
    String search_for = request.getParameter("search_for");

	String search_context = request.getParameter("search_context");
	MemberDao dao = MemberDao.getInstance();
	ArrayList<MemberDto> dtos = dao.SearchMember(search_for, search_context);
	if (dtos.size() > 1) {
		out.print("검색된 글은 총"   + dtos.size() + "이며 내용은 아래와 같습니다");
	}
	
	for (int i = 0; i < dtos.size(); i++) { // dtos 를 size 만큼 for 문 돌림
		MemberDto dto = dtos.get(i); 
		if (search_for.equals("아이디")) {
			if (!search_context.equals(dto.getId())) {
%>
<script language="javascript">
	alert("없는 아이디에요."); // 1 이면 
	history.back(); /* 브라우저를 뒤로 돌림 */
</script>
<%
	}
		} else if  (search_for.equals("이름"))
		{
			if (!search_context.equals(dto.getName())) {
				%>
<script language="javascript">
	alert("없는 이름이에요."); // 1 이면 
	history.back(); /* 브라우저를 뒤로 돌림 */
</script>
<%}
		}
		String name = dto.getName(); // dto의 name 추출하여 저장
		String pw = dto.getPw();
		String id = dto.getId(); // dto의 id 추출하여 저장
		String address = dto.getAddress(); // dto의 address 추출하여 저장
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
    </div>
  <% 	
  }
  %>
    
 
  
</body>
</html>
