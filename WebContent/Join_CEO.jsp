
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE HTML>
<%
	request.setCharacterEncoding("EUC-KR");
%>
<html>
	<head>
		<title>������ ���</title>
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
						<h3>������ ���</h3>
					</header>
					<form action="JoinOkStore.jsp" method="post">
						<div class="row">
							<div class="6u">
								<input class="text" type="text" name="id"  value="" placeholder="���̵�" />
							</div>
							<div class="6u">
								<input class="text" type="password" name="pw"  value="" placeholder="��й�ȣ" />
							</div>
						</div>
			
						<div class="row">
							<div class="12u">
								<input class="text" type="text" name="name" value="" placeholder="������ �̸�" />
							</div>
								<div class="12u">
								<input class="text" type="text" name="address" value="" placeholder="������ �ּ�" />
							</div>
						</div>
						<div class="row">
							<div class="12u">
								<textarea name="inform"  placeholder="������ �Ұ��� �Է��ϼ���"></textarea>
							</div>
						</div>
						<div class="row">
							<div class="12u">
								<ul class="actions">
									<li><input type="submit" value="�����ϱ�" /></li>
									<li><input type="reset" class="style3" value="���� �����" /></li>
									
								</ul>
							</div>
						</div>
					</form>
				</section>
			</article>
	

		<section id="footer">
			
			<div class="copyright">
				<ul class="menu">
					<li>&copy; Untitled. All rights reserved.</li><li>�Թ��� : ������</li>
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