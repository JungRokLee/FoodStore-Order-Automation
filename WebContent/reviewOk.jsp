<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@page import="Review_Package.*"%>


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
		String name = request.getParameter("name");
	    String title = request.getParameter("title");
		String inform = request.getParameter("inform");
	%>
	<jsp:useBean id="dto" class="Review_Package.ReviewDto" />
	<jsp:setProperty name="dto" property="*" />
	<%
	
			ReviewDao dao = ReviewDao.getInstance();
			dao.insertReview(dto);
		
		

	

	%>
		<script language="javascript">
		document.location.href = "viewReview.jsp?id="+'<%=id%>';

</script> 
</body>
</html>