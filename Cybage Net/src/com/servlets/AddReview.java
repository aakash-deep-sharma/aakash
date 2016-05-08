package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UserDao;

/**
 * Servlet implementation class AddReview
 */
@WebServlet("/AddReview")
public class AddReview extends HttpServlet {
	
	private UserDao uDao;  
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		PrintWriter pw = response.getWriter();
		String bid = request.getParameter("b_id");
		String breview = request.getParameter("b_review");
		uDao =(UserDao) request.getSession().getAttribute("userDao");
		System.out.println("finish");
		try {
			String result=uDao.addReview(Integer.parseInt(bid),breview);
			
			if(result.contains("s"))
				pw.print("Review added");
			else
				pw.print("Review not added");
			
			RequestDispatcher rd = request.getRequestDispatcher("user.html");
			rd.include(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
	

	
}
