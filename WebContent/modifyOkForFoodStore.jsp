<%@page import="Foodstore_Package.FoodStoreDao"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<% request.setCharacterEncoding("EUC-KR"); %>
<jsp:useBean id="dto" class="Foodstore_Package.FoodStoreDto" scope="page" />
<jsp:setProperty name="dto" property="*" /> 
  <!--  * �� ��� property�� �ѹ��� set �ϰڴ�.  -->
<%
	String id = (String)session.getAttribute("id");


	dto.setId(id);
	

	/* dao ��ü ������ dao.updateMember() �� dto �� �����Ͽ� 
	������Ʈ �� ��� �� ���� �޾� ��*/
	FoodStoreDao dao = FoodStoreDao.getInstance();
	int ri = dao.updateMember(dto);
	/* dao.updateMember(dto) �� ���� �� ��  */
	/* ��ü �����͸� ������ dto �� �� */
	if(ri == 1) {
%>
	<script language="javascript">
		alert("�������� �Ǿ����ϴ�.");
		document.location.href="first.jsp";
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
