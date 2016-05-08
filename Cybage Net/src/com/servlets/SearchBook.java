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

import com.dao.UserDao;
import com.dto.BookDto;

/**
 * Servlet implementation class SearchBook
 */
@WebServlet("/SearchBook")
public class SearchBook extends HttpServlet {
	
	private UserDao uDao;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String bName = request.getParameter("book"); 
		uDao =(UserDao) request.getSession().getAttribute("userDao");
		try {
			pw.print("<h4 align='right'><a href='Logout'>Logout</a></h4>");
			List<BookDto> list =uDao.searchBook(bName);
			if(list.isEmpty())
			{
				pw.print("<h2>No books Found</h2>");
				RequestDispatcher rd = request.getRequestDispatcher("user.html");
				rd.include(request, response);
			}
			else
			{
			pw.print("<table align='center'border='3' style='background-color: aqua;margin-top: 1in;font: normal;font-size: large;'>");
			pw.print("<form action='ShowReviews' method='post'>");
			for(BookDto b : list)
			{
				pw.print("<tr>");
				pw.print("<td><input type='radio' name='book' value='"+ b.getbId()+"-"+b.getbName()+"-"+b.getbAuthor()+"-"+b.getbPrice()+"'>"+b.getbName());
				pw.print("</td>");
				pw.print("<td>"+b.getbAuthor()+"</td>");
				pw.print("</tr>");
			}
			pw.print("<td colspan='2' align='center'> <input type='submit' value='Show Details'></td>");
			pw.print("</form>");
			pw.print("</table>");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	
}
