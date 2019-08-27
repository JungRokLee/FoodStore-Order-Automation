package Foodstore_Package;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.sql.DataSource;

import com.sun.org.apache.xml.internal.serialize.OutputFormat.DTD;

import Foodstore_Package.FoodStoreDto;
public class FoodStoreDao {
	public static final int MEMBER_NONEXISTENT  = 0;  
	// ������ �� ���� ���
	public static final int MEMBER_EXISTENT = 1;
	public static final int MEMBER_JOIN_FAIL = 0;
	public static final int MEMBER_JOIN_SUCCESS = 1;
	public static final int MEMBER_LOGIN_PW_NO_GOOD = 0;
	public static final int MEMBER_LOGIN_SUCCESS = 1;
	public static final int MEMBER_LOGIN_IS_NOT = -1;
	public static final int MEMBER_DELTETE_SUCCESS = 1;
	public static final int MEMBER_BADUSER = 1;
	public static final int MEMBER_GOODUSER = 0;
	private static FoodStoreDao instance = new FoodStoreDao();
	// FoodStoreDao() �� �̿��Ͽ� instance �� ����
	// instance �� �ڱⰡ �ڱ⸦ �����ϰ� �����ϴ� ���� 
	// static�� �ν��Ͻ��� ��������� ���� ȣ��Ǵ� �ڵ� 
	
	
	private FoodStoreDao() {
		// ������ �����ڴ� public�� - �ܺο��� ������ �����ؾ� �ϹǷ�  
		// FoodStoreDao �� �����ڰ� private �̹Ƿ� �ٸ������� ������ �Ұ�
		// �׷��Ƿ� �ܺο��� �ν��Ͻ� ���� ���ϰ� private �� ���� 
		// private �̹Ƿ� �ٸ������� ��� �Ұ� 
		// �� instance ��ü�� �ϳ��� �����ǰ�, ��� ������ ��� ������
		// �ڱ� Ŭ�������� �ڽ��� �����ϰ� �����ϴ� ��ü
	}  
	
	public static FoodStoreDao getInstance(){
		return instance;
	} // private FoodStoreDao()�� ������ �ȵǹǷ� public �ϰ� static �� 
	  //  �ν��Ͻ��� ����� �ٸ� ������ �ٷ� ������ �����ϵ����� - 
	  // ������ ���� instance �� ��ȯ
	
