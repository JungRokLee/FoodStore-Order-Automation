<%@page import="Member_Package.MemberDto"%>
<%@page import="Member_Package.MemberDao"%>
<%@page import="Foodstore_Package.FoodStoreDao"%>
<%@page import="Foodstore_Package.FoodStoreDto"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
	request.setCharacterEncoding("EUC-KR");
    /* ������ �Է� �� ���� �޾� ������  */
    	MemberDao dao = MemberDao.getInstance();
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	int checkStatus = dao.CheckBadUser(id);
	

	if (checkStatus == 1) { //�ҷ�ȸ��
%>
<script language="javascript">
	alert("�ҷ�ȸ���̹Ƿ� �α��� �� �� �����ϴ�.");
	history.go(-1); /* �� �ܰ� �ڷ� ���� */
</script>
<%
	} else { %>
	<%	MemberDao Mdao = MemberDao.getInstance();

	int checkNum = Mdao.userCheck(id, pw);
	/* dao.userCheck(id, pw) �� ���� �� �� */
	/* id�� pw�� ������ dao.userCheck(id, pw)�� ����  
	üũ�ϰ� ��� �� ��ȯ ����*/
	if(checkNum == -1) {  /* ���ϰ��� ���� ó�� -->MEMBER_LOGIN_IS_NOT(-1)*/
%>
		<script language="javascript">
			alert("���̵� �������� �ʽ��ϴ�.");
			history.go(-1);  /* �� �ܰ� �ڷ� ���� */
		</script>
<%
	} else if(checkNum == 0) {  /* ���ϰ��� ���� ó�� pw Ʋ���� */ 
%>
		<script language="javascript">
			alert("��й�ȣ�� Ʋ���ϴ�.");
			history.go(-1);
		</script>
<%
	} else if(checkNum == 1) {
		MemberDto dto = dao.getMember(id);  
		/* dao.getMember() �� ���� �� ��  */
		/*  ȣ�� MemberDto.getMember(id) ȣ���Ͽ� ó���� ��ȯ�� ���� */
		
		if(dto == null) {
			/* dto�� null �� �ƴϸ� �����͸� ������ �� ����  */
%>
		<script language="javascript">
			alert("�������� �ʴ� ȸ�� �Դϴ�.");
			history.go(-1);
		</script>
<%
		} else {   
			String name = dto.getName();
			session.setAttribute("id", id);
			session.setAttribute("name", name);
			session.setAttribute("ValidMem", "yes");
			response.sendRedirect("mainOfuser.jsp");
		/* ���������� ó���� �Ǹ� ���� �� ���� �� main �� �̵� */
		}
	}
%>

<%	FoodStoreDao Fdao = FoodStoreDao.getInstance();
	/*  MemberDao.getInstance(); ������ �� ��  */
	int FcheckNum = Fdao.userCheck(id, pw);
	/* dao.userCheck(id, pw) �� ���� �� �� */
	/* id�� pw�� ������ dao.userCheck(id, pw)�� ����  
	üũ�ϰ� ��� �� ��ȯ ����*/
	if(FcheckNum == -1) {  /* ���ϰ��� ���� ó�� -->MEMBER_LOGIN_IS_NOT(-1)*/
%>
		<script language="javascript">
			alert("���̵� �������� �ʽ��ϴ�.");
			history.go(-1);  /* �� �ܰ� �ڷ� ���� */
		</script>
<%
	} else if(FcheckNum == 0) {  /* ���ϰ��� ���� ó�� pw Ʋ���� */ 
%>
		<script language="javascript">
			alert("��й�ȣ�� Ʋ���ϴ�.");
			history.go(-1);
		</script>
<%
	} else if(FcheckNum == 1) {
		FoodStoreDto Fdto = Fdao.getMember(id);  

		
		if(Fdto == null) {
			/* dto�� null �� �ƴϸ� �����͸� ������ �� ����  */
%>
		<script language="javascript">
			alert("�������� �ʴ� ȸ�� �Դϴ�.");
			history.go(-1);
		</script>
<%
		} else {   
			String name = Fdto.getName();
			session.setAttribute("id", id);
			session.setAttribute("name", name);
			session.setAttribute("ValidMem", "yes");
			response.sendRedirect("mainOfstore.jsp");
		/* ���������� ó���� �Ǹ� ���� �� ���� �� main �� �̵� */
		}
	}
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html><head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head><body>
</body></html>