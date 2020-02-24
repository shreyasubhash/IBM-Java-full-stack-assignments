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
<form action="usermenu.jsp" method="post">
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<%
if (session.getAttribute("id") != null) {
	ServiceClass sc=new ServiceClass();
%>






<%


c.setId((Integer)request.getSession().getAttribute("id"));
menuOp=(Integer)session.getAttribute("menuOp");
switch(menuOp)
{
case 1: 
	try{
	amount=Integer.parseInt(request.getParameter("wdamount"));
	
	if(sc.withdrawal(c,amount)){
	%>

	Successfully withdrawn
	<%
	}
	else 
	{
		%>
		Check ur balance n try again
	
	<% 
	}
	}catch(NumberFormatException n){
		out.println("enter valid amount");
		RequestDispatcher dispatcher = request.getRequestDispatcher("usermenu.jsp");
		
		dispatcher.include(request, response);
	}
	




break;
case 2:
	
 rs=sc.viewBalance(c);
for(Customer k:rs)
	out.println(k);
break;
case 3:
	
	
	try{
		amount=Integer.parseInt(request.getParameter("deposit"));
		
		if(sc.deposit(c, amount)){
			%>
			Successfully withdrawn
			<%
			
	}
			else
			{
			%>
		Please try again latter
		<% 
			}
		}catch(NumberFormatException n){
			out.println("enter valid amount");
			RequestDispatcher dispatcher = request.getRequestDispatcher("usermenu.jsp");
			
			dispatcher.include(request, response);
		}

break;
case 4:
	
	
	
	try{
		amount=Integer.parseInt(request.getParameter("Transfer"));
		acc=Integer.parseInt(request.getParameter("acc"));
		if(sc.send(c,acc,amount)){
		%>
		successfully sent
	<% 
		}
		else
		{
			%>
			Check ur balance n try again
			
		<%
		}
		}catch(NumberFormatException n){
			out.println("enter valid amount");
			RequestDispatcher dispatcher = request.getRequestDispatcher("usermenu.jsp");
			
			dispatcher.include(request, response);
		}

break;
case 5:
	if(sc.lowBalance(c))
	
	out.print("Account is in low-balance state...");
	else 
		out.println("Account status is healthy..");
	break;
case 6:
	
	t=sc.printTransaction(c);
	for(Transaction k:t)
		out.println(k);
	break;

case 7:
	System.out.println("Successfully logged out");
	out.println("Successfully logged out");
	session.invalidate();
	
}
%>
<input type="submit" value="Back to main Menu">

<%
 }

  else {
	 
	 response.getWriter().println(" Please Login Again...<br><br>");
	  
	  
	  
	 request.getRequestDispatcher("index.html").include(request, response);
	  
  }



%>

</body>
</html>