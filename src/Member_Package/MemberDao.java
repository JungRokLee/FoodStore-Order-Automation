package Member_Package;

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

import Foodstore_Package.FoodStoreDao;
import Member_Package.MemberDto;
public class MemberDao {
	public static final int MEMBER_NONEXISTENT  = 0;  
	// ������ �� ���� ���
	public static final int MEMBER_EXISTENT = 1;
	public static final int MEMBER_JOIN_FAIL = 0;
	public static final int MEMBER_JOIN_SUCCESS = 1;
	public static final int MEMBER_LOGIN_PW_NO_GOOD = 0;
	public static final int MEMBER_LOGIN_SUCCESS = 1;
	public static final int MEMBER_LOGIN_IS_NOT = -1;
	public static final int MEMBER_DELTETE_SUCCESS = 1;
	
	private static MemberDao instance = new MemberDao();
	// MemberDao() �� �̿��Ͽ� instance �� ����
	// instance �� �ڱⰡ �ڱ⸦ �����ϰ� �����ϴ� ���� 
	// static�� �ν��Ͻ��� ��������� ���� ȣ��Ǵ� �ڵ� 
	
	
	private MemberDao() {
		// ������ �����ڴ� public�� - �ܺο��� ������ �����ؾ� �ϹǷ�  
		// MemberDao �� �����ڰ� private �̹Ƿ� �ٸ������� ������ �Ұ�
		// �׷��Ƿ� �ܺο��� �ν��Ͻ� ���� ���ϰ� private �� ���� 
		// private �̹Ƿ� �ٸ������� ��� �Ұ� 
		// �� instance ��ü�� �ϳ��� �����ǰ�, ��� ������ ��� ������
		// �ڱ� Ŭ�������� �ڽ��� �����ϰ� �����ϴ� ��ü
	}  
	
	public static MemberDao getInstance(){
		return instance;
	} // private MemberDao()�� ������ �ȵǹǷ� public �ϰ� static �� 
	  //  �ν��Ͻ��� ����� �ٸ� ������ �ٷ� ������ �����ϵ����� - 
	  // ������ ���� instance �� ��ȯ
	
	// ============= insertMember ==================
	public int insertMember(MemberDto dto) {
		int ri = 0;   // ���� ���� 
		Connection connection = null;
		PreparedStatement pstmt = null;
		String query = "insert into MEMBER values (?,?,?,?,?)";	
		try {
			connection = getConnection();  // ȣ�� - �ǵڿ� ����
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, dto.getId());  // ������ ����
			pstmt.setString(2, dto.getPw());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getAddress());
			pstmt.setInt(5, dto.getStatus());

