
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%
	if (session.getAttribute("ValidMem") == null) { //정상적이면 yes --> loginOK에서설정했음!
		/* loginOk.jsp 에서  지정된 결과에 따라
		yes 가 아니면 비 로그인이므로 */
		String name = (String)session.getAttribute("name");
%>
<jsp:forward page="login.jsp" />
<%
	}
	/*  관리자라면 아래로 수행  */
	String name = (String) session.getAttribute("name");
	String id = (String) session.getAttribute("id");
	
if (id.equals("admin")) {
		
		%>
		<jsp:forward page="admin.jsp" />
		<%
			}
	%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<!DOCTYPE HTML>

<html>
	<head>
		<title>사용자모드</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<!--[if lte IE 8]><script src="assets/js/ie/html5shiv.js"></script><![endif]-->
		<link rel="stylesheet" href="assets/css/main.css" />
		<!--[if lte IE 8]><link rel="stylesheet" href="assets/css/ie8.css" /><![endif]-->
	</head>
	<body>

		<!-- Header -->
			<section id="header">
				<header>
					<h1> 사용자모드</h1>
					<p><%=name%>님 반갑습니다</p>
					
				</header>
				<footer>
					<a href="orderList.jsp" class="button style2 scrolly-middle">주문하기</a>
					<a href="reviewList.jsp?id=<%=id %>" class="button style2 scrolly-middle">리뷰등록</a>
					<a href="modifyForUser.jsp" class="button style2 scrolly-middle">정보수정</a>
						<a href="logout.jsp" class="button style2 scrolly-middle">로그아웃</a>
				</footer>
				
			</section>

		<section id="footer">
		
			<div class="copyright">
				<ul class="menu">
					<li>&copy; Untitled. All rights reserved.</li><li> 개발자 : 이정록 2017년 12월 2일</li>
				</ul>
			</div>
		</section>

		<!-- Scripts -->
			<script src="assets/js/jquery.min.js"></script>
			<script src="assets/js/jquery.scrolly.min.js"></script>
			<script src="assets/js/jquery.poptrox.min.js"></script>
			<script src="assets/js/skel.min.js"></script>
			<script src="assets/js/util.js"></script>
			<!--[if lte IE 8]><script src="assets/js/ie/respond.min.js"></script><![endif]-->
			<script src="assets/js/main.js"></script>

	</body>
</html>