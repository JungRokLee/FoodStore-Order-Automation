
<%@ page import="Food_Package.FoodDao"%>
<%@ page import="Food_Package.FoodDto"%>
<%@ page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%String id = request.getParameter("id"); %>
<!DOCTYPE html>
<html lang="kr">
<head> 
<meta charset="UTF-8">
<title>음식 목록</title>

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
	width: auto;
	height: auto;
}

.wrapper {
	margin: 0 auto;
	padding: 40px;
	max-width: 900 px;
}

.table {
	margin: 0 0 40px 0;
	width: 100%;
	box-shadow: 0 1px 3px rgba(0, 0, 0, 0.2);
	display: table;
	width: auto;
	height: auto;
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
	<form action="doOrderOk.jsp?id=<%=id%>" method="post" name="doOrder">


		<div class="wrapper">
			<h3>
				<font color='white'>음식 목록 </font>
			</h3>
			<p>	<font color='white'>주문하고자 하는 음식명을 선택해주세요 </font></p>
			<div class="table">

				<div class="row header blue">
					<div class="cell">사진</div>
					<div class="cell">가격</div>
					<div class="cell">이름</div>
					<div class="cell">정보</div>
				</div>


                <jsp:useBean id="dto" class="Food_Package.FoodDto" scope="page" />
				<jsp:setProperty name="dto" property="*" />
				<%
				dto.setId(id);
				FoodDao dao = FoodDao.getInstance();
				ArrayList<FoodDto> dtos = dao.SearchFood(id);
					 
				
					for (int i = 0; i < dtos.size(); i++) { // dtos 를 size 만큼 for 문 돌림
						FoodDto Fdto = dtos.get(i);
						String name = Fdto.getName(); // dto의 name 추출하여 저장
						int price = 	Fdto.getPrice();
						String picture = Fdto.getPicture(); // dto의 id 추출하여 저장
						String inform = Fdto.getInform(); // dto의 email 추출하여 저장
						
				%>
				
				<div class="row">  
					<div class="cell">
						<img src="<%=picture%>" width=150 height=150></img>
					</div>
					<div class="cell">
					<input type="checkbox" checked="checked" name="Price" value=<%=price%>><%=price%>
					</div>

					<div class="cell" >
						<input type="checkbox" name="FoodName" value=<%=name%>><%=name%>
					</div>
					<div class="cell">
						<%=inform%>
					</div>
					
				
	<input type="checkbox"  name="TableNum" value="1">1번테이블
	<input type="checkbox"  name="TableNum" value="2">2번테이블
	<input type="checkbox"  name="TableNum" value="3">3번테이블
		
	<input type="checkbox"  name="Count" value="1">1개
	<input type="checkbox"  name="Count" value="2">2개
	<input type="checkbox"  name="Count" value="3">3개
				</div>
     
			
			
		

		
					
				 <%	}  %>
				
<input type="submit" value="주문완료 "> 
			</div>

</body>
</html>
