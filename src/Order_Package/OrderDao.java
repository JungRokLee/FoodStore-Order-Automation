package Order_Package;

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
import Member_Package.MemberDto;
import Order_Package.OrderDao; 
import Order_Package.OrderDto;
 

public class OrderDao {
	
	public static final int MEMBER_ORDER_SUCCESS = 1;
	public static final int MEMBER_DELTETE_SUCCESS = 1;
	
	private static OrderDao instance = new OrderDao();

	
	private OrderDao() {
	
	}  
	
	public static OrderDao getInstance(){
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
		public int insertOrder(OrderDto dto) {
			int ri = 0;   // 지역 변수 
			Connection connection = null;
			PreparedStatement pstmt = null;
			String query = "insert into ORDERFOOD(id, table_num, food_name, count, price, total)  values(?, ?, ?, ?, ?, ?)";	
			try {
				connection = getConnection();  // 호출 - 맨뒤에 있음
				pstmt = connection.prepareStatement(query);
				pstmt.setString(1, dto.getId());  // 데이터 저장
				pstmt.setInt(2, dto.getTable_num());
				pstmt.setString(3, dto.getFoodname());
				pstmt.setInt(4, dto.getCount());
				pstmt.setInt(5, dto.getPrice());
				pstmt.setInt(6, dto.getTotal());
				pstmt.executeUpdate();  // 실행
			
				ri = OrderDao.MEMBER_ORDER_SUCCESS; // 1 이면 -성공이면
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
	       public ArrayList<OrderDto> getList() {
	    	  /* public ArrayList<CookDto> getList() {*/
			ArrayList<OrderDto> dtos = new ArrayList<OrderDto>();
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
					int price = resultSet.getInt("price");
					int count = resultSet.getInt("count");
					Timestamp time = resultSet.getTimestamp("time");
					int total = resultSet.getInt("total");
								
					OrderDto dto = new OrderDto(id, table_num, food_name, price, count, time, total);
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
	   		String query = "Delete from ORDERFOOD where table_num='"+table_num+"'";
	   		try {
	   			connection = getConnection();  // 호출 - 맨뒤에 있음
	   			pstmt = connection.prepareStatement(query);
	   			pstmt.executeUpdate();  // 실행
	   			ri = OrderDao.MEMBER_DELTETE_SUCCESS; // 1 이면 -성공이면
	   		
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
	       
	     /////////////////////////////////////////////////////////////////////////
	    	 public int getTotal(String id) {
	    		Connection connection = null;
	 	   		PreparedStatement pstmt = null;
	 	   		ResultSet rs = null;
	 	   		String query = null;
	 	   		
	 	   		int total=0;
	 	   		int sum=0;
	 	   		
	 	   		try {

	   				connection = getConnection();
	   				query = "select sum(total) from ORDERFOOD where id = ?";
	   				pstmt = connection.prepareStatement(query);
	   				pstmt.setString(1, id);
	   				rs = pstmt.executeQuery();
	 	   			
	 	   			if(rs.next()) {       // 처음부터 마지막 까지 
	 	   			  
	 	   				sum=rs.getInt("sum(total)");
	 	   			   
	 	   		
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
	 	   		return sum;		// 반환
	 	   	}
	   	public ArrayList<OrderDto> SearchOrder(String id) {
	    	  /* public ArrayList<OrderDto> getList() {*/
			ArrayList<OrderDto> dtos = new ArrayList<OrderDto>();
			Connection connection = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String query=null;
			try {
				
					connection = getConnection();
					query = "select * from ORDERFOOD where id = ? order by table_num";
					pstmt = connection.prepareStatement(query);
					pstmt.setString(1, id);
					rs = pstmt.executeQuery();
			 
					while (rs.next()) {
						String bid = rs.getString("id");
						int btable_num = rs.getInt("table_num");
						String bfood_name = rs.getString("food_name");
						int bprice = rs.getInt("price");
						int bcount = rs.getInt("count");
						Timestamp bdate = rs.getTimestamp("time");
						int btotal = rs.getInt("total");
									
						OrderDto dto = new OrderDto(bid, btable_num, bfood_name, bprice, bcount,bdate,btotal);
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
	   	
		public int updateTotal(OrderDto dto) {
			int ri = 0;		
			Connection connection = null;
			PreparedStatement pstmt = null;
			String query = "update ORDERFOOD set total=? where food_name=? AND table_num=?";		
			try {
				connection = getConnection();
				pstmt = connection.prepareStatement(query);
				pstmt.setInt(1, dto.getTotal());
				pstmt.setString(2, dto.getFoodname());
				pstmt.setInt(3, dto.getTable_num());
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
		
		  public int deleteAllFood(String id) {
		   		int ri = 0;   // 지역 변수 
		   		Connection connection = null;
		   		PreparedStatement pstmt = null;
		   		String query = "DELETE FROM ORDERFOOD where id='"+id+"'";
		   		
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
		  
		  public ArrayList<OrderDto> SearchOrderTop(String id) {
	    	  /* public ArrayList<OrderDto> getList() {*/
			ArrayList<OrderDto> dtos = new ArrayList<OrderDto>();
			Connection connection = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String query=null;
			try {
				
					connection = getConnection();
					query = "select * from ORDERFOOD where id = ? order by count DESC";
					pstmt = connection.prepareStatement(query);
					pstmt.setString(1, id);
					rs = pstmt.executeQuery();
			 
					while (rs.next()) {
						String bid = rs.getString("id");
						int btable_num = rs.getInt("table_num");
						String bfood_name = rs.getString("food_name");
						int bprice = rs.getInt("price");
						int bcount = rs.getInt("count");
						Timestamp bdate = rs.getTimestamp("time");
						int btotal = rs.getInt("total");
									
						OrderDto dto = new OrderDto(bid, btable_num, bfood_name, bprice, bcount,bdate,btotal);
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
		  
		  
		  public ArrayList<OrderDto> SearchMoney(String id) {
	    	  /* public ArrayList<OrderDto> getList() {*/
			ArrayList<OrderDto> dtos = new ArrayList<OrderDto>();
			Connection connection = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String query=null;
			try {
				
					connection = getConnection();
					query = "select * from ORDERFOOD where id = ? order by total DESC";
					pstmt = connection.prepareStatement(query);
					pstmt.setString(1, id);
					rs = pstmt.executeQuery();
			 
					while (rs.next()) {
						String bid = rs.getString("id");
						int btable_num = rs.getInt("table_num");
						String bfood_name = rs.getString("food_name");
						int bprice = rs.getInt("price");
						int bcount = rs.getInt("count");
						Timestamp bdate = rs.getTimestamp("time");
						int btotal = rs.getInt("total");
									
						OrderDto dto = new OrderDto(bid, btable_num, bfood_name, bprice, bcount,bdate,btotal);
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
		  
		  public ArrayList<OrderDto> SearchOrderDate(String id) {
	    	  /* public ArrayList<OrderDto> getList() {*/
			ArrayList<OrderDto> dtos = new ArrayList<OrderDto>();
			Connection connection = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String query=null;
			try {
				
					connection = getConnection();
					query = "select * from ORDERFOOD where id = ? order by time DESC";
					pstmt = connection.prepareStatement(query);
					pstmt.setString(1, id);
					rs = pstmt.executeQuery();
			 
					while (rs.next()) {
						String bid = rs.getString("id");
						int btable_num = rs.getInt("table_num");
						String bfood_name = rs.getString("food_name");
						int bprice = rs.getInt("price");
						int bcount = rs.getInt("count");
						Timestamp bdate = rs.getTimestamp("time");
						int btotal = rs.getInt("total");
									
						OrderDto dto = new OrderDto(bid, btable_num, bfood_name, bprice, bcount,bdate,btotal);
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



 