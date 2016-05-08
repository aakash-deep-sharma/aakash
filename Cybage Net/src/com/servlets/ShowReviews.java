package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UserDao;



@WebServlet("/ShowReviews")
public class ShowReviews extends HttpServlet {
	
	private UserDao uDao;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		uDao =(UserDao) request.getSession().getAttribute("userDao");
		try 
		{
			String details[] = request.getParameter("book").split("-");
			System.out.println("passed id="+details[0]);
            List<String> list = uDao.showReview(Integer.parseInt(details[0]));
            
            pw.print("<h4 align='right'><a href='Logout'>Logout</a></h4>");
			pw.print("<h3 align='left'>");
			pw.print("<h1>Book details are</h1><br><br>");
			pw.println("Book name = "+details[1]+"<br>" );
			pw.println("Book author = "+details[2]+"<br>");
			pw.print("Book price = "+details[3]+"<br><br>");
			pw.println("<h1>Reviews</h1> <br>");
			pw.print("<table align='left'>");
			int i =1;
			for(String review:list)
			{
				pw.print("<tr>");
				pw.print("<td>");
				pw.print((i++)+".   "+review );
				pw.print("</td>");
				pw.print("</tr>");
			}
			
			pw.print("<form action='AddReview' method='post'>");
			pw.print("<input type='hidden' name='b_id' value='"+details[0] +"'> ");
			pw.print("<tr>");
			pw.print("<td><input type='text' name='b_review'></td>");
			pw.print("<td> <input type='submit' value='ADD REVIEW'></td>");
			pw.print("</tr>");
			pw.print("</form>");
			pw.print("</table>");
			pw.print("</h3>");
			
		}
		catch (SQLException e)
		{
			
			e.printStackTrace();
		}
		
		
	}

}
