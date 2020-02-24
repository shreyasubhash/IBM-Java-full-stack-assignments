package com.ibm.service;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ibm.bean.Customer;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		try{
			if(request.getParameter("passWord").length()!=0 && request.getParameter("id").length()!=0 ) {
			Customer c = new Customer();
		Integer id=Integer.parseInt(request.getParameter("id"));
		c.setId(id);
		//response.getWriter().print(request.getParameter("passWord"));
		c.setPassword(request.getParameter("passWord"));
		//response.getWriter().print(c.getPassword());
		response.getWriter().print(c);
		System.out.println(c);
		
		if(new ServiceClass().login(c)){
			HttpSession session =request.getSession();
			session.setAttribute("id", id);
			RequestDispatcher dispatcher = request.getRequestDispatcher("usermenu.jsp");

			dispatcher.include(request, response);
			
			
		}
		
		else {
			 response.getWriter().print("Invalid id or password please login again");
			 RequestDispatcher dispatcher = request.getRequestDispatcher("index.html");

				dispatcher.include(request, response);
		 }
		}
		 else {
			 response.getWriter().print("Invalid id or password please login again");
			 RequestDispatcher dispatcher = request.getRequestDispatcher("index.html");

				dispatcher.include(request, response);
		 }


		}catch(NumberFormatException n){
			response.getWriter().println("enter valid id");
			 RequestDispatcher dispatcher = request.getRequestDispatcher("index.html");

				dispatcher.include(request, response);
			
		}
		
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		if (request.getSession().getAttribute("id") != null) {
			System.out.println(request.getSession().getAttribute("id"));
			RequestDispatcher dispatcher = request.getRequestDispatcher("usermenu.jsp");

			dispatcher.include(request, response);
		} else {

			response.getWriter().print("please login... <br><br>");
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.html");

			dispatcher.include(request, response);

		}
		
	}

}
