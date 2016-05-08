package com.dao;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.utility.Utility;

public class LoginDao{

	private Connection con;
	private PreparedStatement pst;
	private Utility utill;
	
	public LoginDao() throws Exception {
		utill=new Utility("jdbc:oracle:thin:@localhost:1521:xe","oracle.jdbc.OracleDriver","system","system");
		con = utill.getConnection();
		pst = con.prepareStatement("select * from accounts where user_name=? and user_pass=?");
	}
	
	public String checkUserCredintials(String username,String password) throws SQLException
	{
		System.out.println("methode");
		pst.setString(1, username);
		pst.setString(2, password);
		ResultSet rst = pst.executeQuery();
		if(rst.next())
		{
			return rst.getString(3);
		}
		return "failed";
	}
	
	public void genrateLog(String userName)
	{
		try(PrintWriter log = new PrintWriter(new FileOutputStream("D://userlog.txt",true));)
		{
			log.write(userName+"     ");
			log.println(new Date()+"    ");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public Connection getCon() {
		return con;
	}
	
	
	
}
