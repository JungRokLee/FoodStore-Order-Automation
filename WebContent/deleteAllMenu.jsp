<%@page import="Order_Package.OrderDao"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.sql.*"%>
<%@ page import="javax.naming.*"%>
<%@ page import="java.util.ArrayList"%>
<title>����</title>

<jsp:useBean id="dto" class="Order_Package.OrderDto" scope="page" />
<jsp:setProperty name="dto" property="*" />
<!--  * �� ��� property�� �ѹ��� set �ϰڴ�.  -->
<%
	request.setCharacterEncoding("EUC-KR");
	String id = (String) session.getAttribute("id");
	OrderDao fdao = OrderDao.getInstance();
	int ri = fdao.deleteAllFood(id);
	if (ri == 1) {
%>
<script language="javascript">
	alert("��� ����� ���� �Ǿ����ϴ�.");
 
	document.location.href = "mainOfstore.jsp?id="+'<%=id%>';

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

