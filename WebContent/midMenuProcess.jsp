<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<!DOCTYPE HTML>


<%
	request.setCharacterEncoding("EUC-KR");
String id = (String)session.getAttribute("id"); %>
<html>
	<head>
		<title>음식점 관리 시스템</title>
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
					<h1> 메뉴관리</h1>
					<p>메뉴 등록 및 수정을 할 수 있습니다.</p>
				</header>
				<footer>
					<a href="menuList.jsp?id=<%=id%>" class="button style1 scrolly-middle">메뉴보기</a>
					<a href="insertMenu.jsp" class="button style1 scrolly-middle">메뉴등록</a>
					
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