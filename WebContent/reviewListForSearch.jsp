<%@ page import="java.sql.Timestamp" %>
<%@ page import="Foodstore_Package.FoodStoreDto"%>
<%@ page import="Foodstore_Package.FoodStoreDao"%>
<%@ page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>

<!DOCTYPE html>
<html lang="kr">
<head> 
<meta charset="UTF-8">
<title>리뷰목록</title>

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
	<form action="mainOfuser.jsp" method="post" name="reg_frm">


		<div class="wrapper">
			<h3>
				<font color='white'>고객들이 작성한 리뷰입니다. </font>
			</h3>
			<div class="table">

				<div class="row header blue">
					<div class="cell">작성자</div>
					<div class="cell">제목</div>
					<div class="cell">내용</div>
					<div class="cell">시간</div>
				
				</div>


        
				  
    <% 
	request.setCharacterEncoding("EUC-KR");
    String id = request.getParameter("id");
    String search_for = request.getParameter("search_for");
	String search_context = request.getParameter("search_context");
	FoodStoreDao dao = FoodStoreDao.getInstance();
	ArrayList<FoodStoreDto> dtos = dao.SearchMemberForUser(search_for, search_context);

	
	for (int i = 0; i < dtos.size(); i++) { // dtos 를 size 만큼 for 문 돌림
		FoodStoreDto dto = dtos.get(i); 
		String id1 = dto.getId();
		String name = dto.getName(); // dto의 name 추출하여 저장
		String address = dto.getAddress(); // dto의 address 추출하여 저장
		String inform = dto.getInform();
%> 

				
				<div class="row">  
					 <div class="cell" style="display:none">
       <%=id1%> 
      </div>
      <div class="cell">
       <%=name%> 
      </div>
      <div class="cell">
      <%=address%>
      </div>
      <div class="cell">
      <%=inform%>
      </div>
     <div class="cell"><a href="doReview.jsp?id=<%=id%>">리뷰작성</a> </div>
					

	
					
				 <%	}  %>
				

			</div>
 <li><input type="submit" value="돌아가기" /></li>
</body>
</html>
