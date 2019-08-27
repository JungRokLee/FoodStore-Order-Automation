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
	// 수정할 수 없는 상수
	public static final int MEMBER_EXISTENT = 1;
	public static final int MEMBER_JOIN_FAIL = 0;
	public static final int MEMBER_JOIN_SUCCESS = 1;
	public static final int MEMBER_LOGIN_PW_NO_GOOD = 0;
	public static final int MEMBER_LOGIN_SUCCESS = 1;
	public static final int MEMBER_LOGIN_IS_NOT = -1;
	public static final int MEMBER_DELTETE_SUCCESS = 1;
	
	private static MemberDao instance = new MemberDao();
	// MemberDao() 를 이용하여 instance 를 생성
	// instance 는 자기가 자기를 생성하고 참조하는 변수 
	// static은 인스턴스가 만들어지기 전에 호출되는 코드 
	
	
	private MemberDao() {
		// 보통의 생성자는 public임 - 외부에서 접근이 가능해야 하므로  
		// MemberDao 는 생성자가 private 이므로 다른곳에서 접근이 불가
		// 그러므로 외부에서 인스턴스 생성 못하게 private 로 선언 
		// private 이므로 다른곳에서 상속 불가 
		// 이 instance 객체는 하나만 생성되고, 모든 곳에서 사용 가능함
		// 자기 클래스에서 자신을 생성하고 참조하는 객체
	}  
	
	public static MemberDao getInstance(){
		return instance;
	} // private MemberDao()는 접근이 안되므로 public 하고 static 한 
	  //  인스턴스를 만들어 다른 곳에서 바로 접근이 가능하도록함 - 
	  // 위에서 만든 instance 를 반환
	
	// ============= insertMember ==================
	public int insertMember(MemberDto dto) {
		int ri = 0;   // 지역 변수 
		Connection connection = null;
		PreparedStatement pstmt = null;
		String query = "insert into MEMBER values (?,?,?,?,?)";	
		try {
			connection = getConnection();  // 호출 - 맨뒤에 있음
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, dto.getId());  // 데이터 저장
			pstmt.setString(2, dto.getPw());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getAddress());
			pstmt.setInt(5, dto.getStatus());

			pstmt.executeUpdate();  // 실행
			ri = MemberDao.MEMBER_JOIN_SUCCESS; // 1 이면 -성공이면
		/*select 가 아니므로 resultSet 객체가 없고 반환형이 int 형이다.*/
			
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
	}  /* ri  select 가 아니므로 resultSet 객체가 없고 반환형이 int 형이다.*/	
	
	//============ confirmId ====================
	public int confirmId(String id) {
		int ri = 0;	
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "select id from MEMBER where id = ?";
		                 /*DB의 id            폼에서 입력한 id */
		 try {
			connection = getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, id); // id는 사용자가 폼에서 입력한 id
			set = pstmt.executeQuery();  // select 일 때 사용
			if(set.next()){
				ri = MemberDao.MEMBER_EXISTENT;// 이미 존재하는 아디면 1
			} else {
				ri = MemberDao.MEMBER_NONEXISTENT; // 존재하지 않는 아디면 0
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
		return ri;    /* 0 or  1 반환 */
	}
	
	//=============== userCheck =====================
	/*사용자가 입력한 id와 pass를 가지고 비교 입력한 값과 같은지 비교 */
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
			pstmt.setString(1, id);  // 사용자가 입력한 아디
			set = pstmt.executeQuery();
			
			if(set.next()) {
				dbPw = set.getString("pw");
				if(dbPw.equals(pw)) {     // 사용자가 입력한 패스워드
					ri = MemberDao.MEMBER_LOGIN_SUCCESS;// 1 이면 로그인 Ok
				} else {
					ri = MemberDao.MEMBER_LOGIN_PW_NO_GOOD;	// 0  비번 X
				}
			} else {
				ri = MemberDao.MEMBER_LOGIN_IS_NOT;	// -1  회원이 아니면	
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
			
			if(set.next()) {       // 처음부터 마지막 까지 
				dto = new MemberDto();
				dto.setId(set.getString("id"));  // 저장 
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
		return dto;		// 반환
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
       // 수정하려는 사람의 dto 객체를 전달받아 
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
			// 업데이트 한 결과를 정수로 반환 1 이면 정상
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
		return ri;   // 리턴 1이면 정상, 0 이면 에러 
	}
	
	//==========================================
	private Connection getConnection() {
		
	Context context = null;
	DataSource dataSource = null;
	Connection connection = null;
	try {
		context = new InitialContext();
		// 오라클 DB를 사용하기 위한 객체 context 생성
		
		dataSource = (DataSource)context.lookup("java:comp/env/jdbc/Oracle11g");
		// context 객체로 lookup 메소드에 매개변수를 이용하여 리소스를 획득한다.
		// 오라클 DB 이름은 기본적으로 java:comp/env 에 등록되어 있다.
		// 해당 영역에서 jdbc/Oracle11g 로 설정된 이름을 얻어온다.
		

		connection = dataSource.getConnection();
		// ds 객체로부터 Connection  객체 얻어온다.
		// 지금부터는 이 객체는 웹 컨테이너의 DBCP 에 의해 관리된다.
		 
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return connection;
	}

	public int deleteMember(MemberDto dto) {
		int ri = 0;   // 지역 변수 
		Connection connection = null;
		PreparedStatement pstmt = null;
		String query = "delete from MEMBER where id='"+dto.getId()+"'";
		try {
			connection = getConnection();  // 호출 - 맨뒤에 있음
			pstmt = connection.prepareStatement(query);
			pstmt.executeUpdate();  // 실행
			ri = MemberDao.MEMBER_DELTETE_SUCCESS; // 1 이면 -성공이면
		
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
	                                    // 유형                   //내용
	public MemberDto searchMember(String search_for,String search_context) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = null;
		MemberDto dto = null;
		try {
			if(search_for.equals("글쓴이"))
			{
				connection = getConnection();
				query = "select * from MEMBER where name like '%?%'";
				pstmt = connection.prepareStatement(query);
				pstmt.setString(1, search_context);
				rs = pstmt.executeQuery();
			}else if(search_for.equals("아이디"))
			{
				connection = getConnection();
				query = "select * from MEMBER where id like '%?%'";
				pstmt = connection.prepareStatement(query);
				pstmt.setString(1, search_context);
				rs = pstmt.executeQuery();
			}
		
			while(rs.next()) {       // 처음부터 마지막 까지 
				dto = new MemberDto();
				dto.setId(rs.getString("id"));  // 저장 
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
		return dto;		// 반환
	}
	
	   public ArrayList<MemberDto> SearchMember(String search_for,String search_context) {
	    	  /* public ArrayList<MemberDto> getList() {*/
			ArrayList<MemberDto> dtos = new ArrayList<MemberDto>();
			Connection connection = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String query=null;
			try {
				if(search_for.equals("이름"))
				{
					connection = getConnection();
					query = "select * from MEMBER where name = ?";
					pstmt = connection.prepareStatement(query);
					pstmt.setString(1, search_context);
					rs = pstmt.executeQuery();
				}else if(search_for.equals("아이디"))
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
				pstmt.setInt(1, 1); //불량회원으로 등록하기
				pstmt.setString(2, dto.getId());
				ri = pstmt.executeUpdate();  
				// 업데이트 한 결과를 정수로 반환 1 이면 정상
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
			return ri;   // 리턴 1이면 정상, 0 이면 에러 
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
					pstmt.setInt(1, 0); //정상회원으로 돌리기
					pstmt.setString(2, dto.getId());
					ri = pstmt.executeUpdate();  
					// 업데이트 한 결과를 정수로 반환 1 이면 정상
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
				return ri;   // 리턴 1이면 정상, 0 이면 에러 
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
					pstmt.setString(1, id);  // 사용자가 입력한 아디
					set = pstmt.executeQuery();
					
					if(set.next()) {
						dbstatus = set.getInt("status");
						if(dbstatus==1) {    //불량회원이면
							ri = FoodStoreDao.MEMBER_BADUSER;// 1 이면 불량회원
						} else {
							ri = FoodStoreDao.MEMBER_GOODUSER;	// 0이면 정상회원
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



 