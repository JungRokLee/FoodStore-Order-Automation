<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>


<html>
<head>
<meta charset="utf-8" />
<title>삭제확인</title>

<%
    request.setCharacterEncoding("EUC-KR");
    
    String id= request.getParameter("id");
	
%>
<script>
var con_test = confirm("모든 메뉴를 삭제하시겠습니까?");
if(con_test == true){

	document.location.href = "deleteAllMenu.jsp?id="+'<%=id%>';
}
else if(con_test == false){
	document.location.href = "menuLsit.jsp";
}
</script>
</head>
<body>
</body>
</html>