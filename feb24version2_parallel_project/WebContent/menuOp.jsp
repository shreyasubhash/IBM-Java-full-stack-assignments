<%@page import="com.ibm.bean.Transaction"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.InputMismatchException"%>
<%@page import="org.eclipse.jdt.internal.compiler.ast.NullAnnotationMatching"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.ibm.service.ServiceClass"%>
<%@page import="com.ibm.bean.Customer"%>
<%@page import="com.ibm.dao.DaoApply"%>


   
 <%! int menuOp;  
 ServiceClass sc=new ServiceClass();
 Customer c=new Customer();
 ArrayList<Customer> rs=new ArrayList<>();
 Integer amount=0,acc;
 ArrayList<Transaction>t=new ArrayList<>();
 %> 


<!DOCTYPE html>


<form action="SwitchCase.jsp" method="post">
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%
if (session.getAttribute("id") != null) {
	c.setId((Integer)request.getSession().getAttribute("id"));

if(request.getParameter("menu").equals("Withdraw")) {
	session.setAttribute("menuOp", 1);
	%>
	Enter amount 
	<input type="number" value="wdamount" name="wdamount">
	
	<%
}

else if(request.getParameter("menu").equals("View")) {
	
	session.setAttribute("menuOp", 2);
%>
please press continue to view ur balance
<%
	
}

else if(request.getParameter("menu").equals("Deposit")) {
	session.setAttribute("menuOp", 3);
	%>
	Enter amount to deposit
	<input type="number" value="deposit" name="deposit">
	
	<%
}

else if(request.getParameter("menu").equals("Transfer")){
	session.setAttribute("menuOp", 4);
	%>
	Enter amount to transfer
	<input type="number" value="Transfer" name="Transfer">
	<br>
	<br>
	Enter recivers acc no
	<input type="number" value="acc" name="acc">
	
	<% 
	
	}
	else if(request.getParameter("menu").equals("Check")) {
		session.setAttribute("menuOp", 5);
		%>
		please press continue to check ur acc status ur balance
		<%
}
	
	else if(request.getParameter("menu").equals("Print")) {
		session.setAttribute("menuOp", 6);
		%>
		please press continue to print ur transaction
		<%
		

}
	
	else if(request.getParameter("menu").equals("Logout")) {
		session.setAttribute("menuOp", 7);
		%>
		please press continue to logout
			<%

	
}

}

 else {
	 
	 response.getWriter().println(" Please Login Again...<br><br>");
	  
	  
	  
	 request.getRequestDispatcher("index.html").include(request, response);
	  
 }



%>
<input type="submit" value="Continue">

</body>
</html>