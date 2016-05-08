package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.dto.BookDto;

public class UserDao {

	private Connection con;
	private PreparedStatement pst1,pst2,pst3;
	
	public UserDao(Connection con) throws Exception
	{
		this.con=con;
		pst1 = con.prepareStatement("select * from books where book_name like ?");
		pst2= con.prepareStatement("select * from reviews where book_id = ?");
		pst3 = con.prepareStatement("insert into reviews(book_id,book_review) values(?,?)");
	}
	
	public List<BookDto> searchBook(String bookName) throws SQLException
	{
		List<BookDto> list = new LinkedList<BookDto>();
		
		pst1.setString(1,"%"+bookName+"%");
		ResultSet rst = pst1.executeQuery();
		while(rst.next())
		{
			BookDto b = new BookDto(rst.getInt(1), rst.getString(2),rst.getString(3),rst.getInt(4));
			list.add(b);
		}
		return list;
	}
	
	public List<String> showReview(int id) throws SQLException
	{
		System.out.println("show review id "+id);
		
		pst2.setInt(1, id);
		ResultSet rst = pst2.executeQuery();
		System.out.println("result ="+rst);
		List<String> list = new LinkedList<String>();
		while(rst.next())
		{
			System.out.println("hello");
			list.add(rst.getString(2));
		}
		System.out.println("show review"+list);
		return list;
	}
	
	public String addReview(int id,String review)throws SQLException
	{
		pst3.setInt(1,id);
		pst3.setString(2,review );
		int result=pst3.executeUpdate();
		if(result ==1)
			return "sucsess";
		else
			return "failed";
	}
	
}
