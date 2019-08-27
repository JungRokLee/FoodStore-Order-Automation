<%@page import="Member_Package.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<% request.setCharacterEncoding("EUC-KR"); %>
<jsp:useBean id="dto" class="Member_Package.MemberDto" scope="page" />
<jsp:setProperty name="dto" property="*" /> 
  <!--  * 는 모든 property를 한번에 set 하겠다.  -->
<%
	String id = (String)session.getAttribute("id");
	dto.setId(id);
	/* dao 객체 생성후 dao.updateMember() 에 dto 값 전달하여 
	업데이트 후 결과 값 전달 받아 옴*/
	MemberDao dao = MemberDao.getInstance();
	int ri = dao.updateMember(dto);
	/* dao.updateMember(dto) 가 보고 올 것  */
	/* 전체 데이터를 가지고 dto 로 감 */
	if(ri == 1) {
%>
	<script language="javascript">
		alert("정보수정 되었습니다.");
		document.location.href="first.jsp";
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
