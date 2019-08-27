<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@page import="Order_Package.*"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>

	<%
		request.setCharacterEncoding("EUC-KR");

		String id = request.getParameter("id");
		String[] food_name = request.getParameterValues("FoodName");
		String[] StableNum = request.getParameterValues("TableNum");
		String[] Scount = request.getParameterValues("Count");
		String[] SPrice = request.getParameterValues("Price");

		int[] price = null;
		int[] count = null;
		int[] table_num = null;
		//int 형 변환 Price////
		if (SPrice != null) {
			price = new int[SPrice.length];
			for (int i = 0; i < SPrice.length; i++) {
				price[i] = Integer.parseInt(SPrice[i]);
			}
		}

		/////int 형 변환  Count////

		if (Scount != null) {
			count = new int[Scount.length];
			for (int i = 0; i < Scount.length; i++) {
				count[i] = Integer.parseInt(Scount[i]);
			}
		}
		
		/////int 형 변환  table_num////

		if (StableNum != null) {
			table_num = new int[StableNum.length];
			for (int i = 0; i < StableNum.length; i++) {
				table_num[i] = Integer.parseInt(StableNum[i]);
			}
		}
	%>
	<jsp:useBean id="dto" class="Order_Package.OrderDto" />
	<jsp:setProperty name="dto" property="*" />
	<%OrderDao dao = OrderDao.getInstance();
		for (int i = 0; i < food_name.length; i++) {
			dto.setId(id);
			dto.setFoodname(food_name[i]);
			dto.setPrice(price[i]);
			dto.setCount(count[i]);
			dto.setTable_num(table_num[i]);
			
			dao.insertOrder(dto);
		
		}
		dao.updateTotal(dto);
	

	%>
		<script language="javascript">
	alert("주문 완료되었습니다.");
	document.location.href = "mainOfuser.jsp";
</script>
</body>
</html>