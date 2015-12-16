package twittermap;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.sql.*;

import twitter4j.TwitterException;
public final class KeywordGet {
	 static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost/test";
	   static final String USER = "root";
	   static final String PASS = "root";
	   public static void main(String[] args) throws TwitterException {
		   Connection conn = null;
		   Statement stmt = null;
		   try{
		      //STEP 2: Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");

		      //STEP 3: Open a connection
		     // System.out.println("Connecting to a selected database...");
		      conn = DriverManager.getConnection(DB_URL, USER, PASS);
		    //  System.out.println("Connected database successfully...");
		    
		      //STEP 4: Execute a query
		    //  System.out.println("Creating statement...");
		      stmt = conn.createStatement();
		      String sql = "UPDATE keyword SET new='n' ;";
       		     stmt.executeUpdate(sql);
       		 
		      for(int i=0;i<49;i++){if(i==0||i==1||i==2||i==7||i==8||i==9||i==15||i==16||i==17||i==18||i==23||i==24||i==25||i==26||i==31||i==32||i==33||i==39||i==40||i==47||i==48){
		       sql = "SELECT Tweet FROM tweets where Areaid='"+i+"' ORDER BY Time DESC LIMIT 5; ";
		      ResultSet rs = stmt.executeQuery(sql);
		      String ab=" ";
           	  
		      while(rs.next()){
		    	  
	               
		         String id = rs.getString("Tweet");
		        
		        ab=ab+" "+id;	                
		        }
           	try
            { //System.out.println(ab);
            String newName = ab;
			 newName = newName.replaceAll(" ", "+");
			 newName = newName.replaceAll("#", "+");
			 newName = newName.replaceAll("@", "+");
           	 if(ab.length()>100){
			 String urlString = "http://access.alchemyapi.com/calls/text/TextGetRankedNamedEntities?apikey=b12ba9b5ffacbb3f4e9188f5c9bfe2b294a0dde4&sentiment=1&showSourceText=1&text="+newName;
           	 URL url = new URL(urlString);
				 URLConnection urlConnection = url.openConnection();
            HttpURLConnection connection = null;
            if(urlConnection instanceof HttpURLConnection)
            {
               connection = (HttpURLConnection) urlConnection;
            }
            else
            {
               System.out.println("Please enter an HTTP URL.");
               return;
            }
            BufferedReader in = new BufferedReader(
            new InputStreamReader(connection.getInputStream()));
            String rel=null;
            String ent=null;
            int z=0,ct=0;
            String current;
            while((current = in.readLine()) != null)
            {
            
            	if(current.contains("            <relevance>")){
            		 rel = current.substring(current.indexOf(">") + 1, current.indexOf("/")-1);
            		
            		if(z==0)
            		z=z+5;
            	}
               if(current.contains("            <text>")){
            	  ent = current.substring(current.indexOf(">") + 1, current.indexOf("/")-1);
         		if(z==5)
         			z=z+5;
         			ct++;
         		
            	}
           		if(z==10){if(ct<5){
           		 sql = "SELECT * FROM keyword where Entity='"+ent+"' AND Areaid='"+i+"';";
     		      ResultSet rs1 = stmt.executeQuery(sql);
     		     System.out.printf(i+" "+ent+ " ");
     		    boolean found = rs1.next();
     		   if (found)
     		   {System.out.println("Record found");
     			sql = "UPDATE keyword SET Relevance='"+rel+"',new='y' WHERE Entity='"+ent+"' AND Areaid='"+i+"';";
	       		     stmt.executeUpdate(sql);
	       		     }  
     		   else
     		   {	System.out.println("RECORD NOT FOUND");
     		  sql = "INSERT INTO keyword (Areaid, Relevance, Time,Entity, new) " + 
     	           		" VALUES ("+i+",'"+rel+"', NOW(),'"+ent+"','y')";
     	       		     stmt.executeUpdate(sql);} 
     		    
           		
           			
           			 		     
           			
           	       		 		     
           		}
        		//System.out.println(rel+" "+ent);
           		z=0;
           		}
                //System.out.println(current);
            }
            sql = "DELETE FROM keyword WHERE new = 'n' AND Areaid='"+i+"';";
     		     stmt.executeUpdate(sql);
           }}catch(IOException e)
         {
            e.printStackTrace();
         }
		      
           	rs.close();
		      }
		      
		      
		      
		      // Now you can extract all the records
		      // to see the updated records
		     
		      
		      }}catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            conn.close();
		      }catch(SQLException se){
		      }// do nothing
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }
	 
	     
}}