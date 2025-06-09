<%@page import="dto.Customer"%>
<%@page import="dto.BankAccount"%>
<%@page import="dao.BankDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Welcome to Check Balance</h1>
	<%	
		long acno= (long) request.getSession().getAttribute("acno"); 
	
		BankDao bankDao= new BankDao();
		
		BankAccount account = bankDao.fetch_by_accno(acno);
		
		Customer customer = account.getCustomer();
	
	%>
	<h1>Hello <%=customer.getName() %>, Your Account Balance is: <%=account.getAmount() %>.</h1>
	

</body>
</html>