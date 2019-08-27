<%@page import="java.sql.Timestamp"%>
<%@page import="Member_Package.*"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%
	request.setCharacterEncoding("EUC-KR");
%>
<!-- 액션 태그 사용  -->
<jsp:useBean id="dto" class="Member_Package.MemberDto" />
<!--  자바 빈을 사용하는데 이름을 dto 로 하겠다.   -->
<jsp:setProperty name="dto" property="*" />
<!--  * 는 모든 property를 한번에 set 하겠다.  -->
<!-- dto의 필드명과 가입폼의 name 값이 같을 때 일괄 set 하기 위해 사용  -->

<!-- UI 가 없는 대신에 많은 로직이 포함됨
여기서 회원 가입 처리를 하지 않고 Dao로 데이터를 보냈다가 
여기서는 반환 받아 결과만 처리  -->
<%
	
MemberDao dao = MemberDao.getInstance();
	if (dao.confirmId(dto.getId()) == MemberDao.MEMBER_EXISTENT) { //1이면-->존재하면은!
		/* 폼에서 입력한 ID를 dto 객체에 담고, 그 id를 뽑아서 
		confirmId()로 보냈다가 결과 반환  결과 값을 이곳으로 돌려 줌 
		id 값을 confirmId()로 보냈다가 0 이나 1 을 반환 받음*/
%>
<script language="javascript">
	alert("아이디가 이미 존재 합니다."); // 1 이면 
	history.back(); /* 브라우저를 뒤로 돌림 */
</script>
<%
	} else { // 0 이면 
		/* 반환값이 1이 아니면 위에서 생성된 dto 객체를 가지고 
		dao.insertMember()로 갔다가 결과를 받아 ri 에 저장*/
		int ri = dao.insertMember(dto);
		/* dao.insertMember() 가 보고 올 것*/
		if (ri == MemberDao.MEMBER_JOIN_SUCCESS) {
			/* 반환값이  1 이면 */
			session.setAttribute("id", dto.getId()); //-->회원가입하자마자 세션저장 -->다른곳에서 이걸 사용
%>
<script language="javascript">
	alert("회원가입을 축하 합니다.");
	document.location.href = "first.jsp"; 
</script>
<%
	} else {
			/* 반환값이  0 이면 */
%>
<script language="javascript">
	alert("회원가입에 실패했습니다.");
	document.location.href = "main.jsp";
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