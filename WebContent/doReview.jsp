<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>

</body>
</html>

<html>
	<head>
		<title>�����ۼ�</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<!--[if lte IE 8]><script src="assets/js/ie/html5shiv.js"></script><![endif]-->
		<link rel="stylesheet" href="assets/css/main.css" />
		<!--[if lte IE 8]><link rel="stylesheet" href="assets/css/ie8.css" /><![endif]-->
	</head>
	<body>
<%
String id = request.getParameter("id");
%>

		<!--�����ۼ�-->
			<article class="container box style3">
				<header>
					<h2>�����ۼ�</h2>
					
				</header>
				<form action="reviewOk.jsp?id=<%=id %>" method="post" name="doReview">
					<div class="row 50%">
						<div class="6u 12u$(mobile)"><input type="text" class="text" name="name" placeholder="�̸�" /></div>
						<div class="6u$ 12u$(mobile)"><input type="text" class="text" name="title" placeholder="����" /></div>
						<div class="12u$">
							<textarea name="inform" placeholder="����" ></textarea>
						</div>
						<div class="12u$">
							<ul class="actions">
								<li><input type="submit" value="�ۼ��Ϸ�" /></li>
								<li><input type="reset" class="style3" value="�Է¸���" /></li>
							</ul>
						</div>
					</div>
				</form>
			</article>

		

	
	

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