package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.AdminDao;
import com.dto.BookDto;

/**
 * Servlet implementation class ShowBooks
 */
@WebServlet("/ShowBooks")
public class ShowBooks extends HttpServlet {

	private AdminDao adao;
	   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
response.setContentType("text/html");
		
		System.out.println("show books");
	
		PrintWriter pw = response.getWriter();
		
		try
		{		
			adao =  (AdminDao)request.getSession().getAttribute("adminDao");
			List<BookDto> list = adao.showBooks();
			pw.print("<table align='center'border='3' style='background-color: aqua;margin-top: 1in;font: normal;font-size: large;'>");
			pw.print("<form action='DeleteBook' method='post'>");
			for(BookDto b:list)
			{
				pw.print("<tr>");
				pw.print("<td><input type='checkbox' name='book' value='"+b.getbId()  +"'>"+b.getbName()+"");
				pw.print("</td>");
				pw.print("<td>");
				pw.print(b.getbAuthor());
				pw.print("</td>");
				pw.print("</tr>");
			}
			pw.print("<td colspan='2' align='center'> <input type='submit' value='DELETE'></td>");
			pw.print("</form>");
			pw.print("</table>");	
			RequestDispatcher rd = request.getRequestDispatcher("admin.html");
			rd.include(request, response);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}	
	}
}
