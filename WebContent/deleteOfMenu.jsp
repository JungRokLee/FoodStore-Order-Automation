<%@page import="Food_Package.FoodDao"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.sql.*"%>
<%@ page import="javax.naming.*"%>
<%@ page import="java.util.ArrayList"%>
    <%@page import="java.net.URLDecoder"%>
<title>����</title>
 
<jsp:useBean id="dto" class="Food_Package.FoodDto" scope="page" />
<jsp:setProperty name="dto" property="*" />
<!--  * �� ��� property�� �ѹ��� set �ϰڴ�.  -->
<%
    request.setCharacterEncoding("EUC-KR");
    String id = (String)session.getAttribute("id"); 
	String name = request.getParameter("name");
	FoodDao fdao = FoodDao.getInstance();
	int ri = fdao.deleteFood(name);
	if (ri == 1) {
%>
<script language="javascript">
	alert("���� �Ǿ����ϴ�.");
 
	document.location.href = "menuList.jsp?id="+'<%=id%>';
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

