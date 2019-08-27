<%@page import="Food_Package.FoodDao"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.sql.*"%>
<%@ page import="javax.naming.*"%>
<%@ page import="java.util.ArrayList"%>
    <%@page import="java.net.URLDecoder"%>
<title>삭제</title>
 
<jsp:useBean id="dto" class="Food_Package.FoodDto" scope="page" />
<jsp:setProperty name="dto" property="*" />
<!--  * 는 모든 property를 한번에 set 하겠다.  -->
<%
    request.setCharacterEncoding("EUC-KR");
    String id = (String)session.getAttribute("id"); 
	String name = request.getParameter("name");
	FoodDao fdao = FoodDao.getInstance();
	int ri = fdao.deleteFood(name);
	if (ri == 1) {
%>
<script language="javascript">
	alert("삭제 되었습니다.");
 
	document.location.href = "menuList.jsp?id="+'<%=id%>';
</script>
<%
	} else {
%>
<script language="javascript">
	alert("정보수정 실패 입니다.");
	history.go(-1);
</script>
<%
	}
%>

