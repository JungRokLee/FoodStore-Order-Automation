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

	// ============= insertOrder ==================
		public int insertOrder(OrderDto dto) {
			int ri = 0;   // ���� ���� 
			Connection connection = null;
			PreparedStatement pstmt = null;
			String query = "insert into ORDERFOOD(id, table_num, food_name, count, price, total)  values(?, ?, ?, ?, ?, ?)";	
			try {
				connection = getConnection();  // ȣ�� - �ǵڿ� ����
				pstmt = connection.prepareStatement(query);
				pstmt.setString(1, dto.getId());  // ������ ����
				pstmt.setInt(2, dto.getTable_num());
				pstmt.setString(3, dto.getFoodname());
				pstmt.setInt(4, dto.getCount());
				pstmt.setInt(5, dto.getPrice());
				pstmt.setInt(6, dto.getTotal());
				pstmt.executeUpdate();  // ����
			
				ri = OrderDao.MEMBER_ORDER_SUCCESS; // 1 �̸� -�����̸�
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
	   		int ri = 0;   // ���� ���� 
	   		Connection connection = null;
	   		PreparedStatement pstmt = null;
	   		String query = "Delete from ORDERFOOD where table_num='"+table_num+"'";
	   		try {
	   			connection = getConnection();  // ȣ�� - �ǵڿ� ����
	   			pstmt = connection.prepareStatement(query);
	   			pstmt.executeUpdate();  // ����
	   			ri = OrderDao.MEMBER_DELTETE_SUCCESS; // 1 �̸� -�����̸�
	   		
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
	 	   			
	 	   			if(rs.next()) {       // ó������ ������ ���� 
	 	   			  
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
	 	   		return sum;		// ��ȯ
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
		
		  public int deleteAllFood(String id) {
		   		int ri = 0;   // ���� ���� 
		   		Connection connection = null;
		   		PreparedStatement pstmt = null;
		   		String query = "DELETE FROM ORDERFOOD where id='"+id+"'";
		   		
		   		try {
		   			connection = getConnection();  // ȣ�� - �ǵڿ� ����
		   			pstmt = connection.prepareStatement(query);
		   			pstmt.executeUpdate();  // ����
		   			ri = FoodDao.MEMBER_DELTETE_SUCCESS; // 1 �̸� -�����̸�
		   		
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



 