package Food_Package;

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

import Food_Package.FoodDao;
import Food_Package.FoodDto;
import Foodstore_Package.FoodStoreDto;
import Member_Package.MemberDao;
import Member_Package.MemberDto;


public class FoodDao {
	
	public static final int MEMBER_ORDER_SUCCESS = 1;
	public static final int MEMBER_DELTETE_SUCCESS = 1;
	
	private static FoodDao instance = new FoodDao();

	
	private FoodDao() {
	
	}  
	
	public static FoodDao getInstance(){
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

	// ============= insertfood ==================
		public int insertfood(FoodDto dto) {
			int ri = 0;   // 지역 변수 
			Connection connection = null;
			PreparedStatement pstmt = null;
			String query = "insert into FOOD(id, name, price, picture, inform)  values(?, ?, ?, ?, ?)";	
			try {
				connection = getConnection();  // 호출 - 맨뒤에 있음
				pstmt = connection.prepareStatement(query);
				pstmt.setString(1, dto.getId()); 
				pstmt.setString(2, dto.getName()); 
				pstmt.setInt(3, dto.getPrice());  
				pstmt.setString(4, dto.getPicture());
				pstmt.setString(5, dto.getInform());
		
			
				pstmt.executeUpdate();  // 실행
				ri = FoodDao.MEMBER_ORDER_SUCCESS; // 1 이면 -성공이면
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
	       public ArrayList<FoodDto> getList() {
	    	  /* public ArrayList<CookDto> getList() {*/
			ArrayList<FoodDto> dtos = new ArrayList<FoodDto>();
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			
			try {
				connection = getConnection();
				
				String query = "select * from FOOD";
				preparedStatement = connection.prepareStatement(query);
				resultSet = preparedStatement.executeQuery();
				
				while (resultSet.next()) {
					String id = resultSet.getString("id");
					String name = resultSet.getString("name");
					int price = resultSet.getInt("price");
					String picture = resultSet.getString("picture");
					String inform = resultSet.getString("inform");
								
					FoodDto dto = new FoodDto(id ,name, price, picture, inform);
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
	       
	       public int deleteFood(String name) {
	   		int ri = 0;   // 지역 변수 
	   		Connection connection = null;
	   		PreparedStatement pstmt = null;
	   		String query = "delete from FOOD where name='"+name+"'";
	   		try {
	   			connection = getConnection();  // 호출 - 맨뒤에 있음
	   			pstmt = connection.prepareStatement(query);
	   			pstmt.executeUpdate();  // 실행
	   			ri = FoodDao.MEMBER_DELTETE_SUCCESS; // 1 이면 -성공이면
	   		
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
	       
	     //==============  updateFood   ==========================
	     
		public int updateFood(FoodDto dto) {
			int ri = 0;		
			Connection connection = null;
			PreparedStatement pstmt = null;
			String query = "update FOOD set name=?, price=?, picture=?, inform=? where id=? AND name = ?";		
			try {
				connection = getConnection();
				pstmt = connection.prepareStatement(query);
				pstmt.setString(1, dto.getName());
				pstmt.setInt(2, dto.getPrice());
				pstmt.setString(3, dto.getPicture());
				pstmt.setString(4, dto.getInform());
				pstmt.setString(5, dto.getId());
				pstmt.setString(6, dto.getName());
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
		
		
		public ArrayList<FoodDto> SearchFood(String id) {
	    	  /* public ArrayList<FoodDto> getList() {*/
			ArrayList<FoodDto> dtos = new ArrayList<FoodDto>();
			Connection connection = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String query=null;
			try {
				
					connection = getConnection();
					query = "select * from FOOD where id = ?";
					pstmt = connection.prepareStatement(query);
					pstmt.setString(1, id);
					rs = pstmt.executeQuery();
			
					while (rs.next()) {
						String bid = rs.getString("id");
						String bname = rs.getString("name");
						int bPrice = rs.getInt("price");
						String binform = rs.getString("inform");
						String bpicture = rs.getString("picture");
									
						FoodDto dto = new FoodDto(bid, bname, bPrice, bpicture, binform);
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



 