<%@ page import="Food_Package.FoodDao"%>
<%@ page import="Food_Package.FoodDto"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.sql.Timestamp" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.sql.*"%>
<%@ page import="javax.naming.*"%>
  <%@page import="java.net.URLDecoder"%>

<!DOCTYPE HTML>
<%
String name = request.getParameter("name");
String text = URLDecoder.decode(name, "UTF-8") ;


String id = (String)session.getAttribute("id"); 

%>
<html>
<head>
<title>메뉴등록</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<!--[if lte IE 8]><script src="assets/js/ie/html5shiv.js"></script><![endif]-->
<link rel="stylesheet" href="assets/css/main.css" />
<!--[if lte IE 8]><link rel="stylesheet" href="assets/css/ie8.css" /><![endif]-->
</head>
<body>
	<article class="container box style3">
		<section>
			<header>
				<h3>메뉴 등록</h3>
			</header>
			<form method="post" enctype="multipart/form-data" action="modifyMenuOk.jsp?id=<%=id%>">

				<div class="row">
					<div class="6u">
						<input class="text" type="text" name="name" value="<%=name%>"
							placeholder="음식명" />
					</div>
					<div class="6u">
						<input class="text" type="text"  name="price" value="" placeholder="가격" /> 
					</div>
				</div>
	<div class="row">
					
				</div>
				<div class="row">
					<div class="12u">
						사진 <input type="file" name="picture" size=30> <input type="submit" value="첨부">
					</div>
				</div>
			
				<div class="row">
					<div class="12u">
						<textarea name="inform" placeholder="음식을 설명하세요 "></textarea>
					</div>
				</div>
				<div class="row">
					<div class="12u">
						<ul class="actions">
							<li><input type="submit" value="등록하기" /></li>
							<li><input type="reset" class="style3" value="내용 지우기" /></li>

						</ul>
					</div>
				</div>
			</form>
		</section>
	</article>


	<section id="footer">

		<div class="copyright">
			<ul class="menu">
				<li>&copy; Untitled. All rights reserved.</li> <li>&copy; 개발자: 이정록</li>
				
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