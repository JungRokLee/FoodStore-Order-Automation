
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%
	if (session.getAttribute("ValidMem") == null) { //�������̸� yes --> loginOK������������!
		/* loginOk.jsp ����  ������ ����� ����
		yes �� �ƴϸ� �� �α����̹Ƿ� */
		String name = (String)session.getAttribute("name");
%>
<jsp:forward page="login.jsp" />
<%
	}
	/*  �����ڶ�� �Ʒ��� ����  */
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
		<title>����ڸ��</title>
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
					<h1> ����ڸ��</h1>
					<p><%=name%>�� �ݰ����ϴ�</p>
					
				</header>
				<footer>
					<a href="orderList.jsp" class="button style2 scrolly-middle">�ֹ��ϱ�</a>
					<a href="reviewList.jsp?id=<%=id %>" class="button style2 scrolly-middle">������</a>
					<a href="modifyForUser.jsp" class="button style2 scrolly-middle">��������</a>
						<a href="logout.jsp" class="button style2 scrolly-middle">�α׾ƿ�</a>
				</footer>
				
			</section>

		<section id="footer">
		
			<div class="copyright">
				<ul class="menu">
					<li>&copy; Untitled. All rights reserved.</li><li> ������ : ������ 2017�� 12�� 2��</li>
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