package twittermap;

import twitter4j.FilterQuery;
import twitter4j.Location;
import twitter4j.ResponseList;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.Trends;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;
import java.sql.*;
import java.util.Random;
/**
 * <p>This is a code example of Twitter4J Streaming API - sample method support.<br>
 * Usage: java twitter4j.examples.PrintSampleStream<br>
 * </p>
 *
 * @author Yusuke Yamamoto - yusuke at mac.com
 */
public final class TweetGet {
    /**
     * Main entry of this application.
     *
     * @param args
     */
	 static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost/test";

	   //  Database credentials
	   static final String USER = "root";
	   static final String PASS = "root";
    public static void main(String[] args) throws TwitterException {
    	 
         
    	//just fill this
    	 ConfigurationBuilder cb = new ConfigurationBuilder();
         cb.setDebugEnabled(true)
           .setOAuthConsumerKey("YfkDoAQ4wyNsRDtmZfu78AOsk")
           .setOAuthConsumerSecret("cAOiIewZFUlIPehZN4zIJLWlpGlCStaj0T5FKm7xEQGjDjzbiF")
           .setOAuthAccessToken("43839696-9xbAXye8wmg2LCTgaYDcw0M3Iv8fHGDHGKXaJvYZf")
           .setOAuthAccessTokenSecret("ifDfy0QDWUe3FapoodPr1y32AcJtzHDXQvnOmJvRf6g8P");
         
        TwitterStream twitterStream = new TwitterStreamFactory(cb.build()).getInstance();
        Twitter twitter = new TwitterFactory().getInstance();
      
        StatusListener listener = new StatusListener() {
            private Random rand;

			@Override
            public void onStatus(Status status) {
            	if(status.getGeoLocation()!=null)
                {
            		String a=status.getGeoLocation().toString();
                                String b=status.getText();
                String lati = null;
                String longi = null;
                String [] keyword;
                keyword = null;
                keyword = new String[15];
                for(int i=0;i<keyword.length;i++)
                {
                	keyword[i] = null;
                }
                for(int i=8;i<a.length();i++)
                {	if(a.charAt(i-7) == 't'&&a.charAt(i-6) == 'i'&&a.charAt(i-5) == 't'&&a.charAt(i-4) == 'u'&&a.charAt(i-3) == 'd'&&a.charAt(i-2) == 'e'&&a.charAt(i-1) == '='){
                	StringBuilder sb = new StringBuilder();
                	int x=0;
                	while(a.charAt(i+x)!=',')
                	{sb.append(a.charAt(i+x));
                		x++;
                	}  
                	lati = sb.toString();
                	}
                	if(a.charAt(i-7) == 'g'&&a.charAt(i-6) == 'i'&&a.charAt(i-5) == 't'&&a.charAt(i-4) == 'u'&&a.charAt(i-3) == 'd'&&a.charAt(i-2) == 'e'&&a.charAt(i-1) == '='){
                	StringBuilder sb1 = new StringBuilder();
                	int x=0;
                	while(a.charAt(i+x)!='}')
                	{sb1.append(a.charAt(i+x));
                		x++;
                	}          	
                	
                	longi = sb1.toString();
                	}
                	}
                for(int i=0;i<b.length()-4;i++)
                {if(b.charAt(i) == 'I'&&b.charAt(i+1) == '\''&&b.charAt(i+2) == 'm'){
                	StringBuilder sb2 = new StringBuilder();
                	sb2.append(b.charAt(i));
                	sb2.append(b.charAt(i+1));
                	sb2.append(b.charAt(i+2));
                	keyword[0] = sb2.toString();
                	 System.out.println("\n "+keyword[0]+"\n");
                	}
                   }
                double lat = Double.parseDouble(lati);
                double lon = Double.parseDouble(longi);
                String newName = status.getText();
   			 newName = newName.replaceAll("'", " ");
   			 newName = newName.replaceAll("\\+", " ");
               	//System.out.println("@" + status.getUser().getScreenName() + " - " + newName +" "+ status.getGeoLocation());
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
            		      int h=(int)(((lat-40.699885)/(40.878192-40.699885))*49);
            		      int w=(int)(((-73.909766-lon)/(74.021867-73.909766))*49);
            		      int area= h+w;
            		      
            		      if(area>48||area<0){    Random ran = new Random();
            		      area = ran.nextInt(48) + 0;}
            		      System.out.println(area);
            		      if(area<48&&area>-1){
            		      String sql = "INSERT INTO tweets  " +
                          " VALUES ('" + status.getUser().getScreenName() + "','"+status.getUser().getFollowersCount()+"','"+status.getCreatedAt()+"','"+area+"','"+newName+"')";
            		      stmt.executeUpdate(sql);
            		      // Now you can extract all the records
            		      // to see the updated records
            		      }
            		      
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
            		            conn.close();
            		      }catch(SQLException se){
            		      }// do nothing
            		      try{
            		         if(conn!=null)
            		            conn.close();
            		      }catch(SQLException se){
            		         se.printStackTrace();
            		      }//end finally try
            		   }//end try
            		  
                }
            
            }
            @Override
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
                //System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
            }

            @Override
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
                System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
            }

            @Override
            public void onScrubGeo(long userId, long upToStatusId) {
                System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
            }

            @Override
            public void onStallWarning(StallWarning warning) {
                System.out.println("Got stall warning:" + warning);
            }

            @Override
            public void onException(Exception ex) {
                ex.printStackTrace();
            }
        };
        
        twitterStream.addListener(listener);
        FilterQuery filter = new FilterQuery();
      //40.597807, -74.047966 
	      //40.853827, -73.803520
        filter.locations(new double[][] { { -74.021867,40.699885 }, { -73.909766, 40.878192 } });

       
        twitterStream.filter(filter);
    }
}