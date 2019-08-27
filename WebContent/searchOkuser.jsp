<%@ page import="Member_Package.MemberDto"%>
<%@ page import="Member_Package.MemberDao"%>
<%@ page import="Foodstore_Package.FoodStoreDao"%>
<%@ page import="Foodstore_Package.FoodStoreDto"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.sql.Timestamp" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>

<!DOCTYPE html>
<html lang="kr">
	<head>
	<meta charset="UTF-8">
	<title>�������</title>

<style rel="stylesheet">
body {
  font-family: "Helvetica Neue", Helvetica, Arial;
  font-size: 14px;
  line-height: 20px;
  font-weight: 400;
  color: #3b3b3b;
  -webkit-font-smoothing: antialiased;
  font-smoothing: antialiased;
  background: #2b2b2b;
}

.wrapper {
  margin: 0 auto;
  padding: 40px;
  max-width: 800px;
}

.table {
  margin: 0 0 40px 0;
  width: 100%;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.2);
  display: table;
}
@media screen and (max-width: 580px) {
  .table {
    display: block;
  }
}

.row {
  display: table-row;
  background: #f6f6f6;
}
.row:nth-of-type(odd) {
  background: #e9e9e9;
}
.row.header {
  font-weight: 900;
  color: #ffffff;
  background: #ea6153;
}
.row.green {
  background: #27ae60;
}
.row.blue {
  background: #2980b9;
}
@media screen and (max-width: 580px) {
  .row {
    padding: 8px 0;
    display: block;
  }
}

.cell {
  padding: 6px 12px;
  display: table-cell;
}
@media screen and (max-width: 580px) {
  .cell {
    padding: 2px 12px;
    display: block;
  }
}


</style>
</head>
<body>

 

<div class="wrapper">

  <div class="table">
    <h2> <font color ='white' >����� ��� </font> </h2>
    <div class="row header">
      <div class="cell">
           ���̵�
      </div>
      <div class="cell">
           ��й�ȣ
      </div>
      <div class="cell">
            �̸�
      </div>
      <div class="cell">
            �ּ�
      </div>
       <div class="cell">
            ����
      </div>
    </div>
    
    <% 
	request.setCharacterEncoding("EUC-KR");
    String search_for = request.getParameter("search_for");

	String search_context = request.getParameter("search_context");
	MemberDao dao = MemberDao.getInstance();
	ArrayList<MemberDto> dtos = dao.SearchMember(search_for, search_context);
	if (dtos.size() > 1) {
		out.print("�˻��� ���� ��"   + dtos.size() + "�̸� ������ �Ʒ��� �����ϴ�");
	}
	
	for (int i = 0; i < dtos.size(); i++) { // dtos �� size ��ŭ for �� ����
		MemberDto dto = dtos.get(i); 
		if (search_for.equals("���̵�")) {
			if (!search_context.equals(dto.getId())) {
%>
<script language="javascript">
	alert("���� ���̵𿡿�."); // 1 �̸� 
	history.back(); /* �������� �ڷ� ���� */
</script>
<%
	}
		} else if  (search_for.equals("�̸�"))
		{
			if (!search_context.equals(dto.getName())) {
				%>
<script language="javascript">
	alert("���� �̸��̿���."); // 1 �̸� 
	history.back(); /* �������� �ڷ� ���� */
</script>
<%}
		}
		String name = dto.getName(); // dto�� name �����Ͽ� ����
		String pw = dto.getPw();
		String id = dto.getId(); // dto�� id �����Ͽ� ����
		String address = dto.getAddress(); // dto�� address �����Ͽ� ����
%> 

		
	 <div class="row">
      <div class="cell">
     <%=id%>
      </div>
      <div class="cell">
        <%=pw%>  
      </div>
      <div class="cell">
       <%=name%> 
      </div>
      <div class="cell">
      <%=address%>
      </div>
                  <div class="cell"><a href="delMidUser.jsp?id=<%=id%>">����</a> </div>
    </div>
  <% 	
  }
  %>
    
 
  
</body>
</html>
