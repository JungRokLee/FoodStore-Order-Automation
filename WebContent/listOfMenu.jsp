<%@ page import="java.util.Date"  %>
<%@ page import="Order_Package.OrderDao"%>
<%@ page import="Order_Package.OrderDto"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.sql.Timestamp" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>


                <jsp:useBean id="dto" class="Order_Package.OrderDto" scope="page" />
				<jsp:setProperty name="dto" property="*" />
<%
request.setCharacterEncoding("EUC-KR");
String id = (String)session.getAttribute("id");
out.print("id"+id);
dto.setId(id);
OrderDao dao = OrderDao.getInstance();
ArrayList<OrderDto> dtos = dao.SearchOrder(id);
 int totalprice = dao.getTotal(id);


%>

<!DOCTYPE html>
<html lang="kr">
<head> 
<meta charset="UTF-8">
<title>�ֹ� ���</title>

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
	<form action="deleteMidAll.jsp?id=<%=id%>" method="post" name="doOrder">


		<div class="wrapper">
			<h3>
				<font color='white'>�ֹ� ��� </font>
				
			</h3>	<font color='white'>�� �Ǹž� : <%=totalprice %>��!!!</font> 
			<a href="showBest.jsp"><font color='white' size=2>����Ʈ���� Ȯ��(Ŭ��)</font></a>
			<div class="table">

				<div class="row header blue">
					
					<div class="cell">���̺��ȣ</div>
					<div class="cell">�����̸�</div>
					<div class="cell">����</div>
					<div class="cell">����</div>
					<div class="cell">�� �Ǹž�</div>
					<div class="cell">�ֹ��ð�</div>
				</div>


				<%
				
				
					for (int i = 0; i < dtos.size(); i++) { // dtos �� size ��ŭ for �� ����
						OrderDto Fdto = dtos.get(i);
					
					int table_num = Fdto.getTable_num();
					String food_name = Fdto.getFoodname();
					int count = Fdto.getCount();
					int price = Fdto.getPrice();
					int total = Fdto.getTotal();
					Timestamp timestamp =Fdto.getDate();
					Date time = new Date(timestamp.getTime()); 
						
					
					
				
					
				%>
				
				<div class="row">  
				
					<div class="cell">
					<%=table_num%>
					</div>

					<div class="cell">
						<%=food_name%>
					</div>
					<div class="cell">
						<%=count%>
					</div>
					<div class="cell">
						<%=price%>��
					</div>
					<div class="cell">
						<%=total%>��
					</div>
					<div class="cell">
						<%=timestamp%>
					</div>
				
					<div class="cell">
						
					</div>
				</div>
     
				<tr>
		</tr>					
				 <%	}  %>
				 
				 	
<input type="submit" value="�ֹ����� �ʱ�ȭ "> 

<a href="showDate.jsp"><font color='white' size=2>�ֹ� ��¥���� ����</font></a>
</body>
</html>
