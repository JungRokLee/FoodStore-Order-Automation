<%@page import="Member_Package.MemberDao"%>
<%@page import="Member_Package.MemberDto"%>
<%@page import="Foodstore_Package.FoodStoreDao"%>
<%@page import="Foodstore_Package.FoodStoreDto"%>
<%@ page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>

<%
	request.setCharacterEncoding("EUC-KR");
%>

<%
	String search_for = request.getParameter("search_for");

	String search_context = request.getParameter("search_context");

	
	MemberDao dao = MemberDao.getInstance();
	ArrayList<MemberDto> dtos = dao.SearchMember(search_for, search_context);
	if (dtos.size() > 1) {
		out.print("�˻��� ���� ��"   + dtos.size() + "�̸� ������ �Ʒ��� �����ϴ�");
	}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<form action="search_ok.jsp" method="post" name="memberSelect">
		<table width="600" cellpadding="0" cellspacing="0" border="1">
			<caption>ȸ�� ��� ����</caption>
			<tr>
				<td width="70">���̵�</td>
				<td width="70">�н�</td>
				<td width="70">�̸�</td>
				<td width="140">�̸���</td>
				<td width="250">�ּ�</td>
			</tr>
		</table>
		<%
			for (int i = 0; i < dtos.size(); i++) { // dtos �� size ��ŭ for �� ����
				MemberDto dto = dtos.get(i); 
				if (search_for.equals("���̵�")) {
					if (!search_context.equals(dto.getId())) {
		%>
		<script language="javascript">
			alert("���� ���̵𿡿�."); // 1 �̸� 
			history.back(); /* �������� �ڷ� ���� */
		</script>
		<%
			}
				} else if  (search_for.equals("�̸�"))
				{
					if (!search_context.equals(dto.getName())) {
						%>
		<script language="javascript">
			alert("���� �̸��̿���."); // 1 �̸� 
			history.back(); /* �������� �ڷ� ���� */
		</script>
		<%}
				}
				String name = dto.getName(); // dto�� name �����Ͽ� ����
				String pw = dto.getPw();
				String id = dto.getId(); // dto�� id �����Ͽ� ����
				String address = dto.getAddress(); // dto�� address �����Ͽ� ����
		%> 

		<table width="600" cellpadding="0" cellspacing="0" border="1">
			<tr>
				<td width="70"><%=id%></td>
				<td width="70"><%=pw%></td>
				<td width="70"><%=name%></td>
				<td width="250"><%=address%></td>
			</tr>
		</table>
		<%
		
		}
	%>
	<input type="button" value="�ڷΰ���" 
		onclick="javascript:window.location='memberSelect.jsp'">