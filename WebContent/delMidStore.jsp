<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>

<jsp:useBean id="dto" class="Foodstore_Package.FoodStoreDto" scope="page" />
<jsp:setProperty name="dto" property="*" />
<!--  * �� ��� property�� �ѹ��� set �ϰڴ�.  -->
<%
    String id= request.getParameter("id");
	
%>

<html>
<head>
<meta charset="utf-8" />
<title>�ڹٽ�ũ��Ʈ</title>
<script>
var con_test = confirm("�����Ͻðڽ��ϱ�?");
if(con_test == true){

	document.location.href = "deleteOfstore.jsp?id=<%=id%>";
}
else if(con_test == false){
	document.location.href = "listOfstore.jsp";
}
</script>
</head>
<body>
</body>
</html>