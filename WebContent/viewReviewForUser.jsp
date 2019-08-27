<%@ page import="java.sql.Timestamp" %>
<%@ page import="Review_Package.ReviewDao"%>
<%@ page import="Review_Package.ReviewDto"%>
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
	<form>


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


                <jsp:useBean id="dto" class="Food_Package.FoodDto" scope="page" />
				<jsp:setProperty name="dto" property="*" />
				<%
				String orderId = (String)session.getAttribute("id"); 
				
				request.setCharacterEncoding("EUC-KR");
				 String id= request.getParameter("id");
				      dto.setId(id);
	
				ReviewDao dao = ReviewDao.getInstance();
				ArrayList<ReviewDto> dtos = dao.SearchReview(id);
					 
				
					for (int i = 0; i < dtos.size(); i++) { // dtos 를 size 만큼 for 문 돌림
						ReviewDto Fdto = dtos.get(i);
						String name = Fdto.getName(); 
						String title = Fdto.getTitle(); 
						String inform = Fdto.getInform();
					   	Timestamp time = Fdto.getDate();
						
				%>
				
				<div class="row">  
					<div class="cell">
						<%=name%>
					</div>
					<div class="cell">
						<%=title%>
					</div>

					
					<div class="cell">
						<%=inform%>
					</div>
		
						<div class="cell">
						<%=time%>
					</div>
			
				</div>
     
				 <%	}  %>
				

			</div>

</body>
</html>
