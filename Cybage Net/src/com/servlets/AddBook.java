package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.AdminDao;
import com.dao.LoginDao;
import com.utility.Utility;

@WebServlet("/AddBook")
public class AddBook extends HttpServlet {
	
	private AdminDao adao;
	
	@Override
	public void init() throws ServletException {
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{ 
		response.setContentType("text/html");
		int bPrice,maxId=1;
		String bName,bAuthor;
		PrintWriter pw = response.getWriter();
		adao =  (AdminDao)request.getSession().getAttribute("adminDao");
		System.out.println("add book");
		try 
		{
			bName = request.getParameter("Bookname");
			bAuthor = request.getParameter("Bookauthor");
			bPrice = Integer.parseInt(request.getParameter("Bookprice"));
			maxId = adao.maxBookId();
			
			String result = adao.addBook(maxId, bName, bAuthor, bPrice); 
			
			if(result.contains("s"))
				pw.print("Book Added Succesfully");
			else
				pw.print("Book not Added");
				
			RequestDispatcher rd = request.getRequestDispatcher("admin.html");
			rd.include(request, response);
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}	
	}
}
