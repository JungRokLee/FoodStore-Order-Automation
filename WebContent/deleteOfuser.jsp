<%@page import="Member_Package.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>

<%
	request.setCharacterEncoding("EUC-KR");
%>
<jsp:useBean id="dto" class="Member_Package.MemberDto" scope="page" />
<jsp:setProperty name="dto" property="*" />
<!--  * �� ��� property�� �ѹ��� set �ϰڴ�.  -->
<%
    String id= request.getParameter("id");
	//String id = (String) session.getAttribute("id");
	//out.print("id==>"+id);
	dto.setId(id);
	/* dao ��ü ������ dao.updateMember() �� dto �� �����Ͽ� 
	������Ʈ �� ��� �� ���� �޾� ��*/
	MemberDao dao = MemberDao.getInstance();
	int ri = dao.deleteMember(dto);
	/* dao.updateMember(dto) �� ���� �� ��  */
	/* ��ü �����͸� ������ dto �� �� */
	if (ri == 1) {
%>
<script language="javascript">
	alert("���� �Ǿ����ϴ�.");
	document.location.href = "listOfuser.jsp";
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
