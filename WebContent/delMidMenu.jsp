<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
	<%@page import="java.net.URLEncoder"%>
<!DOCTYPE html>

<jsp:useBean id="dto" class="Foodstore_Package.FoodStoreDto" scope="page" />
<jsp:setProperty name="dto" property="*" />
<!--  * �� ��� property�� �ѹ��� set �ϰڴ�.  -->
<%
    request.setCharacterEncoding("EUC-KR");
    
    String name= request.getParameter("name");
    String NAME = URLEncoder.encode(name, "UTF-8") ;

%>

<html>
<head>
<meta charset="utf-8" />
<title>�ڹٽ�ũ��Ʈ</title>
<script>
var con_test = confirm("�����Ͻðڽ��ϱ�?");
if(con_test == true){

	document.location.href = "deleteOfMenu.jsp?name=<%=NAME%>";
}
else if(con_test == false){
	document.location.href = "menuLsit.jsp";
}
</script>
</head>
<body>
</body>
</html>