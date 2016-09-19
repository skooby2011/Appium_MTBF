package db;

import java.sql.*;

import org.apache.log4j.Logger;

public class ResultDB {
		static Logger logger=Logger.getLogger(ResultDB.class);
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost/test?useSSL=false";

	   static final String USER = "root";
	   static final String PASS = "yj881129";
	   
	   static Connection conn = null;
	   static Statement stmt = null;
	   
	   public static void updatePassDB(int passCount) {
		   try{
	      Class.forName("com.mysql.jdbc.Driver");  	      //Register JDBC driver
	      conn = DriverManager.getConnection(DB_URL,USER,PASS);    //Open a connection
	      
	      stmt = conn.createStatement();     	      //Execute a query
	      String sql = "UPDATE result " + "SET pass = " + passCount;
          stmt.executeUpdate(sql);
	      
	      stmt.close();
	      conn.close();
	   }catch(SQLException se){
	      se.printStackTrace();  //Handle errors for JDBC
	   }catch(Exception e){      
	      e.printStackTrace();   //Handle errors for Class.forName
	   }finally{     
	      try{
	         if(stmt!=null)     //finally block used to close resources
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
		      Class.forName("com.mysql.jdbc.Driver");     
		      conn = DriverManager.getConnection(DB_URL,USER,PASS);    		      
		      
		      stmt = conn.createStatement();     	  
		      String sql = "UPDATE result " + "SET fail = " + failCount;
	          stmt.executeUpdate(sql);

		      stmt.close();    		     
		      conn.close();
		   }catch(SQLException se){     
		      se.printStackTrace();
		   }catch(Exception e){  
		      e.printStackTrace();
		   }finally{
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
	
	public static void getResultDB(){	
		try{
		      Class.forName("com.mysql.jdbc.Driver");    
		      conn = DriverManager.getConnection(DB_URL,USER,PASS);    		   
		      
		      stmt = conn.createStatement();     	 
		      String sql = "SELECT pass,fail FROM Result";
		      ResultSet rs = stmt.executeQuery(sql);

		      while(rs.next()){         
		         int pass  = rs.getInt("pass");
		         int fail = rs.getInt("fail");
		         
		         logger.fatal("Pass count =" + pass);
		         logger.fatal("Fail count =" + fail);
		        
		         System.out.print("Pass: " + pass);       //Display values
		         System.out.print(", Fail: " + fail);
		      }
		      rs.close();
		      stmt.close();    		      
		      conn.close();
		      
		   }catch(SQLException se){      
		      se.printStackTrace();
		   }catch(Exception e){  
		      e.printStackTrace();
		   }finally{
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
}
