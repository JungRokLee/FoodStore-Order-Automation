<%@page import="java.sql.Timestamp"%>
<%@page import="Foodstore_Package.*"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%
	request.setCharacterEncoding("EUC-KR");
%>
<!-- �׼� �±� ���  -->
<jsp:useBean id="dto" class="Foodstore_Package.FoodStoreDto" />
<!--  �ڹ� ���� ����ϴµ� �̸��� dto �� �ϰڴ�.   -->
<jsp:setProperty name="dto" property="*" />
<!--  * �� ��� property�� �ѹ��� set �ϰڴ�.  -->
<!-- dto�� �ʵ��� �������� name ���� ���� �� �ϰ� set �ϱ� ���� ���  -->

<!-- UI �� ���� ��ſ� ���� ������ ���Ե�
���⼭ ȸ�� ���� ó���� ���� �ʰ� Dao�� �����͸� ���´ٰ� 
���⼭�� ��ȯ �޾� ����� ó��  -->
<%
	
FoodStoreDao dao = FoodStoreDao.getInstance();
	if (dao.confirmId(dto.getId()) == FoodStoreDao.MEMBER_EXISTENT) { //1�̸�-->�����ϸ���!
		/* ������ �Է��� ID�� dto ��ü�� ���, �� id�� �̾Ƽ� 
		confirmId()�� ���´ٰ� ��� ��ȯ  ��� ���� �̰����� ���� �� 
		id ���� confirmId()�� ���´ٰ� 0 �̳� 1 �� ��ȯ ����*/
%>
<script language="javascript">
	alert("���̵� �̹� ���� �մϴ�."); // 1 �̸� 
	history.back(); /* �������� �ڷ� ���� */
</script>
<%
	} else { // 0 �̸� 
		/* ��ȯ���� 1�� �ƴϸ� ������ ������ dto ��ü�� ������ 
		dao.insertMember()�� ���ٰ� ����� �޾� ri �� ����*/
		int ri = dao.insertMember(dto);
		/* dao.insertMember() �� ���� �� ��*/
		if (ri == FoodStoreDao.MEMBER_JOIN_SUCCESS) {
			/* ��ȯ����  1 �̸� */
			session.setAttribute("id", dto.getId()); //-->ȸ���������ڸ��� �������� -->�ٸ������� �̰� ���
%> 
<script language="javascript">
	alert("ȸ�������� ���� �մϴ�.");
	document.location.href = "first.jsp"; 
</script>
<%
	} else {
			/* ��ȯ����  0 �̸� */
%>
<script language="javascript">
	alert("ȸ�����Կ� �����߽��ϴ�.");
	document.location.href = "first.jsp";
</script>
<%
	}
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