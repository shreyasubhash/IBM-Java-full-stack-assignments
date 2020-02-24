<%@page import="com.ibm.service.BankService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.ibm.service.ServiceClass"%>
<%@page import="com.ibm.bean.Customer"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


Enter the user id


<br>
<input type="text" name="id">



<br>
<br>
Enter the password
<br>
<input type="password" name="passWord">

 <br>
 <br>
 <br>
 
 
   <br>
<input type="submit" value="Login">
 
<%
Customer c=new Customer();
try{
Integer id=Integer.parseInt(request.getParameter("id"));
c.setId(id);
c.setPassword(request.getParameter("passWord"));

if(new ServiceClass().login(c)){
	
%>
 
 <form action=login  method="post">
 
 <%
}
 else
	 %>
	 
	 <form action=index.html  method="post">

<%

}catch(NumberFormatException n){
	out.println("enter valid id");
}
%>
</body>
</html>