<%@page import="java.sql.Timestamp"%>
<%@page import="Member_Package.*"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%
	request.setCharacterEncoding("EUC-KR");
%>
<!-- �׼� �±� ���  -->
<jsp:useBean id="dto" class="Member_Package.MemberDto" />
<!--  �ڹ� ���� ����ϴµ� �̸��� dto �� �ϰڴ�.   -->
<jsp:setProperty name="dto" property="*" />
<!--  * �� ��� property�� �ѹ��� set �ϰڴ�.  -->
<!-- dto�� �ʵ��� �������� name ���� ���� �� �ϰ� set �ϱ� ���� ���  -->

<!-- UI �� ���� ��ſ� ���� ������ ���Ե�
���⼭ ȸ�� ���� ó���� ���� �ʰ� Dao�� �����͸� ���´ٰ� 
���⼭�� ��ȯ �޾� ����� ó��  -->
<%
	
MemberDao dao = MemberDao.getInstance();
		int ri = dao.enrollBad(dto);
		if (ri == MemberDao.MEMBER_JOIN_SUCCESS) {	
%>
<script language="javascript">
	alert("�ҷ�ȸ������ ����߽��ϴ�.");
	document.location.href = "listOfuser.jsp"; 
</script>
<%
	} else {
			/* ��ȯ����  0 �̸� */
%>
<script language="javascript">
	alert("�ҷ�ȸ�� ��Ͽ� �����߽��ϴ�.");
	document.location.href = "listOfuser.jsp";
</script>
<%
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
</body>
</html>