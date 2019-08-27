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
		out.print("검색된 글은 총"   + dtos.size() + "이며 내용은 아래와 같습니다");
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
			<caption>회원 목록 보기</caption>
			<tr>
				<td width="70">아이디</td>
				<td width="70">패스</td>
				<td width="70">이름</td>
				<td width="140">이메일</td>
				<td width="250">주소</td>
			</tr>
		</table>
		<%
			for (int i = 0; i < dtos.size(); i++) { // dtos 를 size 만큼 for 문 돌림
				MemberDto dto = dtos.get(i); 
				if (search_for.equals("아이디")) {
					if (!search_context.equals(dto.getId())) {
		%>
		<script language="javascript">
			alert("없는 아이디에요."); // 1 이면 
			history.back(); /* 브라우저를 뒤로 돌림 */
		</script>
		<%
			}
				} else if  (search_for.equals("이름"))
				{
					if (!search_context.equals(dto.getName())) {
						%>
		<script language="javascript">
			alert("없는 이름이에요."); // 1 이면 
			history.back(); /* 브라우저를 뒤로 돌림 */
		</script>
		<%}
				}
				String name = dto.getName(); // dto의 name 추출하여 저장
				String pw = dto.getPw();
				String id = dto.getId(); // dto의 id 추출하여 저장
				String address = dto.getAddress(); // dto의 address 추출하여 저장
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
	<input type="button" value="뒤로가기" 
		onclick="javascript:window.location='memberSelect.jsp'">