	// ============= insertMember ==================
	public int insertMember(FoodStoreDto dto) {
		int ri = 0;   // ���� ���� 
		Connection connection = null;
		PreparedStatement pstmt = null;
		String query = "insert into FOODSTORE values (?,?,?,?,?)";	
		try {
			connection = getConnection();  // ȣ�� - �ǵڿ� ����
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, dto.getId());  // ������ ����
			pstmt.setString(2, dto.getPw());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getAddress());
			pstmt.setString(5, dto.getInform());
			pstmt.executeUpdate();  // ����
			ri = FoodStoreDao.MEMBER_JOIN_SUCCESS; // 1 �̸� -�����̸�
		/*select �� �ƴϹǷ� resultSet ��ü�� ���� ��ȯ���� int ���̴�.*/
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(connection != null) connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}		
		return ri;   
	}  /* ri  select �� �ƴϹǷ� resultSet ��ü�� ���� ��ȯ���� int ���̴�.*/	
	
	//============ confirmId ====================
	public int confirmId(String id) {
		int ri = 0;	
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "select id from MEMBER where id = ?";
		                 /*DB�� id            ������ �Է��� id */
		 try {
			connection = getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, id); // id�� ����ڰ� ������ �Է��� id
			set = pstmt.executeQuery();  // select �� �� ���
			if(set.next()){
				ri = FoodStoreDao.MEMBER_EXISTENT;// �̹� �����ϴ� �Ƶ�� 1
			} else {
				ri = FoodStoreDao.MEMBER_NONEXISTENT; // �������� �ʴ� �Ƶ�� 0
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				set.close();
				pstmt.close();
				connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}	
		return ri;    /* 0 or  1 ��ȯ */
	}
	
	//=============== userCheck =====================
	/*����ڰ� �Է��� id�� pass�� ������ �� �Է��� ���� ������ �� */
	public int userCheck( String id, String pw) {
		int ri = 0;
		String dbPw;		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "select pw from FOODSTORE where id = ?";		
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, id);  // ����ڰ� �Է��� �Ƶ�
			set = pstmt.executeQuery();
			
			if(set.next()) {
				dbPw = set.getString("pw");
				if(dbPw.equals(pw)) {     // ����ڰ� �Է��� �н�����
					ri = FoodStoreDao.MEMBER_LOGIN_SUCCESS;// 1 �̸� �α��� Ok
				} else {
					ri = FoodStoreDao.MEMBER_LOGIN_PW_NO_GOOD;	// 0  ��� X
				}
			} else {
				ri = FoodStoreDao.MEMBER_LOGIN_IS_NOT;	// -1  ȸ���� �ƴϸ�	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				set.close();
				pstmt.close();
				connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return ri;
	}
	
	//=============== getMember   ===================
	public FoodStoreDto getMember(String id) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "select * from FOODSTORE where id = ?";
		FoodStoreDto dto = null;
		
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, id);
			set = pstmt.executeQuery();
			
			if(set.next()) {       // ó������ ������ ���� 
				dto = new FoodStoreDto();
				dto.setId(set.getString("id"));  // ���� 
				dto.setPw(set.getString("pw"));
				dto.setName(set.getString("name"));
				dto.setAddress(set.getString("address"));
				dto.setInform(set.getString("inform"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				set.close();
				pstmt.close();
				connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}		
		return dto;		// ��ȯ
	}
		
      
	//===============  getList  =================================
       public ArrayList<FoodStoreDto> getList() {
    	  /* public ArrayList<MemberDto> getList() {*/
		ArrayList<FoodStoreDto> dtos = new ArrayList<FoodStoreDto>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = getConnection();
			
			String query = "select * from FOODSTORE";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				String bid = resultSet.getString("id");
				String bpw = resultSet.getString("pw");
				String bname = resultSet.getString("name");
				String baddress = resultSet.getString("address");
				String binform = resultSet.getString("inform");		
				FoodStoreDto dto = new FoodStoreDto(bid, bpw, bname, baddress, binform);
				dtos.add(dto);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(resultSet != null) resultSet.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return dtos;
	}
		
	//==============  updateMember   ==========================
       // �����Ϸ��� ����� dto ��ü�� ���޹޾� 
	public int updateMember(FoodStoreDto dto) {
		int ri = 0;		
		Connection connection = null;
		PreparedStatement pstmt = null;
		String query = "update FOODSTORE set pw=?, name=?, address=?, inform=? where id=?";		
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, dto.getPw());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getAddress());
			pstmt.setString(4, dto.getInform());
			pstmt.setString(5, dto.getId());
			ri = pstmt.executeUpdate();  
			// ������Ʈ �� ����� ������ ��ȯ 1 �̸� ����
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}		
		return ri;   // ���� 1�̸� ����, 0 �̸� ���� 
	}
	
	//==========================================
	private Connection getConnection() {
		
	Context context = null;
	DataSource dataSource = null;
	Connection connection = null;
	try {
		context = new InitialContext();
		// ����Ŭ DB�� ����ϱ� ���� ��ü context ����
		
		dataSource = (DataSource)context.lookup("java:comp/env/jdbc/Oracle11g");
		// context ��ü�� lookup �޼ҵ忡 �Ű������� �̿��Ͽ� ���ҽ��� ȹ���Ѵ�.
		// ����Ŭ DB �̸��� �⺻������ java:comp/env �� ��ϵǾ� �ִ�.
		// �ش� �������� jdbc/Oracle11g �� ������ �̸��� ���´�.
		

		connection = dataSource.getConnection();
		// ds ��ü�κ��� Connection  ��ü ���´�.
		// ���ݺ��ʹ� �� ��ü�� �� �����̳��� DBCP �� ���� �����ȴ�.
		 
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return connection;
	}
///////////////////////////////////////////////////////////////////////////////////////////
	public int deleteMember(FoodStoreDto dto) {
		int ri = 0;   // ���� ���� 
		Connection connection = null;
		PreparedStatement pstmt = null;
		String query = "delete from FOODSTORE where id='"+dto.getId()+"'";
		try {
			connection = getConnection();  // ȣ�� - �ǵڿ� ����
			pstmt = connection.prepareStatement(query);
			pstmt.executeUpdate();  // ����
			ri = FoodStoreDao.MEMBER_DELTETE_SUCCESS; // 1 �̸� -�����̸�
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(connection != null) connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}		
		return ri;   
	}  
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	

	  
	   public ArrayList<FoodStoreDto> SearchMemberForAdmin(String search_for,String search_context) {
	    	  /* public ArrayList<MemberDto> getList() {*/
			ArrayList<FoodStoreDto> dtos = new ArrayList<FoodStoreDto>();
			Connection connection = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String query=null;
			try {
				if(search_for.equals("�̸�"))
				{
					connection = getConnection();
					query = "select * from FOODSTORE where name like ?";
					pstmt = connection.prepareStatement(query);
					pstmt.setString(1, "%" +search_context + "%");
					rs = pstmt.executeQuery();
				}
				else if(search_for.equals("���̵�"))
				{
					connection = getConnection();
					query = "select * from FOODSTORE where id like ?";
					pstmt = connection.prepareStatement(query);
					pstmt.setString(1, "%" + search_context + "%");
					rs = pstmt.executeQuery();
				}
				else if(search_for.equals("�ּ�"))
				{
					connection = getConnection();
					query = "select * from FOODSTORE where address like ?";
					pstmt = connection.prepareStatement(query);
					pstmt.setString(1, "%" + search_context + "%");
					rs = pstmt.executeQuery();
				}
				while (rs.next()) {
					String bid = rs.getString("id");
					String bpw = rs.getString("pw");
					String bname = rs.getString("name");
					String baddress = rs.getString("address");
					String binform = rs.getString("inform");	
					FoodStoreDto dto = new FoodStoreDto(bid, bpw, bname, baddress, binform);
					dtos.add(dto);
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			} finally {
				try {
					if(rs != null) rs.close();
					if(pstmt != null) pstmt.close();
					if(connection != null) connection.close();
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			} 
			return dtos;
		}
	   
	   public ArrayList<FoodStoreDto> SearchMemberForUser(String search_for,String search_context) {
	    	  /* public ArrayList<MemberDto> getList() {*/
			ArrayList<FoodStoreDto> dtos = new ArrayList<FoodStoreDto>();
			Connection connection = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String query=null;
			try {
				if(search_for.equals("�̸�"))
				{
					connection = getConnection();
					query = "select * from FOODSTORE where name like ?";
					pstmt = connection.prepareStatement(query);
					pstmt.setString(1, "%" +search_context + "%");
					rs = pstmt.executeQuery();
				}
				else if(search_for.equals("�ּ�"))
				{
					connection = getConnection();
					query = "select * from FOODSTORE where address like ?";
					pstmt = connection.prepareStatement(query);
					pstmt.setString(1, "%" + search_context + "%");
					rs = pstmt.executeQuery();
				}
				else if(search_for.equals("����"))
				{
					connection = getConnection();
					query = "select * from FOODSTORE where inform like ?";
					pstmt = connection.prepareStatement(query);
					pstmt.setString(1, "%" + search_context + "%");
					rs = pstmt.executeQuery();
				}
				
				while (rs.next()) {
					String bid = rs.getString("id");
					String bpw = rs.getString("pw");
					String bname = rs.getString("name");
					String baddress = rs.getString("address");
					String binform = rs.getString("inform");	
					FoodStoreDto dto = new FoodStoreDto(bid, bpw, bname, baddress, binform);
					dtos.add(dto);
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			} finally {
				try {
					if(rs != null) rs.close();
					if(pstmt != null) pstmt.close();
					if(connection != null) connection.close();
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			} 
			return dtos;
		}
	   
		
}



 