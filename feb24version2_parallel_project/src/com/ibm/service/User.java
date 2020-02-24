package com.ibm.service;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/user")
public class User extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
if(request.getParameter("u").equals("Yes")) {
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.html");
			
			
			dispatcher.include(request, response);
			
			
		}
		else
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher("register.html");
			
			
			dispatcher.include(request, response);
			
		}
	}
	
	
	



		
	}

	

