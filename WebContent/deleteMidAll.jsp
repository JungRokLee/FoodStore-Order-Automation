<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>


<html>
<head>
<meta charset="utf-8" />
<title>����Ȯ��</title>

<%
    request.setCharacterEncoding("EUC-KR");
    
    String id= request.getParameter("id");
	
%>
<script>
var con_test = confirm("��� �޴��� �����Ͻðڽ��ϱ�?");
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