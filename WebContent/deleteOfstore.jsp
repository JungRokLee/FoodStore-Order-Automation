<%@page import="Foodstore_Package.FoodStoreDao"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>


<%
	request.setCharacterEncoding("EUC-KR");
%>
<title>����</title>

<jsp:useBean id="dto" class="Foodstore_Package.FoodStoreDto" scope="page" />
<jsp:setProperty name="dto" property="*" />
<!--  * �� ��� property�� �ѹ��� set �ϰڴ�.  -->
<%
    String id= request.getParameter("id");
	dto.setId(id);
	FoodStoreDao fdao = FoodStoreDao.getInstance();
	int ri = fdao.deleteMember(dto);
	if (ri == 1) {
%>
<script language="javascript">
	alert("���� �Ǿ����ϴ�.");
	
	document.location.href = "listOfstore.jsp";
</script>
<%
	} else {
%>
<script language="javascript">
	alert("�������� ���� �Դϴ�.");
	history.go(-1);
</script>
<%
	}
%>

