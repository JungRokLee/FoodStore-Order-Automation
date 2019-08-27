<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.util.Date"  %>
<%@page import="Review_Package.ReviewDao"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.sql.*"%>
<%@ page import="javax.naming.*"%>
<%@ page import="java.util.ArrayList"%>
<title>삭제</title>
 
<jsp:useBean id="dto" class="Review_Package.ReviewDto" scope="page" />
<jsp:setProperty name="dto" property="*" />
<!--  * 는 모든 property를 한번에 set 하겠다.  -->
<%
       
    request.setCharacterEncoding("EUC-KR");
    String id = (String)session.getAttribute("id"); 
    int num = Integer.parseInt(request.getParameter("num"));

	ReviewDao dao = ReviewDao.getInstance();
	int ri = dao.deleteReview(num);
	if (ri == 1) {
%>
<script language="javascript">
	alert("삭제 되었습니다.");
 
	document.location.href = "adminReview.jsp?id="+'<%=id%>';
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

