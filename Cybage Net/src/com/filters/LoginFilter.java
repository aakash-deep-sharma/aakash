package com.filters;



import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.dao.LoginDao;


@WebFilter("/LoginFilter")
public class LoginFilter implements Filter {
	
	
	private LoginDao logDao;
	private HttpSession session;
	
	public void init(FilterConfig fConfig) throws ServletException
	{
		try 
		{
			
			logDao = new LoginDao();
			System.out.println("connection");
		} 
		catch (Exception e)
		{
			System.out.println("connection faild");
			e.printStackTrace();
		}
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		response.setContentType("text/html");
		String username = request.getParameter("uname");
		String password = request.getParameter("pass");
		
		
		HttpServletRequest req = (HttpServletRequest) request;
		session = req.getSession();
		
		try
		{
			String result = logDao.checkUserCredintials(username, password);
			request.setAttribute("status",result);
			if(!result.contains("f"))
			{
				System.out.println("login");
				logDao.genrateLog(username);
				session.setAttribute("loginDao",logDao);
				session.setAttribute("uname",username);
			}
			RequestDispatcher rd = request.getRequestDispatcher("LoginServlet");
			rd.forward(request, response);
			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
	}
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	
	

}
