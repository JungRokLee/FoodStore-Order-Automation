package Cook_Package;

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

import Cook_Package.CookDao;
import Cook_Package.CookDto;


public class CookDao {
	
	public static final int MEMBER_ORDER_SUCCESS = 1;
	public static final int MEMBER_DELTETE_SUCCESS = 1;
	
	private static CookDao instance = new CookDao();

	
	private CookDao() {
	
	}  
	
	public static CookDao getInstance(){
		return instance;
	}
	
	//====================getConnection=====================
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

	// ============= insertOrder ==================
		public int insertOrder(CookDto dto) {
			int ri = 0;   // 지역 변수 
			Connection connection = null;
			PreparedStatement pstmt = null;
			String query = "insert into ORDERFOOD(id, table_num, food_name, count, price, total)  values(?, ?, ?, ?, ?, ?)";	
			try {
				connection = getConnection();  // 호출 - 맨뒤에 있음
				pstmt = connection.prepareStatement(query);
				pstmt.setString(1, dto.getId()); 
				pstmt.setInt(2, dto.getTable_num());  
				pstmt.setString(3, dto.getFood_name());
				pstmt.setInt(4, dto.getCount());
				pstmt.setInt(5, dto.getPrice());
				pstmt.setInt(6, dto.getTotal());
			
				pstmt.executeUpdate();  // 실행
				ri = CookDao.MEMBER_ORDER_SUCCESS; // 1 이면 -성공이면
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
		}  
		
		//===============  getList  =================================
	       public ArrayList<CookDto> getList() {
	    	  /* public ArrayList<CookDto> getList() {*/
			ArrayList<CookDto> dtos = new ArrayList<CookDto>();
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			
			try {
				connection = getConnection();
				
				String query = "select * from ORDERFOOD";
				preparedStatement = connection.prepareStatement(query);
				resultSet = preparedStatement.executeQuery();
				
				while (resultSet.next()) {
					String id = resultSet.getString("id");
					int table_num = resultSet.getInt("table_num");
					String food_name = resultSet.getString("food_name");
					int count = resultSet.getInt("count");
					int price = resultSet.getInt("price");
					int total = resultSet.getInt("total");
					Timestamp date = resultSet.getTimestamp("indate");
								
					CookDto dto = new CookDto(id, table_num, food_name, count, price, total, date);
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
	       
	       public int deleteOrder(String table_num) {
	   		int ri = 0;   // 지역 변수 
	   		Connection connection = null;
	   		PreparedStatement pstmt = null;
	   		String query = "delete from ORDERFOOD where table_num='"+table_num+"'";
	   		try {
	   			connection = getConnection();  // 호출 - 맨뒤에 있음
	   			pstmt = connection.prepareStatement(query);
	   			pstmt.executeUpdate();  // 실행
	   			ri = CookDao.MEMBER_DELTETE_SUCCESS; // 1 이면 -성공이면
	   		
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
	       
	     //=============== getTotal   ===================
	   	 public int getTotal() {
	   		Connection connection = null;
	   		PreparedStatement pstmt = null;
	   		ResultSet set = null;
	   		String query = "SELECT sum(total) from ORDERFOOD";
	   		
	   		int sum=0;
	   		
	   		try {
	   			connection = getConnection();
	   			pstmt = connection.prepareStatement(query);
	   			set = pstmt.executeQuery();
	   			
	   			if(set.next()) {       // 처음부터 마지막 까지 
	   				sum=set.getInt(1);
	   				
	   		
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
	   		return sum;		// 반환
	   	}
}



 