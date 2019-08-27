<%@page import="Foodstore_Package.FoodStoreDao"%>
<%@page import="Foodstore_Package.FoodStoreDto"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<% request.setCharacterEncoding("EUC-KR"); %>
<%
	String id = (String)session.getAttribute("id");
   // 유니크 한 id 값을 받아서 
	FoodStoreDao dao = FoodStoreDao.getInstance();
   // 싱글턴 dao 객체 생성 후 
	FoodStoreDto dto = dao.getMember(id);  //이제 읽어온 값이 dto에 다 들어가있음!
   // dao 객체 중애서 id 를 매개변수로 하여 getMember() 호출하여
   // 그 결과를 dto 에 저장 
%>
<!DOCTYPE HTML>

<html>
	<head>
		<title>음식점 정보 수정</title>
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
						<h3>정보 수정</h3>
					</header>
					<form action="modifyOkForFoodStore.jsp" method="post" name="reg_frm">
						<div class="row">
							<div class="6u">
								<input class="text" type="text" name="id"  value="<%= dto.getId() %>"  />
							</div>
							<div class="6u">
								<input class="text" type="password" name="pw"  value="" placeholder="비밀번호" />
							</div>
						</div>
						</div>
						<div class="row">
							<div class="12u">
								<input class="text" type="text" name="name" value="" placeholder="음식점 이름" />
							</div>
								<div class="12u">
								<input class="text" type="text" name="address" value="" placeholder="음식점 주소" />
							</div>
						</div>
						<div class="row">
							<div class="12u">
								<textarea name="inform"  placeholder="음식점 소개를 입력하세요"></textarea>
							</div>
						</div>
						<div class="row">
							<div class="12u">
								<ul class="actions">
									<li><input type="submit" value="전송하기" /></li>
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
					<li>&copy; Untitled. All rights reserved.</li><li>개발자 : 이정록</a></li>
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