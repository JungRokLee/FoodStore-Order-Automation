
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
<title>���� ���</title>

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
	<form action="doOrderOk.jsp?id=<%=id%>" method="post" name="doOrder">


		<div class="wrapper">
			<h3>
				<font color='white'>���� ��� </font>
			</h3>
			<div class="table">

				<div class="row header blue">
					<div class="cell">����</div>
					<div class="cell">����</div>
					<div class="cell">�̸�</div>
					<div class="cell">����</div>
				</div>


                <jsp:useBean id="dto" class="Food_Package.FoodDto" scope="page" />
				<jsp:setProperty name="dto" property="*" />
				<%
				
				out.print("id"+id);
				dto.setId(id);
				FoodDao dao = FoodDao.getInstance();
				ArrayList<FoodDto> dtos = dao.SearchFood(id);
					 
				
					for (int i = 0; i < dtos.size(); i++) { // dtos �� size ��ŭ for �� ����
						FoodDto Fdto = dtos.get(i);
						String name = Fdto.getName(); // dto�� name �����Ͽ� ����
						int price = 	Fdto.getPrice();
						String picture = Fdto.getPicture(); // dto�� id �����Ͽ� ����
						String inform = Fdto.getInform(); // dto�� email �����Ͽ� ����
						
				%>
				
				<div class="row">  
					<div class="cell">
						<img src="<%=picture%>" width=150 height=150></img>
					</div>
					<div class="cell">
					<input type="checkbox" checked="checked" name="Price" value=<%=price%>><%=price%>
					</div>

					<div class="cell">
						<input type="checkbox" name="FoodName" value=<%=name%>><%=name%>
					</div>
					<div class="cell">
						<%=inform%>
					</div>
				</div>
     
				<tr>
		

			
			<td><font color=white>���̺��ȣ</font></td>
			<td><select name='TableNum'>
					<option value= 0 selected>-- ���� --</option>
					<option value='1'>1��</option>
					<option value='2'>2��</option>
					<option value='3'>3��</option>
			</select></td>
		</tr>

		<tr>
		<td><font color=white>����</font></td>
			<td><select name='Count'>
					<option value= 0 selected>=������ �����ϼ���=</option>
					<option value='1'>1��</option>
					<option value='2'>2��</option>
					<option value='3'>3��</option>
			</select></td>
			
			
		</tr>	

		
					
				 <%	}  %>
				
<input type="submit" value="�ֹ��Ϸ� "> 
			</div>

</body>
</html>
