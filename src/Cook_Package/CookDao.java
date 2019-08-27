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
		public int insertOrder(CookDto dto) {
			int ri = 0;   // ���� ���� 
			Connection connection = null;
			PreparedStatement pstmt = null;
			String query = "insert into ORDERFOOD(id, table_num, food_name, count, price, total)  values(?, ?, ?, ?, ?, ?)";	
			try {
				connection = getConnection();  // ȣ�� - �ǵڿ� ����
				pstmt = connection.prepareStatement(query);
				pstmt.setString(1, dto.getId()); 
				pstmt.setInt(2, dto.getTable_num());  
				pstmt.setString(3, dto.getFood_name());
				pstmt.setInt(4, dto.getCount());
				pstmt.setInt(5, dto.getPrice());
				pstmt.setInt(6, dto.getTotal());
			
				pstmt.executeUpdate();  // ����
				ri = CookDao.MEMBER_ORDER_SUCCESS; // 1 �̸� -�����̸�
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
	   		int ri = 0;   // ���� ���� 
	   		Connection connection = null;
	   		PreparedStatement pstmt = null;
	   		String query = "delete from ORDERFOOD where table_num='"+table_num+"'";
	   		try {
	   			connection = getConnection();  // ȣ�� - �ǵڿ� ����
	   			pstmt = connection.prepareStatement(query);
	   			pstmt.executeUpdate();  // ����
	   			ri = CookDao.MEMBER_DELTETE_SUCCESS; // 1 �̸� -�����̸�
	   		
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
	   			
	   			if(set.next()) {       // ó������ ������ ���� 
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
	   		return sum;		// ��ȯ
	   	}
}



 