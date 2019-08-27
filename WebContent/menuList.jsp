
<%@ page import="Food_Package.FoodDao"%>
<%@ page import="Food_Package.FoodDto"%>
<%@ page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
	<%@page import="java.net.URLEncoder"%>




<%
	request.setCharacterEncoding("EUC-KR");
%>
<!DOCTYPE html>
<html lang="kr">
<head> 
<meta charset="UTF-8">
<title>리스트</title>

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
		<form action="mainOfstore.jsp" method="post">


		<div class="wrapper">
			<h3>
				<font color='white'>음식 목록 </font>
			</h3>
			<div class="table">

				<div class="row header blue">
					<div class="cell">이름</div>
					<div class="cell">가격</div>
					<div class="cell">사진</div>
					<div class="cell">정보</div>
					<div class="cell">수정</div>
						<div class="cell">삭제</div>
				</div>


                <jsp:useBean id="dto" class="Food_Package.FoodDto" scope="page" />
				<jsp:setProperty name="dto" property="*" />
				<%
				request.setCharacterEncoding("EUC-KR");
				 String id= request.getParameter("id");
				      dto.setId(id);
	
				FoodDao dao = FoodDao.getInstance();
				ArrayList<FoodDto> dtos = dao.SearchFood(id);
					 
				
					for (int i = 0; i < dtos.size(); i++) { // dtos 를 size 만큼 for 문 돌림
						FoodDto Fdto = dtos.get(i);
						String name = Fdto.getName(); // dto의 name 추출하여 저장
						int price = 	Fdto.getPrice();
						String picture = Fdto.getPicture(); // dto의 id 추출하여 저장
						String inform = Fdto.getInform(); // dto의 email 추출하여 저장
					   
						
						 


					    String NAME = URLEncoder.encode(name, "UTF-8") ;


					
				%>
				
				<div class="row">  
					<div class="cell">
						<%=name%>
					</div>
					<div class="cell">
						<%=price%>
					</div>

					<div class="cell">
						<img src="<%=picture%>" width=70 height=70></img>
					</div>
					<div class="cell">
						<%=inform%>
					</div>
					<div class="cell"><a href="modify_menu.jsp?name=<%=NAME%>">수정</a> </div>
					<div class="cell"><a href="delMidMenu.jsp?name=<%=NAME%>">삭제</a> </div>
						
				</div>

				 <%	}  %>
				

			</div>
        <input type="submit" value="돌아가기 "> 
</body>
</html>
