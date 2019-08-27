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
	<title>http://www.blueb.co.kr</title>

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
  margin: 0 0 10px 0;
  width: 130%;
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
<form action="searchOkstoreForUser.jsp" method="post" name="orderLsit">
 

<div class="wrapper">
 <h3> <font color ='white' >������ ��� </font> </h3>
  <div class="table">
   
    <div class="row header blue">
     
      <div class="cell">
           �������̸�
      </div>
      <div class="cell">
            �ּ�
      </div>
      <div class="cell">
            ����
      </div>
       <div class="cell" >
            �ֹ��ϱ�
      </div>
       <div class="cell">
            ���亸��
      </div>
    </div>
    
    <% 
    FoodStoreDao f_dao = FoodStoreDao.getInstance();
	// MemberDAO() Ŭ������ �̿��Ͽ� m_dao ��ü ����

	ArrayList<FoodStoreDto> fdtos = f_dao.getList();
	// memberDAO �� �ִ� getList() �� �����ϰ� ���� 
	// ArrayList<MemberDto>	�� dtos ��ü�� ���� �� 
	// memberDao.getList() �� dtos �� ��ȯ �޾� ���
	for (int i = 0; i < fdtos.size(); i++) { // dtos �� size ��ŭ for �� ����
		//dtos�� get(0)--> ù��°����� dto�� �����Ѵ�..
		//�׸��� �� ù��°����� �̸�,�н�����,���̵�,�̸���,�ּҸ� �����ϰ�
		//�ؿ� �������� ���̺��� �� ������ ��´�
		//�׸��� �ٽ� �ö�ͼ� �������..�̷���
		FoodStoreDto fdto = fdtos.get(i); // dtos �� i ���� ������ ������ dto �� ����
		String name = fdto.getName(); // dto�� name �����Ͽ� ����
		String pw = fdto.getPw();
		String id = fdto.getId(); // dto�� id �����Ͽ� ����
		String address = fdto.getAddress(); // dto�� address �����Ͽ� ����
		String inform = fdto.getInform();
			
			/////////////////////////////////////////////////////////
		

	%>
		
	 <div class="row">
      <div class="cell">
       <%=name%> 
      </div>
      <div class="cell">
      <%=address%>
      </div>
      <div class="cell">
      <%=inform%>
      </div>
                <div class="cell"><a href="doOrder.jsp?id=<%=id%>">�ֹ��ϱ�</a> </div>
                <div class="cell"><a href="viewReviewForUser.jsp?id=<%=id%>">���亸��</a> </div>
    </div>
  <% 	
  }
  %>
    
  </div>
  
 <select name="search_for">
    <option value="�̸�">�̸�����</option>
    <option value="�ּ�">�ּҷ�</option>
     <option value="����">��������</option>
 
</select>
	�˻�:<input type ="text" name="search_context">
	<input type="submit" value="�˻�">
	</form> 
</body>
</html>
