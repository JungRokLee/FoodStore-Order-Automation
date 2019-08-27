package Review_Package;

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
import Food_Package.FoodDto;
import Member_Package.MemberDto;
import Order_Package.OrderDao;
import Review_Package.ReviewDao; 
import Review_Package.ReviewDto;
 

public class ReviewDao {

	public static final int MEMBER_ORDER_SUCCESS = 1;
	public static final int MEMBER_DELTETE_SUCCESS = 1;
	
	private static ReviewDao instance = new ReviewDao();

	int num=0; // 인덱스 
	
	private ReviewDao() {
	}  
	
	public static ReviewDao getInstance(){
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

	// ============= insertReview ==================
		public int insertReview(ReviewDto dto) {
		
			int ri = 0;   // ���� ���� 
			Connection connection = null;
			PreparedStatement pstmt = null;
			String query = "insert into REVIEW(id, name, title, inform, num)  values(?, ?, ?, ?, ?)";	
			try {
				connection = getConnection();  // ȣ�� - �ǵڿ� ����
				pstmt = connection.prepareStatement(query);
				pstmt.setString(1, dto.getId());  
				pstmt.setString(2, dto.getName());
				pstmt.setString(3, dto.getTitle());
				pstmt.setString(4, dto.getInform());
				pstmt.setInt(5,num );
				pstmt.executeUpdate();  // ����
			       num++;
				ri = ReviewDao.MEMBER_ORDER_SUCCESS; // 1 �̸� -�����̸�
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
	       public ArrayList<ReviewDto> getList() {
	    	  /* public ArrayList<CookDto> getList() {*/
			ArrayList<ReviewDto> dtos = new ArrayList<ReviewDto>();
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			
			try {
				connection = getConnection();
				
				String query = "select * from REVIEW ";
				preparedStatement = connection.prepareStatement(query);
				resultSet = preparedStatement.executeQuery();
				
				while (resultSet.next()) {
					String id = resultSet.getString("id");
					String name = resultSet.getString("name");
					String title = resultSet.getString("title");
					String inform = resultSet.getString("inform");
					Timestamp time = resultSet.getTimestamp("time");
					int num = resultSet.getInt("num");			
					ReviewDto dto = new ReviewDto(id, name , title, inform, time,num);
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
	       
	      
	   	public ArrayList<ReviewDto> SearchReview(String id) {
	    	  /* public ArrayList<SearchReview> getList() {*/
			ArrayList<ReviewDto> dtos = new ArrayList<ReviewDto>();
			Connection connection = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String query=null;
			try {
				
					connection = getConnection();
					query = "select * from REVIEW where id = ?";
					pstmt = connection.prepareStatement(query);
					pstmt.setString(1, id);
					rs = pstmt.executeQuery();
			
					while (rs.next()) {
						String bid = rs.getString("id");
						String bname = rs.getString("name");
						String btitle = rs.getString("title");
						String binform = rs.getString("inform");
						Timestamp bdate = rs.getTimestamp("time");
						int bnum = rs.getInt("num");
						ReviewDto dto = new ReviewDto(bid, bname, btitle, binform,bdate,bnum);
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
	   	
		public ArrayList<ReviewDto> SearchAllReview() {
	    	  /* public ArrayList<SearchReview> getList() {*/
			ArrayList<ReviewDto> dtos = new ArrayList<ReviewDto>();
			Connection connection = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String query=null;
			try {
				
					connection = getConnection();
					query = "select * from REVIEW ";
					pstmt = connection.prepareStatement(query);
				
					rs = pstmt.executeQuery();
			
					while (rs.next()) {
						String bid = rs.getString("id");
						String bname = rs.getString("name");
						String btitle = rs.getString("title");
						String binform = rs.getString("inform");
						Timestamp bdate = rs.getTimestamp("time");	
						int bnum = rs.getInt("num");
						ReviewDto dto = new ReviewDto(bid, bname, btitle, binform, bdate,bnum);
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
		
		///////////////////////////////////////////////////////////////////////
		public int deleteReview(int num) { 
	   		int ri = 0;   // ���� ���� 
	   		Connection connection = null;
	   		PreparedStatement pstmt = null;
	   		String query = "Delete from REVIEW where num='"+num+"'";
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
}



 