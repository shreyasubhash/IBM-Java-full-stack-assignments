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
import com.ibm.dao.DaoApply;

/**
 * Servlet implementation class Register
 */
@WebServlet("/register")
public class Register extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		if (request.getSession().getAttribute("id") != null) {
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("usermenu.jsp");

			dispatcher.include(request, response);
		} else {

			response.getWriter().print("please login... <br><br>");
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.html");

			dispatcher.include(request, response);

		}

		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html");
		
		// TODO Auto-generated method stub

		Customer c = new Customer();
		if (request.getParameter("email").length() != 0 && request.getParameter("Dob").length() != 0
				&& request.getParameter("userName").length() != 0 && request.getParameter("passWord").length()!= 0
				&& request.getParameter("phno").length()!= 0 && request.getParameter("balance").length()!= 0) {
			c.setDob(request.getParameter("Dob"));
			c.setEmail(request.getParameter("email"));
			c.setName(request.getParameter("userName"));
			c.setPassword(request.getParameter("passWord"));
			c.setPhone(request.getParameter("phno"));
			c.a.setBalance(Integer.parseInt(request.getParameter("balance")));
			if (new ServiceClass().createAccount(c)) {

				HttpSession session = request.getSession();
				session.setAttribute("id", new DaoApply().fetchCustID(c));
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("usermenu.jsp");

				dispatcher.include(request, response);

			}
		} else {
			response.getWriter().print("Invalid user data again");
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.html");

			dispatcher.include(request, response);
		}

	}
}
