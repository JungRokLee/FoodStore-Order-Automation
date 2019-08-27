<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
	<%@page import="java.net.URLEncoder"%>
<!DOCTYPE html>

<jsp:useBean id="dto" class="Foodstore_Package.FoodStoreDto" scope="page" />
<jsp:setProperty name="dto" property="*" />
<!--  * 는 모든 property를 한번에 set 하겠다.  -->
<%
    request.setCharacterEncoding("EUC-KR");
    
    String name= request.getParameter("name");
    String NAME = URLEncoder.encode(name, "UTF-8") ;

%>

<html>
<head>
<meta charset="utf-8" />
<title>자바스크립트</title>
<script>
var con_test = confirm("삭제하시겠습니까?");
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