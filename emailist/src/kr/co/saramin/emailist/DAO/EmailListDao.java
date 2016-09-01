package kr.co.saramin.emailist.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.co.saramin.emailist.VO.emailListVO;

public class EmailListDao {
	
	public void insert(emailListVO vo){
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		

		try{
			// 1. Driver Loading
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost/webdb";

			// 2. get Connection 				
			conn =  DriverManager.getConnection ( url, "webdb","webdb");
			
			
			//3.create statement
			String sql = "INSERT INTO `webdb`.`emaillist` (`no`, `first_name`, `last_name`, `email`) VALUES (null, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			//4. binding
			
			pstmt.setString(1, vo.getFirstName());
			pstmt.setString(2, vo.getLastName());
			pstmt.setString(3, vo.getEmail());
			
			//5. execute SQL
			pstmt.executeUpdate();
			
			
		}catch(ClassNotFoundException ex){
			ex.printStackTrace();
			System.out.println("Driver loading fail");
			
		}catch(SQLException ex){
			ex.printStackTrace();
			System.out.println("error = "+ex);
		}finally{
			try{

				if(pstmt != null){
					pstmt.close();	
				}
				if(conn != null){
					conn.close();	
				}
				
			}catch(SQLException ex){
				ex.printStackTrace();
			}
		}
		
		
		
	}
	
	public List getList(){
			List<emailListVO> list = new ArrayList();
			Connection conn = null;
			Statement stmt = null;
			ResultSet rs  = null;
			
			try{
				// 1. Driver Loading
				Class.forName("com.mysql.jdbc.Driver");
				String url = "jdbc:mysql://localhost/webdb";

				// 2. get Connection 				
				conn =  DriverManager.getConnection ( url, "webdb","webdb");
				
				//3.create statement
				stmt = conn.createStatement();
				
				//4. excute sql
				String sql = "select * from emaillist";
				rs = stmt.executeQuery(sql);
				
				//5. resotre result
				while(rs.next()){
					Long no = rs.getLong(1);
					String firstName = rs.getString(2);
					String lastName = rs.getString(3);
					String email = rs.getString(4);
					
					emailListVO vo = new emailListVO();
								vo.setEmail(email);
								vo.setNo(no);
								vo.setFirstName(firstName);
								vo.setLastName(lastName);
								
								list.add(vo);
				}
				
			}catch(ClassNotFoundException ex){
				ex.printStackTrace();
				System.out.println("Driver loading fail");
				
			}catch(SQLException ex){
				ex.printStackTrace();
				System.out.println("error = "+ex);
			}finally{
				try{
					if(rs != null){
						rs.close();	
					}
					if(stmt != null){
						stmt.close();	
					}
					if(conn != null){
						conn.close();	
					}
					
				}catch(SQLException ex){
					ex.printStackTrace();
				}
			}
			
			
			
			return list;
	}
}
