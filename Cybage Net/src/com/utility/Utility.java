package com.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/* Utility class for creating  and closeing
 * the connection  
 */


public class Utility {
	private Connection con;
	private String url,driver,username,password;
	
	public Utility(String url, String driver, String username, String password) {
		super();
		this.url = url;
		this.driver = driver;
		this.username = username;
		this.password = password;
	}
	// Method to create connection object only one time and returns the same object whenever needed 
	public Connection getConnection() throws Exception
	{	
		System.out.println(url +" "+driver+" "+username+" "+password);
		if(con == null)
		{
		Class.forName(driver);
		con = DriverManager.getConnection(url,username,password);
		}
		return con;
	}
	//Method to close the connection
	public void closeConnection() throws SQLException
	{
		if(con != null)
			con.close();
	}
}
