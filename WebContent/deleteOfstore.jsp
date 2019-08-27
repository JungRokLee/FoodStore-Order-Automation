<%@page import="Foodstore_Package.FoodStoreDao"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>


<%
	request.setCharacterEncoding("EUC-KR");
%>
<title>삭제</title>

<jsp:useBean id="dto" class="Foodstore_Package.FoodStoreDto" scope="page" />
<jsp:setProperty name="dto" property="*" />
<!--  * 는 모든 property를 한번에 set 하겠다.  -->
<%
    String id= request.getParameter("id");
	dto.setId(id);
	FoodStoreDao fdao = FoodStoreDao.getInstance();
	int ri = fdao.deleteMember(dto);
	if (ri == 1) {
%>
<script language="javascript">
	alert("삭제 되었습니다.");
	
	document.location.href = "listOfstore.jsp";
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

