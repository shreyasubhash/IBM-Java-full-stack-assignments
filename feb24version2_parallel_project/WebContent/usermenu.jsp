<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@page import="com.ibm.service.ServiceClass"%>
<%@page import="com.ibm.bean.Customer"%>
<%@page import="com.ibm.dao.DaoApply"%>



<!DOCTYPE html>
<form action="menuOp.jsp" method="post">

<html>
<head>
<meta charset="ISO-8859-1">
<title>Menu</title>
</head>
<body>

<%
if (session.getAttribute("id") != null) {
	
	response.getWriter().print("Welcome "+session.getAttribute("id"));
	ServiceClass sc=new ServiceClass();
%>

Menu



<br>
  
    <input type="radio" name="menu" value="Withdraw">
   Withdraw


  
    <input type="radio" name="menu" value="View">
  View

  
  
    <input type="radio" name="menu" value="Deposit">
  Deposit


  
    <input type="radio" name="menu" value="Transfer">
  Transfer

 
  
    <input type="radio" name="menu" value="Check">
  Check Balance status
  
  
    <input type="radio" name="menu" value="Print">
  Print Transactions
  
  
    <input type="radio" name="menu" value="Logout">
   Logout
 
 <input type="Submit" name="Submit">
 
 <%
 }

  else {
	 
	 response.getWriter().println(" Please Login Again...<br><br>");
	  
	  
	  
	 request.getRequestDispatcher("index.html").include(request, response);
	  
  }



%>
</body>
</html>