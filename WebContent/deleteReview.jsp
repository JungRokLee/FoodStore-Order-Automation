<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.util.Date"  %>
<%@page import="Review_Package.ReviewDao"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.sql.*"%>
<%@ page import="javax.naming.*"%>
<%@ page import="java.util.ArrayList"%>
<title>����</title>
 
<jsp:useBean id="dto" class="Review_Package.ReviewDto" scope="page" />
<jsp:setProperty name="dto" property="*" />
<!--  * �� ��� property�� �ѹ��� set �ϰڴ�.  -->
<%
       
    request.setCharacterEncoding("EUC-KR");
    String id = (String)session.getAttribute("id"); 
    int num = Integer.parseInt(request.getParameter("num"));

	ReviewDao dao = ReviewDao.getInstance();
	int ri = dao.deleteReview(num);
	if (ri == 1) {
%>
<script language="javascript">
	alert("���� �Ǿ����ϴ�.");
 
	document.location.href = "adminReview.jsp?id="+'<%=id%>';
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

