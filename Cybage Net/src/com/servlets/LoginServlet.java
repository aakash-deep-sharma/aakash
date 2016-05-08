package com.servlets;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.AdminDao;
import com.dao.LoginDao;
import com.dao.UserDao;
import com.utility.Utility;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<String> count;
	private LoginDao logDao;
	private HttpSession session;

    public LoginServlet() throws Exception 
    {
    	count = new ArrayList<>();
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String status = (String)request.getAttribute("status");
		try
		{
		if(status.contains("f"))
		{
			pw.println("<h2 align='center' style='font: 400;background-color:'red';font-weight: bold;'>Invalid username or password<h2>");
			RequestDispatcher rd = request.getRequestDispatcher("loginForm.html");
			rd.include(request, response);
		}
		else
		{
		String username =(String) request.getSession().getAttribute("uname");
		logDao =(LoginDao)request.getSession().getAttribute("loginDao");
		
		
		session=request.getSession();
		pw.println("<h2 align='center'>Welcome "+username+"</h2>");
				if(status.equals("A"))
				{	
					pw.print("<h2 align='left'>Count of online users are:"+count.size()+"</h2>");
					AdminDao aDao = new AdminDao(logDao.getCon());
					session.setAttribute("adminDao",aDao);
					RequestDispatcher rd = request.getRequestDispatcher("admin.html");
					rd.include(request, response);
				}
				else
				{
					count.add(username);
					UserDao uDao = new UserDao(logDao.getCon());
					session.setAttribute("userDao",uDao);
					RequestDispatcher rd = request.getRequestDispatcher("user.html");
					rd.include(request, response);
				}
				
				session.setAttribute("count", count);
				
					
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}

