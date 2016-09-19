package db;

import java.sql.*;

public class ResultDB {
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost/test?useSSL=false";

	   static final String USER = "root";
	   static final String PASS = "yj881129";
	   
	   static Connection conn = null;
	   static Statement stmt = null;
	   
	   public static void updatePassDB(int passCount) {
		   try{
	      //STEP 2: Register JDBC driver
	      Class.forName("com.mysql.jdbc.Driver");

	      //STEP 3: Open a connection
	      conn = DriverManager.getConnection(DB_URL,USER,PASS);

	      stmt = conn.createStatement();     	      //STEP 4: Execute a query
	      String sql = "UPDATE result " + "SET pass = " + passCount;
          stmt.executeUpdate(sql);
	      
	      stmt.close();
	      conn.close();
	   }catch(SQLException se){
	      //Handle errors for JDBC
	      se.printStackTrace();
	   }catch(Exception e){
	      //Handle errors for Class.forName
	      e.printStackTrace();
	   }finally{
	      //finally block used to close resources
	      try{
	         if(stmt!=null)
	            stmt.close();
	      }catch(SQLException se2){
	      }
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }
	   }
	}
	   
	public static void updateFailDB(int failCount){
		 try{
		      Class.forName("com.mysql.jdbc.Driver");     //STEP 2: Register JDBC driver
		      conn = DriverManager.getConnection(DB_URL,USER,PASS);    		      //STEP 3: Open a connection
		      
		      stmt = conn.createStatement();     	      //STEP 4: Execute a query
		      String sql = "UPDATE result " + "SET fail = " + failCount;
	          stmt.executeUpdate(sql);

		      stmt.close();    		      //STEP 6: Clean-up environment
		      conn.close();
		      
		   }catch(SQLException se){      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){   //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      try{
		         if(stmt!=null)   
		            stmt.close();   		      //finally block used to close resources
		      }catch(SQLException se2){
		      }
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		   } 
	   }
	
	public static void showResultDB(){
		
		try{
		      Class.forName("com.mysql.jdbc.Driver");     //STEP 2: Register JDBC driver
		      conn = DriverManager.getConnection(DB_URL,USER,PASS);    		      //STEP 3: Open a connection
		      
		      stmt = conn.createStatement();     	      //STEP 4: Execute a query
		      String sql = "SELECT pass,fail FROM Result";
		      ResultSet rs = stmt.executeQuery(sql);

		      while(rs.next()){              //STEP 5: Extract data from result set
		         //Retrieve by column name
		         int pass  = rs.getInt("pass");
		         int fail = rs.getInt("fail");
		         
		         //Display values
		         System.out.print("Pass: " + pass);
		         System.out.print(", Fail: " + fail);
		      }
		      //STEP 6: Clean-up environment
		      rs.close();

		      stmt.close();    		      //STEP 6: Clean-up environment
		      conn.close();
		      
		   }catch(SQLException se){      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){   //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      try{
		         if(stmt!=null)   
		            stmt.close();   		      //finally block used to close resources
		      }catch(SQLException se2){
		      }
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		   } 
		
		
	}
	   
	   


}
