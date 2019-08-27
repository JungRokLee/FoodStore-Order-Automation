<%@page import="Member_Package.MemberDto"%>
<%@page import="Member_Package.MemberDao"%>
<%@page import="Foodstore_Package.FoodStoreDao"%>
<%@page import="Foodstore_Package.FoodStoreDto"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
	request.setCharacterEncoding("EUC-KR");
    /* 폼에서 입력 한 값을 받아 저장함  */
    	MemberDao dao = MemberDao.getInstance();
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	int checkStatus = dao.CheckBadUser(id);
	

	if (checkStatus == 1) { //불량회원
%>
<script language="javascript">
	alert("불량회원이므로 로그인 할 수 없습니다.");
	history.go(-1); /* 한 단계 뒤로 돌림 */
</script>
<%
	} else { %>
	<%	MemberDao Mdao = MemberDao.getInstance();

	int checkNum = Mdao.userCheck(id, pw);
	/* dao.userCheck(id, pw) 가 보고 올 것 */
	/* id와 pw를 가지고 dao.userCheck(id, pw)로 가서  
	체크하고 결과 값 반환 받음*/
	if(checkNum == -1) {  /* 리턴값에 따라 처리 -->MEMBER_LOGIN_IS_NOT(-1)*/
%>
		<script language="javascript">
			alert("아이디가 존재하지 않습니다.");
			history.go(-1);  /* 한 단계 뒤로 돌림 */
		</script>
<%
	} else if(checkNum == 0) {  /* 리턴값에 따라 처리 pw 틀리면 */ 
%>
		<script language="javascript">
			alert("비밀번호가 틀립니다.");
			history.go(-1);
		</script>
<%
	} else if(checkNum == 1) {
		MemberDto dto = dao.getMember(id);  
		/* dao.getMember() 가 보고 올 것  */
		/*  호출 MemberDto.getMember(id) 호출하여 처리후 반환값 받음 */
		
		if(dto == null) {
			/* dto가 null 이 아니면 데이터를 가지고 온 것임  */
%>
		<script language="javascript">
			alert("존재하지 않는 회원 입니다.");
			history.go(-1);
		</script>
<%
		} else {   
			String name = dto.getName();
			session.setAttribute("id", id);
			session.setAttribute("name", name);
			session.setAttribute("ValidMem", "yes");
			response.sendRedirect("mainOfuser.jsp");
		/* 정상적으로 처리가 되면 세션 값 저장 후 main 로 이동 */
		}
	}
%>

<%	FoodStoreDao Fdao = FoodStoreDao.getInstance();
	/*  MemberDao.getInstance(); 가보고 올 것  */
	int FcheckNum = Fdao.userCheck(id, pw);
	/* dao.userCheck(id, pw) 가 보고 올 것 */
	/* id와 pw를 가지고 dao.userCheck(id, pw)로 가서  
	체크하고 결과 값 반환 받음*/
	if(FcheckNum == -1) {  /* 리턴값에 따라 처리 -->MEMBER_LOGIN_IS_NOT(-1)*/
%>
		<script language="javascript">
			alert("아이디가 존재하지 않습니다.");
			history.go(-1);  /* 한 단계 뒤로 돌림 */
		</script>
<%
	} else if(FcheckNum == 0) {  /* 리턴값에 따라 처리 pw 틀리면 */ 
%>
		<script language="javascript">
			alert("비밀번호가 틀립니다.");
			history.go(-1);
		</script>
<%
	} else if(FcheckNum == 1) {
		FoodStoreDto Fdto = Fdao.getMember(id);  

		
		if(Fdto == null) {
			/* dto가 null 이 아니면 데이터를 가지고 온 것임  */
%>
		<script language="javascript">
			alert("존재하지 않는 회원 입니다.");
			history.go(-1);
		</script>
<%
		} else {   
			String name = Fdto.getName();
			session.setAttribute("id", id);
			session.setAttribute("name", name);
			session.setAttribute("ValidMem", "yes");
			response.sendRedirect("mainOfstore.jsp");
		/* 정상적으로 처리가 되면 세션 값 저장 후 main 로 이동 */
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