			pstmt.executeUpdate();  // ����
			ri = MemberDao.MEMBER_JOIN_SUCCESS; // 1 �̸� -�����̸�
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
				ri = MemberDao.MEMBER_EXISTENT;// �̹� �����ϴ� �Ƶ�� 1
			} else {
				ri = MemberDao.MEMBER_NONEXISTENT; // �������� �ʴ� �Ƶ�� 0
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
		String query = "select pw from MEMBER where id = ?";		
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, id);  // ����ڰ� �Է��� �Ƶ�
			set = pstmt.executeQuery();
			
			if(set.next()) {
				dbPw = set.getString("pw");
				if(dbPw.equals(pw)) {     // ����ڰ� �Է��� �н�����
					ri = MemberDao.MEMBER_LOGIN_SUCCESS;// 1 �̸� �α��� Ok
				} else {
					ri = MemberDao.MEMBER_LOGIN_PW_NO_GOOD;	// 0  ��� X
				}
			} else {
				ri = MemberDao.MEMBER_LOGIN_IS_NOT;	// -1  ȸ���� �ƴϸ�	
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
	public MemberDto getMember(String id) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "select * from MEMBER where id = ?";
		MemberDto dto = null;
		
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, id);
			set = pstmt.executeQuery();
			
			if(set.next()) {       // ó������ ������ ���� 
				dto = new MemberDto();
				dto.setId(set.getString("id"));  // ���� 
				dto.setPw(set.getString("pw"));
				dto.setName(set.getString("name"));
				dto.setAddress(set.getString("address"));
				dto.setStatus(set.getInt("status"));
		
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
       public ArrayList<MemberDto> getList() {
    	  /* public ArrayList<MemberDto> getList() {*/
		ArrayList<MemberDto> dtos = new ArrayList<MemberDto>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = getConnection();
			
			String query = "select * from MEMBER";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				String bid = resultSet.getString("id");
				String bpw = resultSet.getString("pw");
				String bname = resultSet.getString("name");
				String baddress = resultSet.getString("address");
				int bstatus = resultSet.getInt("status");
		
				MemberDto dto = new MemberDto(bid, bpw, bname, baddress,bstatus);
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
	public int updateMember(MemberDto dto) {
		int ri = 0;		
		Connection connection = null;
		PreparedStatement pstmt = null;
		String query = "update MEMBER set pw=?, name = ?,  address=? where id=?";		
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, dto.getPw());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getAddress());
			pstmt.setString(4, dto.getId());
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

	public int deleteMember(MemberDto dto) {
		int ri = 0;   // ���� ���� 
		Connection connection = null;
		PreparedStatement pstmt = null;
		String query = "delete from MEMBER where id='"+dto.getId()+"'";
		try {
			connection = getConnection();  // ȣ�� - �ǵڿ� ����
			pstmt = connection.prepareStatement(query);
			pstmt.executeUpdate();  // ����
			ri = MemberDao.MEMBER_DELTETE_SUCCESS; // 1 �̸� -�����̸�
		
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
	                                    // ����                   //����
	public MemberDto searchMember(String search_for,String search_context) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = null;
		MemberDto dto = null;
		try {
			if(search_for.equals("�۾���"))
			{
				connection = getConnection();
				query = "select * from MEMBER where name like '%?%'";
				pstmt = connection.prepareStatement(query);
				pstmt.setString(1, search_context);
				rs = pstmt.executeQuery();
			}else if(search_for.equals("���̵�"))
			{
				connection = getConnection();
				query = "select * from MEMBER where id like '%?%'";
				pstmt = connection.prepareStatement(query);
				pstmt.setString(1, search_context);
				rs = pstmt.executeQuery();
			}
		
			while(rs.next()) {       // ó������ ������ ���� 
				dto = new MemberDto();
				dto.setId(rs.getString("id"));  // ���� 
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				dto.setAddress(rs.getString("address"));
				dto.setStatus(rs.getInt("status"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}		
		return dto;		// ��ȯ
	}
	
	   public ArrayList<MemberDto> SearchMember(String search_for,String search_context) {
	    	  /* public ArrayList<MemberDto> getList() {*/
			ArrayList<MemberDto> dtos = new ArrayList<MemberDto>();
			Connection connection = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String query=null;
			try {
				if(search_for.equals("�̸�"))
				{
					connection = getConnection();
					query = "select * from MEMBER where name = ?";
					pstmt = connection.prepareStatement(query);
					pstmt.setString(1, search_context);
					rs = pstmt.executeQuery();
				}else if(search_for.equals("���̵�"))
				{
					connection = getConnection();
					query = "select * from MEMBER where id = ?";
					pstmt = connection.prepareStatement(query);
					pstmt.setString(1, search_context);
					rs = pstmt.executeQuery();
				}
				
				while (rs.next()) {
					String bid = rs.getString("id");
					String bpw = rs.getString("pw");
					String bname = rs.getString("name");
					String baddress = rs.getString("address");
					int bstatus = rs.getInt("status");
					MemberDto dto = new MemberDto(bid, bpw, bname, baddress , bstatus);
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
	   
		public int enrollBad(MemberDto dto) {
			int ri = 0;		
			Connection connection = null;
			PreparedStatement pstmt = null;
			String query = "update MEMBER set status=? where id=?";		
			try {
				connection = getConnection();
				pstmt = connection.prepareStatement(query);
				pstmt.setInt(1, 1); //�ҷ�ȸ������ ����ϱ�
				pstmt.setString(2, dto.getId());
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
		//////////////////////////////////////////////////////////////////////////
		  public ArrayList<MemberDto> getBadList() {
	    	  /* public ArrayList<MemberDto> getList() {*/
			ArrayList<MemberDto> dtos = new ArrayList<MemberDto>();
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			
			try {
				connection = getConnection();
				
				String query = "select * from MEMBER WHERE status=1";
				preparedStatement = connection.prepareStatement(query);
				resultSet = preparedStatement.executeQuery();
				
				while (resultSet.next()) {
					String bid = resultSet.getString("id");
					String bpw = resultSet.getString("pw");
					String bname = resultSet.getString("name");
					String baddress = resultSet.getString("address");
					int bstatus = resultSet.getInt("status");
			
					MemberDto dto = new MemberDto(bid, bpw, bname, baddress,bstatus);
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
		  
		  ///////////////////////////////////////////////////////////////////////
		  public int recoverUser(MemberDto dto) {
				int ri = 0;		
				Connection connection = null;
				PreparedStatement pstmt = null;
				String query = "update MEMBER set status=? where id=?";		
				try {
					connection = getConnection();
					pstmt = connection.prepareStatement(query);
					pstmt.setInt(1, 0); //����ȸ������ ������
					pstmt.setString(2, dto.getId());
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
		  
		  /////////////////////////////////////////////////////////////////////
		   public int CheckBadUser(String id) {
				int ri = 0;
				int dbstatus;		
				Connection connection = null;
				PreparedStatement pstmt = null;
				ResultSet set = null;
				String query = "select status from MEMBER where id = ?";		
				try {
					connection = getConnection();
					pstmt = connection.prepareStatement(query);
					pstmt.setString(1, id);  // ����ڰ� �Է��� �Ƶ�
					set = pstmt.executeQuery();
					
					if(set.next()) {
						dbstatus = set.getInt("status");
						if(dbstatus==1) {    //�ҷ�ȸ���̸�
							ri = FoodStoreDao.MEMBER_BADUSER;// 1 �̸� �ҷ�ȸ��
						} else {
							ri = FoodStoreDao.MEMBER_GOODUSER;	// 0�̸� ����ȸ��
						}
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
}



 