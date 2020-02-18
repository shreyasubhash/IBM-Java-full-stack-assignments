package com.ibm.trail;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Logic
 */
@WebServlet("/logic")
public class Logic extends HttpServlet {

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
//		Set the content type
		response.setContentType("text/html");
		
		
		
//		Get the value from form
		String user = request.getParameter("userName");
	//String lang="";
		//Validate the use
		String lan="";
	response.getWriter().print("Welcome " + user) ;
	response.getWriter().print("<br>");
	String lang= request.getParameter("lang");
	response.getWriter().print("<br> language selected is "+lang);
	if(lang.equals("English"))
	 lan="English";
	if(lang.equals("Hindi"))
		lan="Hindi";
	if(lang.equals("Kannada"))
		lan="Kannada";
	//response.getWriter().print("language selected is "+lan);
	response.getWriter().print("<br>");
	String genera[]=request.getParameterValues("genera");
	response.getWriter().print(" Author selected is ");
	String a=request.getParameter("Author");
	response.getWriter().println(a);
			response.getWriter().print("<br>");
	
	response.getWriter().print("genera selected is ");
	response.getWriter().print("<br>");
	for(String g:genera) {
		response.getWriter().print(g+"<br>");
	}
	//String a[]=request.getParameterValues("Author");
	
	
			
		
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().println("Post requests now supported...");
		
		doGet(request, response);
	}

}
